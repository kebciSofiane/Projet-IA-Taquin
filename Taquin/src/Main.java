import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        FileReader fileReader = new FileReader();
        Scanner scanner = new Scanner(System.in);

        ArrayList<char[][]> myGrids = fileReader.readFileData
                ("Taquin/problems/taquin_2x4b.grid.txt");

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

            Boolean found = null;
            switch (rep) {
                case "1":
                    breadth_first_search.setOpen();
                    breadth_first_search.setClose();
                     found =breadth_first_search.run_Breadth_Search();

                    break;
                case "2":
                    depth_first_search.setOpen();
                    depth_first_search.setClose();
                    found =depth_first_search.run_Depth_First_Search();
                    break;
                case "3":

                    List<String> validValues = Arrays.asList("1", "2", "3", "4", "5");
                    do {
                        System.out.println("Choose an heuristic !");
                        System.out.println("1-misplacement heuristic");
                        System.out.println("2-euclideanDistance heuristic");
                        System.out.println("3-manhattanDistance heuristic");
                        System.out.println("4-misplacedCorners heuristic");
                        System.out.println("5-weightedTiles heuristic ");
                        heuristic = scanner.nextLine();
                    } while (!validValues.contains(heuristic));


                    bestFirstSearch.setHeuristicNumber(heuristic);
                    bestFirstSearch.setClose();
                    bestFirstSearch.setOpen();
                    found =bestFirstSearch.run_Best_First_Search();
                    break;

            }
            if (found) System.out.println("---Result found---");
            else System.out.println("---Can't find result---");
            System.out.println();
        } while (!rep.equals("00"));
    }
}