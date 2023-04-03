import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {


    public ArrayList<char[][]> readFileData(String fileName) throws FileNotFoundException {
        ArrayList<char[][]> myGrids = new ArrayList<>();
        ArrayList<String> fileContent = new ArrayList<>();

        File myObj = new File(fileName);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String line =myReader.nextLine();
            if ( !line.equals(""))
                fileContent.add(line);
        }

        int numberOfraws= Integer.parseInt(fileContent.get(0));
        int numberOfcols=0;

        for (int i=1; i<fileContent.size();i++)
            if (fileContent.get(i).length() > numberOfcols)
                numberOfcols = fileContent.get(i).length();
        fileContent.remove(0);

        char[][] initialGrid = new char[numberOfraws][numberOfcols];
        char[][] finalGrid = new char[numberOfraws][numberOfcols];

        for (int i=0; i<fileContent.size();i++){
            String line = fileContent.get(i);
            if (i<numberOfraws)
                for (int j=0; j<line.length();j++)
                    initialGrid[i][j]=line.charAt(j);
            else
                for (int j=0; j<line.length();j++)
                    finalGrid[i-numberOfraws][j]=line.charAt(j);
        }
        myGrids.add(initialGrid);
        myGrids.add(finalGrid);

        return myGrids;
    }
    public ArrayList<String> findFilesName(){
                File folder = new File("Taquin/problems");
                File[] files = folder.listFiles();
                ArrayList<String> names = new ArrayList<>();

                for (File file : files) {
                    if (file.isFile()) {
                        names.add(file.getName());
                    }
                }
                return  names;
        }
}
