import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {


    public ArrayList<char[][]> readFileData(String fileName){
        ArrayList<char[][]> meyGrids = new ArrayList<>();
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);

            int line =1;

            int fillRawFinalGrid=0;
            int fillRawInitialGrid=1;

            int numberOfraws = Integer.parseInt(myReader.nextLine());
            int numberOfcols =0;

            String firstRaw="";

            if (myReader.hasNextLine()) {
                firstRaw = myReader.nextLine();
                numberOfcols=firstRaw.length();
            }
            char[][] initialGrid = new char[numberOfraws][numberOfcols];
            char[][] finalGrid = new char[numberOfraws][numberOfcols];

            for (int i=0; i<firstRaw.length(); i++)
                initialGrid[0][i] =  firstRaw.charAt(i);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                if (data.length() !=0)
                    if ( line<numberOfraws){
                        for (int i=0; i<data.length(); i++)
                            initialGrid[fillRawInitialGrid][i] =  data.charAt(i);
                        fillRawInitialGrid++;}

                    else {
                        for (int i=0; i<data.length(); i++)
                            finalGrid[fillRawFinalGrid][i] =  data.charAt(i);
                        fillRawFinalGrid++;
                    }
                line++;
            }
            myReader.close();
            meyGrids.add(initialGrid);
            meyGrids.add(finalGrid);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return meyGrids;
    }

}
