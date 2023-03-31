import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader fileReader = new FileReader();
        ArrayList<String> fileNames = fileReader.findFilesName();

        Scanner scanner = new Scanner(System.in);


        int rep;
        String heuristic;

        do {
            ArrayList<Integer> possibleAnswers;
            int cmt=0;
            ArrayList<Integer> possibleAnswersForNames= new ArrayList<>();


            do {
                System.out.println("Step 1 : Choose a file !");
                for (String fileName : fileNames){
                    System.out.println(cmt+"-"+fileName);
                possibleAnswersForNames.add(cmt);
                cmt++;
                }
                System.out.println("3-Exit");
                rep = Integer.parseInt(scanner.nextLine());
                cmt=0;
            } while (!possibleAnswersForNames.contains(rep));


            ArrayList<char[][]> myGrids = fileReader.readFileData
                    ("Taquin/problems/"+fileNames.get(rep));


            char[][] initialGrid = myGrids.get(0);
            char[][] finalGrid = myGrids.get(1);

            Breadth_First_Search breadth_first_search = new Breadth_First_Search(initialGrid, finalGrid);
            Depth_First_Search depth_first_search = new Depth_First_Search(initialGrid, finalGrid);
            Best_First_Search bestFirstSearch = new Best_First_Search(initialGrid, finalGrid);


            System.out.println();
            System.out.println("Initial grid : " + Arrays.deepToString(initialGrid));
            System.out.println("Final grid : " + Arrays.deepToString(finalGrid));
            System.out.println();


            do {
                System.out.println("Choose an option !");
                System.out.println("1-Breadth first search");
                System.out.println("2-Depth first search");
                System.out.println("3-Best first search");
                System.out.println("00-Exit");

                rep = Integer.parseInt(scanner.nextLine());
                possibleAnswers= new ArrayList<>(Arrays.asList(1,2,3,0));
            } while (!possibleAnswers.contains(rep));

            switch (rep) {
                case 1:
                    breadth_first_search.setOpen();
                    breadth_first_search.setClose();
                    breadth_first_search.run_Breadth_Search();
                    break;
                case 2:
                    depth_first_search.setOpen();
                    depth_first_search.setClose();
                    depth_first_search.run_Depth_First_Search();
                    break;
                case 3:

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
                    bestFirstSearch.run_Best_First_Search();
                    break;

            }
            System.out.println();
        } while (rep!=0);
    }
}