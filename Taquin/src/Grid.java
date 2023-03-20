

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
public class Grid extends JFrame {

    public Grid()    {
        setSize( 500, 500 );
        setVisible( true );
    }
    public void paint( Graphics g )
    {
     FileReader fileReader = new FileReader();
        ArrayList<char[][]> myGrids = fileReader.readFileData
                ("Taquin/problems/taquin_3x3.grid.txt");
        char[][] initialGrid = myGrids.get(0);

        for ( int x = 200; x <= 500; x += 30 )
            for ( int y = 200; y <= 500; y += 30 ) {
                g.drawRect(x, y, 30, 30);
                g.drawString(String.valueOf(initialGrid[0][0]),x, y+30);

            }


    }
    public static void main( String args[] )
    {
        Grid application = new Grid();
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );   }  }