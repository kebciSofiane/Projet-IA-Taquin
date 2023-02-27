import java.util.Arrays;
import java.util.LinkedList;

public class Best_First {
        LinkedList<char[][]> open  = new LinkedList<>();//use a priority queue
        LinkedList<char[][]> close = new LinkedList<>();
        private final char[][] initialGrid;
        private final char[][] finalGrid;

        private final int row;
        private final int col;



        public Best_First(char[][] initialGrid, char[][] finalGrid) {
            this.finalGrid=finalGrid;
            this.initialGrid=initialGrid;
            this.row = initialGrid.length;
            this.col= initialGrid[0].length;
        }

        private int heuristiqueValue(char[][] grid){
            int cmt =0;
            for (int i=0; i<row; i++)
                for (int j=0; j<col; j++)
                    if (grid[i][j] == finalGrid[i][j]) cmt++;
            return cmt;
        }


        public void run_Best_First(){
            open.addLast(this.initialGrid);
            int emptyCaseX = -1;
            int emptyCaseY = -1;


            while (! open.isEmpty()){

                char[][] myGrid = open.poll();

                if (equals(myGrid,finalGrid)){
                    System.out.println("Final grid found with breadth_first_search: "+ Arrays.deepToString(myGrid));
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


                neighborCell(open, close, emptyCaseX, emptyCaseY-1, myGrid,emptyCaseX,emptyCaseY);
                neighborCell(open, close, emptyCaseX-1, emptyCaseY, myGrid,emptyCaseX,emptyCaseY);
                neighborCell(open, close, emptyCaseX, emptyCaseY+1, myGrid,emptyCaseX,emptyCaseY);
                neighborCell(open, close, emptyCaseX+1, emptyCaseY, myGrid,emptyCaseX,emptyCaseY);

                close.add(myGrid);
                System.out.println("State number "+close.toArray().length+" Treated");

            }
        }
        private void neighborCell(LinkedList<char[][]> open, LinkedList<char[][]> close, int x, int y, char[][] grid,int xb,int yb) {
            char[][] transitionalGrid = deepCopy(grid);
            if (canBeAccessed(x, y))
            {
                transitionalGrid[xb][yb] = grid[x][y];
                transitionalGrid[x][y] = ' ';
                if (!isIn(close, transitionalGrid) & !isIn(open, transitionalGrid)){
                    System.out.println(Arrays.deepToString(transitionalGrid));
                    open.addLast(transitionalGrid);
                }

            }
        }

        private boolean isIn(LinkedList<char[][]> llist, char[][] grid){
            for (char[][] chars : llist) {
                if (equals(chars, grid)) return true;
            }
            return false;
        }

        private  char[][] deepCopy(char[][] grid){
            char[][] newGrid = new char[row][col];
            for (int i=0; i<row; i++)
                for (int j=0; j<col; j++)
                    newGrid[i][j]=grid[i][j];
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


}
