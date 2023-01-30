public class Main {
    public static void main(String[] args) {

        char[][] grid = new char[3][3];
        char[][] fGrid = new char[3][3];

        grid[0][0] = '1';
        grid[0][1] = '2';
        grid[0][2] = '3';
        grid[1][0] = '7';
        grid[1][1] = '4';
        grid[1][2] = '5';
        grid[2][0] = '8';
        grid[2][1] = ' ';
        grid[2][2] = '6';



        fGrid[0][0] = '1';
        fGrid[0][1] = '2';
        fGrid[0][2] = '3';
        fGrid[1][0] = '4';
        fGrid[1][1] = '5';
        fGrid[1][2] = '6';
        fGrid[2][0] = '7';
        fGrid[2][1] = '8';
        fGrid[2][2] = ' ';
        Breadth_First_Search breadth_first_search = new Breadth_First_Search(grid,fGrid);

        breadth_first_search.run_Breadth_Search();






    }



}