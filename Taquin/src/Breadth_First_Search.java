import java.util.Arrays;
import java.util.LinkedList;

public class Breadth_First_Search{

    // TODO: 30/01/2023
    // Créer des classes pour les cases ?
    // Résoudre le problème avec les lettres
    LinkedList<char[][]> open  = new LinkedList<>();
    LinkedList<char[][]> close = new LinkedList<>();
    private final char[][] initialGrid;
    private final char[][] finalGrid;

    private final int row;
    private final int col;



    public Breadth_First_Search(char[][] initialGrid, char[][] finalGrid) {
        this.finalGrid=finalGrid;
        this.initialGrid=initialGrid;
        this.row = initialGrid.length;
        this.col= initialGrid[0].length;
    }



    public Boolean run_Breadth_Search(){

        open.addLast(this.initialGrid);
        int emptyCaseX = -1;
        int emptyCaseY = -1;


        while (! open.isEmpty()){

            char[][] myGrid = open.poll();

            if (equals(myGrid,finalGrid)){
                System.out.println();
                System.out.println("Final grid found with breadth_first_search: "+Arrays.deepToString(myGrid));
                System.out.println("Number of treated states: "+ close.toArray().length+ " states" );
                System.out.println("Number of waiting states: "+ open.toArray().length+ " states" );
                return true;
            }

            for (int i= 0; i< row; i++)
                for (int j= 0; j< col;j++)
                    if (myGrid[i][j]==' '){
                        emptyCaseX =i;
                        emptyCaseY =j;
                    }


            neighborCell(open, close, emptyCaseX, emptyCaseY-1, myGrid,emptyCaseX,emptyCaseY);
            neighborCell(open, close, emptyCaseX-1, emptyCaseY, myGrid,emptyCaseX,emptyCaseY);
            neighborCell(open, close, emptyCaseX, emptyCaseY+1, myGrid,emptyCaseX,emptyCaseY);
            neighborCell(open, close, emptyCaseX+1, emptyCaseY, myGrid,emptyCaseX,emptyCaseY);

            close.add(myGrid);
            System.out.println("State number "+close.toArray().length+" Treated");

        }
        System.out.println();
        System.out.println("Number of treated states: "+ close.toArray().length+ " states" );
        System.out.println("Number of waiting states: "+ open.toArray().length+ " states" );
        return false;
    }
    private void neighborCell(LinkedList<char[][]> open, LinkedList<char[][]> close, int x, int y, char[][] grid,int xb,int yb) {
        char[][] transitionalGrid = deepCopy(grid);
        if (canBeAccessed(x, y))
        {
            transitionalGrid[xb][yb] = grid[x][y];
            transitionalGrid[x][y] = ' ';
            //System.out.println(Arrays.deepToString(transitionalGrid));
            if (isNotIn(close, transitionalGrid) & isNotIn(open, transitionalGrid))
                open.addLast(transitionalGrid);

        }
    }

    private boolean isNotIn(LinkedList<char[][]> llist, char[][] grid){
        for (char[][] chars : llist) {
            if (equals(chars, grid)) return false;
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

    public boolean equals(char[][] o, char[][] o2){
            for (int i=0;i<row;i++)
                for (int j=0;j<col;j++)
                    if (o[i][j] != o2[i][j])
                        return false;
        return true;
    }


    public void setOpen() {
        this.open = new LinkedList<>();
    }

    public void setClose() {
        this.close = new LinkedList<>();
    }
}
