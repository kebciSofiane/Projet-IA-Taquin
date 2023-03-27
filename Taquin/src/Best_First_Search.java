import java.util.Arrays;
import java.util.PriorityQueue;

public class Best_First_Search {

    private final char[][] initialGrid;
    private final char[][] finalGrid;
    PriorityQueue<State>open  = new PriorityQueue<>();
    PriorityQueue<State>close  = new PriorityQueue<>();

    private final int row;
    private final int col;


    private String heuristicNumber;

    public Best_First_Search(char[][] initialGrid, char[][] finalGrid) {
        this.initialGrid = initialGrid;
        this.finalGrid = finalGrid;
        this.row = initialGrid.length;
        this.col= initialGrid[0].length;
    }




    private int heuristic(char[][] grid, String heuristic){
        switch (heuristic){
            case "1": return misplacement(grid);
            case "2": return linearConflict(grid);
            case "3": return manhattanDistance(grid);
            case "4": return misplacedCorners(grid);
            case "5": return weightedTilesHeuristic(grid);
        }
        return 0;
    }

    private int[] findPosition(char character, char[][] grid){
        for (int i=0; i<row; i++)
            for (int j=0;j<col;j++)
                if (grid[i][j] == character) return new int[]{i,j};
    return new int[]{0,0};
    }

    public  int manhattanDistance(char[][] grid){
        int totalDistance=0;
        for (int x=0; x<row; x++)
            for (int y=0;y<col; y++){
                if (grid[x][y] != ' ') {
                    int xFinal = findPosition(finalGrid[x][y],grid)[0];
                    int yFinal = findPosition(finalGrid[x][y],grid)[1];
                    totalDistance+= Math.abs(x-xFinal)+Math.abs(y-yFinal);
                }

            }

        return totalDistance;
    }

    public  int linearConflict(char[][] grid){
        int totalDistance=0;
        for (int i=0; i<row; i++)
            for (int j=0;j<col; j++){
                if (grid[i][j] != ' ') {
                    int ib = findPosition(finalGrid[i][j],grid)[0];
                    int jb = findPosition(finalGrid[i][j],grid)[1];
                    totalDistance+= Math.sqrt(
                            Math.pow((i-ib),2)+
                                    Math.pow((j-jb),2)
                    );
                }

            }
        return totalDistance;
    }

    public int misplacement(char[][] grid){
        int nbr=0;
        for (int i=0; i<row; i++)
            for (int j=0;j<col;j++)
                if ( grid[i][j]!=' ' && grid[i][j] != finalGrid[i][j])
                    nbr++;

        return  nbr;
    }

    public int weightedTilesHeuristic(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        int count = 0;

        int[][] weights = new int[n][m];


        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                weights[i][j]=1;
                if (i > 0 && j< m-1){
                    weights[i][j]=4;

                }
            }

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (i==0 || j==m-1 ||j==0 || i==n-1)
                    weights[i][j]=5;

        weights[0][0] = 6;
        weights[n-1][m-1] = 6;
        weights[0][m-1] = 6;
        weights[n-1][0] = 6;

        System.out.println(Arrays.deepToString(weights));



            for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != ' ' && board[i][j] != finalGrid[i][j]) {
                    count += weights[i][j];
                }
            }
        }
        return count;
    }

    public int misplacedCorners(char[][] grid) {
        int misplacedCorners = 0;
        if (grid[0][0] != finalGrid[0][0]) {
            misplacedCorners++;
        }
        if (grid[0][grid.length - 1] != finalGrid[0][finalGrid.length - 1]) {
            misplacedCorners++;
        }
        if (grid[grid.length - 1][0] != finalGrid[finalGrid.length - 1][0]) {
            misplacedCorners++;
        }
        if (grid[grid.length - 1][grid.length - 1] != finalGrid[finalGrid.length - 1][finalGrid.length - 1]) {
            misplacedCorners++;
        }
        return misplacedCorners;
    }




    public void run_Best_First_Search(){

        open.add(new State(initialGrid,heuristic(initialGrid, heuristicNumber)));
        int emptyCaseX = -1;
        int emptyCaseY = -1;


        while (! open.isEmpty()){

            State myGridState = open.poll();

            if (equals(myGridState.getGrid(),finalGrid)){
                System.out.println("Final grid found with Best_First_Search: "+ Arrays.deepToString(myGridState.getGrid()));
                System.out.println("Number of treated states: "+ close.toArray().length+ " states" );
                System.out.println("Number of waiting states: "+ open.toArray().length+ " states" );
                break;
            }

            for (int i= 0; i< row; i++)
                for (int j= 0; j< col;j++)
                    if (myGridState.getGrid()[i][j]==' '){
                        emptyCaseX =i;
                        emptyCaseY =j;
                    }


            neighborCell(open, close, emptyCaseX, emptyCaseY-1, myGridState.getGrid(), emptyCaseX,emptyCaseY);
            neighborCell(open, close, emptyCaseX-1, emptyCaseY, myGridState.getGrid(),emptyCaseX,emptyCaseY);
            neighborCell(open, close, emptyCaseX, emptyCaseY+1, myGridState.getGrid(),emptyCaseX,emptyCaseY);
            neighborCell(open, close, emptyCaseX+1, emptyCaseY, myGridState.getGrid(),emptyCaseX,emptyCaseY);

            close.add(myGridState);
            System.out.println("State number "+close.toArray().length+" Treated");
        }

    }

    public boolean equals(char[][] o, char[][] o2){
        for (int i=0;i<row;i++)
            for (int j=0;j<col;j++)
                if (o[i][j] != o2[i][j])
                    return false;
        return true;
    }


    private void neighborCell(PriorityQueue<State> open, PriorityQueue<State> close, int x, int y, char[][] grid,int xb,int yb) {
        char[][] transitionalGrid = deepCopy(grid);
        if (canBeAccessed(x, y))
        {
            transitionalGrid[xb][yb] = grid[x][y];
            transitionalGrid[x][y] = ' ';
            System.out.println(Arrays.deepToString(transitionalGrid)+" Heuristic value : "+heuristic(transitionalGrid, heuristicNumber));
            if (isIn(close, transitionalGrid) & isIn(open, transitionalGrid))
                open.add(new State(transitionalGrid,heuristic(transitionalGrid, heuristicNumber)));
        }
    }

    private boolean isIn(PriorityQueue<State>  stateList, char[][] grid){
        for (State state : stateList) {
            if (equals(state.getGrid(), grid)) return false;
        }
        return true;
    }

    private  char[][] deepCopy(char[][] grid){
        char[][] newGrid = new char[row][col];
        for (int i=0; i<row; i++)
            for (int j=0; j<col; j++)
                newGrid[i][j]= grid[i][j];
        return newGrid;
    }
    private boolean canBeAccessed(int i, int j){
        return i < row & i > -1 & j < col & j > -1;
    }





    public void setOpen() {
        this.open = new PriorityQueue<>();
    }

    public void setClose() {
        this.close = new PriorityQueue<>();
    }
    public void setHeuristicNumber(String heuristicNumber) {
        this.heuristicNumber = heuristicNumber;
    }

}
