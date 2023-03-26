import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        FileReader fileReader = new FileReader();
        Scanner scanner = new Scanner(System.in);

        ArrayList<char[][]> myGrids = fileReader.readFileData
                ("Taquin/problems/taquin_3x3.grid.txt");

        char[][] initialGrid = myGrids.get(0);
        char[][] finalGrid = myGrids.get(1);

        Breadth_First_Search breadth_first_search = new Breadth_First_Search(initialGrid, finalGrid);
        Depth_First_Search depth_first_search = new Depth_First_Search(initialGrid, finalGrid);
        Best_First_Search bestFirstSearch = new Best_First_Search(initialGrid, finalGrid);


        System.out.println();
        System.out.println("Initial grid : " + Arrays.deepToString(initialGrid));
        System.out.println("Final grid : " + Arrays.deepToString(finalGrid));
        System.out.println();

        String rep;
        String heuristic;

        do {
            do {
                System.out.println("Choose an option !");
                System.out.println("1-Breadth first search");
                System.out.println("2-Depth first search");
                System.out.println("3-Best first search");
                System.out.println("00-Exit");

                rep = scanner.nextLine();

            } while (!rep.equals("1") && !rep.equals("2") && !rep.equals("3") && !rep.equals("00"));

            switch (rep) {
                case "1":
                    breadth_first_search.setOpen();
                    breadth_first_search.setClose();
                    breadth_first_search.run_Breadth_Search();
                    break;
                case "2":
                    depth_first_search.setOpen();
                    depth_first_search.setClose();
                    depth_first_search.run_Depth_First_Search();
                    break;
                case "3":
                    do {
                        System.out.println("Choose an heuristic !");
                        System.out.println("1-misplacement heuristic");
                        System.out.println("2-linearConflict heuristic");
                        System.out.println("3-manhattanDistance heuristic");
                        heuristic =scanner.nextLine();
                    } while (!heuristic.equals("1") && !heuristic.equals("2") && !heuristic.equals("3"));
                    bestFirstSearch.setHeuristicNumber(heuristic);
                    bestFirstSearch.setClose();
                    bestFirstSearch.setOpen();
                    bestFirstSearch.run_Best_First_Search();
                    break;

            }
            System.out.println();
        } while (!rep.equals("00"));
    }
}