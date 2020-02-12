package hayder.mazeKillNew;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Hayder Ameen on 6/10/2015.
 */
public class Login extends JPanel implements KeyListener, MouseListener {

    URL sound = null;
    static AudioClip introSound;

    public Login() {

        setVisible(false);
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocusInWindow();

        try {
            sound = new URL("file:introSound.wav");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        introSound = Applet.newAudioClip(sound);
        introSound.loop();
    }

    public void paintComponent(Graphics g) {

        ImageIcon ic = new ImageIcon("StartScreen.png");
        Image logo = ic.getImage();
        g.drawImage(logo, 0, 0, null);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        setVisible(false);
        setFocusable(false);
        introSound.stop();

        MazeOne m1 = null;
        try {
            m1 = new MazeOne();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        m1.setVisible(true);
        MainClass.window.setContentPane(m1);
        m1.setFocusable(true);
        m1.requestFocusInWindow();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        setVisible(false);
        setFocusable(false);
        introSound.stop();

        MazeOne m1 = null;
        try {
            m1 = new MazeOne();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        m1.setVisible(true);
        MainClass.window.setContentPane(m1);
        m1.setFocusable(true);
        m1.requestFocusInWindow();

    }

    MazeOne m1 = null;

    @Override
    public void mousePressed(MouseEvent e) {

        setVisible(false);
        setFocusable(false);
        introSound.stop();


        try {
            m1 = new MazeOne();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        m1.setVisible(true);
        MainClass.window.setContentPane(m1);
        m1.requestFocusInWindow();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
