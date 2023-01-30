public class Main {
    public static void main(String[] args) {

        char[][] grid = new char[2][2];
        char[][] fGrid = new char[2][2];

        grid[0][0] = '1';
        grid[0][1] = '3';
        grid[1][0] = '2';
        grid[1][1] = ' ';

        fGrid[0][0] = '1';
        fGrid[0][1] = '2';
        fGrid[1][0] = '3';
        fGrid[1][1] = ' ';

        Breadth_First_Search depth_first_search = new Breadth_First_Search(grid,fGrid);

        depth_first_search.run_Breadth_Search();






    }



}