import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        FileReader fileReader = new FileReader();
        ArrayList<char[][]> myGrids= fileReader.readFileData
                ("Taquin/problems/taquin_3x3c.grid.txt");

        char[][] grid = myGrids.get(0);
        char[][] fGrid = myGrids.get(1);

        Breadth_First_Search breadth_first_search = new Breadth_First_Search(grid,fGrid);
        breadth_first_search.run_Breadth_Search();







    }



}