import java.util.Arrays;
import java.util.LinkedList;

public class Breadth_First_Search {

    LinkedList<char[][]> open  = new LinkedList<>();
    LinkedList<char[][]> close = new LinkedList<>();
    private char[][] initialGrid;
    private char[][] finalGrid;

    private  int row;
    private  int col;



    public Breadth_First_Search(char[][] initialGrid, char[][] finalGrid) {
        this.finalGrid=finalGrid;
        this.initialGrid=initialGrid;
        this.row = initialGrid.length;
        this.col= initialGrid[0].length;
    }


    public void run_Breadth_Search(){

        open.addLast(this.initialGrid);
        int emptyCaseX = -1;
        int emptyCaseY = -1;


        while (! open.isEmpty()){

            char[][] myGrid = open.poll();

            if (equals(myGrid,finalGrid)){
                System.out.println("Final grid found with breadth_first_search: "+Arrays.deepToString(myGrid));
                System.out.println("Number of treated states: "+ close.toArray().length+ " states" );
                System.out.println("Number of waiting states: "+ open.toArray().length+ " states" );


                break;
            }

            for (int i= 0; i< row; i++)
                for (int j= 0; j< col;j++)
                    if (myGrid[i][j]==' '){
                        emptyCaseX =i;
                        emptyCaseY =j;
                    }


            System.out.println(emptyCaseX+" "+emptyCaseY);


            neighborCell(open, close, emptyCaseX, emptyCaseY-1, myGrid,emptyCaseX,emptyCaseY);
            neighborCell(open, close, emptyCaseX-1, emptyCaseY, myGrid,emptyCaseX,emptyCaseY);
            neighborCell(open, close, emptyCaseX, emptyCaseY+1, myGrid,emptyCaseX,emptyCaseY);
            neighborCell(open, close, emptyCaseX+1, emptyCaseY, myGrid,emptyCaseX,emptyCaseY);

            close.add(myGrid);
        }
    }
    private void neighborCell(LinkedList<char[][]> open, LinkedList<char[][]> close, int x, int y, char[][] grid,int xb,int yb) {
        char[][] transitionalGrid = deepCopy(grid);
        if (canBeAccessed(x, y))
        {
            transitionalGrid[xb][yb] = grid[x][y];
            transitionalGrid[x][y] = ' ';
            System.out.println(Arrays.deepToString(transitionalGrid));
            if (!isIn(close, transitionalGrid) & !isIn(open,transitionalGrid) )
                open.addLast(transitionalGrid);

        }
    }

    private boolean isIn(LinkedList<char[][]> llist, char[][] grid){
        for(int i = 0 ; i < llist.size(); i++){
            if (equals(llist.get(i),grid)) return true;}
        return false;
    }

    private  char[][] deepCopy(char[][] grid){
        char[][] newGrid = new char[row][col];
        for (int i=0; i<row; i++)
            for (int j=0; j<row; j++)
                newGrid[i][j] = grid[i][j];
        return newGrid;
    }
    private boolean canBeAccessed(int i, int j){
        if (i<row & i>-1 & j<col & j>-1)
            return true;
        else
            return false;
    }

    public boolean equals(char[][] o, char[][] o2){
            for (int i=0;i<row;i++)
                for (int j=0;j<col;j++)
                    if (o[i][j] != o2[i][j])
                        return false;
        return true;
    }

}
