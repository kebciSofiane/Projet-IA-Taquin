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
        System.out.println();
        System.out.println();


        int rep;
        String heuristic;
        boolean ResultFound = false;

        do {
            ArrayList<Integer> validValuesSearchMethod;
            int cmt=0;
            ArrayList<Integer> validValuesForNames= new ArrayList<>();


            do {
                System.out.println("Step 1 : Choose a file !");
                for (String fileName : fileNames){
                    System.out.println(cmt+"-"+fileName);
                validValuesForNames.add(cmt);
                cmt++;
                }
                System.out.print("Your choice :");
                rep = Integer.parseInt(scanner.nextLine());
                cmt=0;
            } while (!validValuesForNames.contains(rep));


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
                System.out.println("Step 2 : Choose a search method !");
                System.out.println("1-Breadth first search");
                System.out.println("2-Depth first search");
                System.out.println("3-Best first search");
                System.out.println(fileNames.size()+"-Exit");

                System.out.print("Your choice :");
                rep = Integer.parseInt(scanner.nextLine());
                validValuesSearchMethod= new ArrayList<>(Arrays.asList(1,2,3,fileNames.size()));
            } while (!validValuesSearchMethod.contains(rep));

            switch (rep) {
                case 1:
                    breadth_first_search.setOpen();
                    breadth_first_search.setClose();
                    ResultFound=breadth_first_search.run_Breadth_Search();
                    break;
                case 2:
                    depth_first_search.setOpen();
                    depth_first_search.setClose();
                    ResultFound=depth_first_search.run_Depth_First_Search();
                    break;
                case 3:
                    List<String> validValuesHeuristic = Arrays.asList("1", "2", "3", "4", "5");
                    do {
                        System.out.println("Step 3 : Choose an heuristic !");
                        System.out.println("1-misplacement heuristic");
                        System.out.println("2-euclideanDistance heuristic");
                        System.out.println("3-manhattanDistance heuristic");
                        System.out.println("4-misplacedCorners heuristic");
                        System.out.println("5-weightedTiles heuristic ");
                        System.out.print("Your choice :");
                        heuristic = scanner.nextLine();
                    } while (!validValuesHeuristic.contains(heuristic));


                    bestFirstSearch.setHeuristicNumber(heuristic);
                    bestFirstSearch.setClose();
                    bestFirstSearch.setOpen();
                    ResultFound =bestFirstSearch.run_Best_First_Search();
                    break;

            }
            if (ResultFound) System.out.println("---Result ResultFound---");
            else System.out.println("---Can't find result---");
            System.out.println();
        } while (rep!=0);
    }
}