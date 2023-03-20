import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        FileReader fileReader = new FileReader();
        ArrayList<char[][]> myGrids= fileReader.readFileData
                ("Taquin/problems/taquin_3x3.grid.txt");

        char[][] grid = myGrids.get(0);
        char[][] fGrid = myGrids.get(1);

        Breadth_First_Search breadth_first_search = new Breadth_First_Search(grid,fGrid);
        Depth_First_Search depth_first_search = new Depth_First_Search(grid,fGrid);
        Best_First_Search bestFirstSearch = new Best_First_Search(grid,fGrid);
        //breadth_first_search.run_Breadth_Search();
        bestFirstSearch.run_Best_First_Search();
        //depth_first_search.run_Depth_First_Search();






    }



}