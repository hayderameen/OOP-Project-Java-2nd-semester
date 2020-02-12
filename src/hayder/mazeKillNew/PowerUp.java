package hayder.mazeKillNew;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Hayder Ameen on 6/10/2015.
 */
public class PowerUp {

    public int X;
    public int Y;

    public ImageIcon img;
    public Image image;

    public PowerUp(int x, int y, ImageIcon img) {

        this.X = x;
        this.Y = y;

        this.img = img;
        image = this.img.getImage();
    }
}
