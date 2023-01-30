import java.util.Arrays;
import java.util.LinkedList;

public class Breadth_First_Search {

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
        LinkedList<char[][]> open  = new LinkedList<>();
        LinkedList<char[][]> close = new LinkedList<>();
        open.add(initialGrid);
        int emptyCaseX = -1;
        int emptyCaseY = -1;


        while (! open.isEmpty()){
            char[][] myGrid = open.poll();
            if (myGrid == finalGrid)
                break;

            for (int i= 0; i< row; i++)
                for (int j= 0; j< col;j++){
                    if (myGrid[i][j]==' '){
                        emptyCaseX =i;
                        emptyCaseY =j;
                    }
                }
            System.out.println(emptyCaseX+" "+emptyCaseY);


            char[][] transitionalGrid;

            if (canBeAccessed(emptyCaseX,emptyCaseY-1))
            {
                System.out.println(Arrays.deepToString(myGrid));
                transitionalGrid=myGrid;
                transitionalGrid[emptyCaseX][emptyCaseY] = transitionalGrid[emptyCaseX][emptyCaseY-1];
                transitionalGrid[emptyCaseX][emptyCaseY-1] = ' ';
                System.out.println(Arrays.deepToString(transitionalGrid));

                if (!close.contains(transitionalGrid) & !open.contains(transitionalGrid) )
                    open.add(transitionalGrid);

            }
            if (canBeAccessed(emptyCaseX-1,emptyCaseY))
            {
                transitionalGrid=myGrid;
                System.out.println(Arrays.deepToString(transitionalGrid));

                transitionalGrid[emptyCaseX][emptyCaseY] = transitionalGrid[emptyCaseX-1][emptyCaseY];
                transitionalGrid[emptyCaseX-1][emptyCaseY] = ' ';
                if (!close.contains(transitionalGrid) & !open.contains(transitionalGrid) )
                    open.add(transitionalGrid);
                System.out.println(Arrays.deepToString(transitionalGrid));

            }
            if (canBeAccessed(emptyCaseX+1,emptyCaseY))
            {
                transitionalGrid=myGrid;
                transitionalGrid[emptyCaseX][emptyCaseY] = transitionalGrid[emptyCaseX+1][emptyCaseY];
                transitionalGrid[emptyCaseX+1][emptyCaseY] = ' ';
                if (!close.contains(transitionalGrid) & !open.contains(transitionalGrid) )
                    open.add(transitionalGrid);

            }
            if (canBeAccessed(emptyCaseX,emptyCaseY+1))
            {
                transitionalGrid=myGrid;
                transitionalGrid[emptyCaseX][emptyCaseY] = transitionalGrid[emptyCaseX][emptyCaseY+1];
                transitionalGrid[emptyCaseX][emptyCaseY+1] = ' ';

                if (!close.contains(transitionalGrid) & !open.contains(transitionalGrid) )
                    open.add(transitionalGrid);
            }

            close.add(myGrid);


        }

    }


    private boolean canBeAccessed(int i, int j){
        if (i<row & i>-1 & j<col & j>-1)
            return true;
        else
            return false;
    }
}
