package hayder.mazeKillNew;

import javax.swing.*;
import java.io.FileNotFoundException;

/**
 * Created by Hayder Ameen on 6/2/2015.
 *
 * Runs the game the initial compontnts etc
 */
public class MainClass {

    static JFrame window;

    public static void main(String[] args) throws FileNotFoundException {

        Logo l = new Logo();
        l.setVisible(true);

        window = new JFrame("World Of Tanks");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1125,1000);
        window.setContentPane( l );
        window.setLocationRelativeTo(null);
        window.setResizable(true);
        window.setVisible(true);


    }
}
