import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        FileReader fileReader = new FileReader();
        ArrayList<char[][]> myGrids= fileReader.readFileData
                ("Taquin/problems/taquinTest1.grid.txt");

        char[][] grid = myGrids.get(0);
        char[][] fGrid = myGrids.get(1);

        System.out.println("Initial state : "+Arrays.deepToString(grid));
        System.out.println("Final state : "+Arrays.deepToString(fGrid));

        System.out.println("----------------");



        Breadth_First_Search breadth_first_search = new Breadth_First_Search(grid,fGrid);
        breadth_first_search.run_Breadth_Search();

      /* Depth_First_Search depth_first_search = new Depth_First_Search(grid,fGrid);
        depth_first_search.run_Depth_Search();*/







    }



}