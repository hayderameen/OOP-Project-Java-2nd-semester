package hayder.mazeKillNew;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

/**
 * Created by Hayder Ameen on 6/9/2015.
 */
public class Logo extends JPanel implements ActionListener, KeyListener, MouseListener {

    Timer speed;

    public Logo() {
        setBackground(Color.WHITE);
        setVisible(false);
        setFocusable(true);
        addMouseListener(this);
        addKeyListener(this);

        speed = new Timer(2000, this);
        speed.start();
    }

    public void paintComponent(Graphics g) {

        ImageIcon ic = new ImageIcon("intro.png");
        Image intro = ic.getImage();
        g.drawImage(intro, 0, 0, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        setVisible(false);
        Login l = new Login();
        l.setVisible(true);
        l.setFocusable(true);
        l.requestFocusInWindow();
        l.repaint();
        MainClass.window.setContentPane(l);



        speed.stop();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

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
