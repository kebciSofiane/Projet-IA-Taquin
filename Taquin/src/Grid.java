

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

        for ( int x = 0; x < 3; x += 1 )
            for ( int y = 0; y < 3; y += 1 ) {
                g.drawRect(x*30+100, y*30+100, 30, 30);
                g.drawString(String.valueOf(initialGrid[y][x]),x*30+100, y*30+130);
            }
    }
    public static void main( String args[] )
    {
        Grid application = new Grid();
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );   }  }