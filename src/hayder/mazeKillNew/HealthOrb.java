package hayder.mazeKillNew;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Hayder Ameen on 6/10/2015.
 */
public class HealthOrb extends PowerUp {

    Rectangle location;
    boolean used = false;

    public HealthOrb(int x, int y, ImageIcon img) {

        super(x, y, img);

        location = new Rectangle(x, y, 28,34);
    }
}
