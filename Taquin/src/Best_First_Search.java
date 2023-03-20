import java.util.Arrays;
import java.util.PriorityQueue;

public class Best_First_Search {

    private final char[][] initialGrid;
    private final char[][] finalGrid;
    PriorityQueue<State>open  = new PriorityQueue<>();
    PriorityQueue<State>close  = new PriorityQueue<>();

    private int heuristic(char[][] grid){
        return linearConflict(grid);
    }

    private final int row;
    private final int col;


    private int[] findPosition(char character, char[][] grid){
        for (int i=0; i<row; i++)
            for (int j=0;j<col;j++)
                if (grid[i][j] == character) return new int[]{i,j};
    return new int[]{0,0};
    }

    public  int manhattanDistanceHeuristic(char[][] grid){
        int totalDistance=0;
        for (int i=0; i<row; i++)
            for (int j=0;j<col; j++){
                int ib = findPosition(finalGrid[i][j],grid)[0];
                int jb = findPosition(finalGrid[i][j],grid)[1];
                totalDistance+= Math.abs(i-ib)+Math.abs(j-jb);
            }

        return totalDistance;
    }

    public  int linearConflict(char[][] grid){
        int totalDistance=0;
        for (int i=0; i<row; i++)
            for (int j=0;j<col; j++){
                int ib = findPosition(finalGrid[i][j],grid)[0];
                int jb = findPosition(finalGrid[i][j],grid)[1];
                totalDistance+= Math.sqrt(
                        Math.pow((i-ib),2)+
                        Math.pow((j-jb),2)
                );
            }
        return totalDistance;
    }

    public int misplacement(char[][] grid){
        int nbr=0;
        for (int i=0; i<row; i++)
            for (int j=0;j<col;j++)
                if (grid[i][j] != finalGrid[i][j])
                    nbr++;

        return  nbr;
    }


    public Best_First_Search(char[][] initialGrid, char[][] finalGrid) {
        this.initialGrid = initialGrid;
        this.finalGrid = finalGrid;
        this.row = initialGrid.length;
        this.col= initialGrid[0].length;
    }
    public void run_Best_First_Search(){

        open.add(new State(initialGrid,heuristic(initialGrid)));
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
            System.out.println(Arrays.deepToString(transitionalGrid)+" Wrong : "+heuristic(transitionalGrid));
            if (isIn(close, transitionalGrid) & isIn(open, transitionalGrid))
                open.add(new State(transitionalGrid,heuristic(transitionalGrid)));
        }
    }

    private boolean isIn(PriorityQueue<State>  stateList, char[][] grid){
        for (State state : stateList) {
            if (equals(state.getGrid(), grid)) return false;// vérifie dans les autre classe le false
        }
        return true;
    }

    private  char[][] deepCopy(char[][] grid){
        char[][] newGrid = new char[row][col];
        for (int i=0; i<row; i++)
            System.arraycopy(grid[i], 0, newGrid[i], 0, row);
        return newGrid;
    }
    private boolean canBeAccessed(int i, int j){
        return i < row & i > -1 & j < col & j > -1;
    }
}
