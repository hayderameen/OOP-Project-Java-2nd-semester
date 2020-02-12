package hayder.mazeKillNew;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Hayder Ameen on 6/2/2015.
 *
 * Contains all the components of Maze one along with
 * ActionListeners etc related to this class only.
 */
public class MazeOne extends JPanel implements MouseMotionListener {

    // Player 1 Health
    int p1 = 50;
    // Player 2 Health
    int p2 = 50;


    // ArrayList for containing all the rectangles for collisions
    static ArrayList<Rectangle> maze = new ArrayList<Rectangle>();

    // For the coordinate system to help build the game!
    int xx, yy;

    // Coordinates of the dummy box on screen
    int boxX, boxY, prevX, prevY;

    // Coordinates of dummy box on screen
    int box2X, box2Y, prev2X, prev2Y;

    // Coordinates of Bullets on screen (Player 1)
    int bulletX, bulletY;

    // Coordinates of Bullets on screen (Player 2)
    int bullet2X, bullet2Y;

    // Whether a bullet is fired or not (Player 1)
    boolean fired;

    // Whether a bullet is fired or not (Player 2)
    boolean fired2;

    // For storing what is the current movement direction (box 1)
    boolean up, down, left, right = true;

    // For storing what is the current movement direction (box 2)
    boolean up2, down2, left2 = true, right2;

    // Direction of Bullet (Player 1)
    boolean bUp, bDown, bLeft, bRight = true; // Initially bullet on right direction

    // Direction of Bullet (Player 2)
    boolean b2Up, b2Down, b2Left = true, b2Right; // Initially bullet on left direction

    Timer bulletSpeed; // Player 1
    Timer bulletSpeed2; // Player 2
//    Timer blastAnimation; // Blast Animation

    // Booleans for which player lost
    boolean player1Lost = false;
    boolean player2Lost = false;

    // Sounds for bullets of both players
    URL url = null;
    static AudioClip bullet;
    // Sound if one of the tank explodes
    URL blastSound = null;
    static AudioClip blast;
    // Sound for blast collision with tank
    URL collision = null;
    static AudioClip collideSound;
    // Gameplay sound
    URL game = null;
    static AudioClip gameplay;

    ////////// PowerUp and Bonuses Coordinates///////////

    ///// Health Orbs///////
    HealthOrb orb1;
    HealthOrb orb2;
    HealthOrb orb3;

    ///// Death Balls////////
    DeathBall ball1;
    DeathBall ball2;
    DeathBall ball3;

    public MazeOne() throws FileNotFoundException {

        // Making object of KeysManager
        KeysManager keys = new KeysManager();

        // Making object of BulletsManager

        setSize(1125, 1000);
        setVisible(true);
        addMouseMotionListener(this);
        addKeyListener(keys);
        setFocusable(true);

        // Adding Bullets timer
        // Speed for bullet
        bulletSpeed = new Timer(8, new Bullets());
        bulletSpeed2 = new Timer(8, new Bullets2());
//        Blast b = new Blast();
//        blastAnimation = new Timer(250, b);


        // Initiating dummy box 1 coordinates on screen
        boxX = 87;
        boxY = 77;

        // Initiating dummy box 2 coordinates on screen
        box2X = 993;
        box2Y = 83;

        // Adding Rectangles for collision detections of maze only here
        maze.add(new Rectangle(159, 133, (508 - 159), (161 - 133)));
        maze.add(new Rectangle(46, 40, (1077 - 46), (59 - 40)));
        maze.add(new Rectangle(719, 59, (743 - 719), (149 - 59)));
        maze.add(new Rectangle(46, 41, (66 - 46), (930 - 41)));
        maze.add(new Rectangle(1058, 58, (1078 - 1058), (930 - 58)));
        maze.add(new Rectangle(66, 909, (1076 - 66), (930 - 909)));
        maze.add(new Rectangle(276, 232, (513 - 276), (251 - 232)));
        maze.add(new Rectangle(275, 329, (400 - 275), (348 - 329)));
        maze.add(new Rectangle(159, 233, (182 - 159), (438 - 233)));
        maze.add(new Rectangle(170, 418, (400 - 170), (445 - 418)));
        maze.add(new Rectangle(492, 331, (519 - 492), (443 - 331)));
        maze.add(new Rectangle(156, 519, (404 - 156), (545 - 519)));
        maze.add(new Rectangle(156, 519, (186 - 156), (636 - 519)));
        maze.add(new Rectangle(499, 518, (738 - 499), (549 - 518)));
        maze.add(new Rectangle(611, 421, (738 - 611), (450 - 421)));
        maze.add(new Rectangle(612, 326, (740 - 612), (352 - 326)));
        maze.add(new Rectangle(609, 140, (630 - 609), (252 - 140)));
        maze.add(new Rectangle(630, 229, (856 - 630), (253 - 229)));
        maze.add(new Rectangle(832, 136, (853 - 832), (253 - 136)));
        maze.add(new Rectangle(833, 134, (960 - 833), (156 - 134)));
        maze.add(new Rectangle(949, 233, (1057 - 949), (259 - 233)));
        maze.add(new Rectangle(828, 327, (963 - 828), (354 - 327)));
        maze.add(new Rectangle(828, 328, (864 - 828), (513 - 328)));
        maze.add(new Rectangle(861, 512, (962 - 861), (544 - 512)));
        maze.add(new Rectangle(951, 422, (1057 - 951), (447 - 422)));
        maze.add(new Rectangle(66, 713, (293 - 66), (738 - 713)));
        maze.add(new Rectangle(265, 615, (293 - 265), (738 - 615)));
        maze.add(new Rectangle(265, 614, (854 - 265), (645 - 614)));
        maze.add(new Rectangle(940, 618, (972 - 940), (733 - 618)));
        maze.add(new Rectangle(338, 711, (515 - 338), (739 - 711)));
        maze.add(new Rectangle(607, 713, (854 - 607), (736 - 713)));
        maze.add(new Rectangle(709, 737, (755 - 709), (908 - 737)));
        maze.add(new Rectangle(157, 806, (627 - 157), (835 - 806)));
        maze.add(new Rectangle(834, 806, (964 - 834), (835 - 806)));

        // Initiating sound of Bullets///////////
        try {
            url = new URL("file:blastSound.wav");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        bullet = Applet.newAudioClip(url);
        /////////////////////////////////////////

        // Initiating sound for Blasts
        try {
            blastSound = new URL("file:blast.wav");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        blast = Applet.newAudioClip(blastSound);
        //////////////////////////////////////////

        try {
            collision = new URL("file:collide.wav");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        collideSound = Applet.newAudioClip(collision);

        // Initiating and playing Sound of gamePlay
        try {
            game = new URL("file:gameplay.wav");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        gameplay = Applet.newAudioClip(game);
        gameplay.loop();
        ///////////////////////////////////////

        // Initiating Health Orbs

        orb1 = new HealthOrb(204, 267, new ImageIcon("orb.png"));
        orb2 = new HealthOrb(745, 477, new ImageIcon(("orb.png")));
        orb3 = new HealthOrb(316, 669, new ImageIcon("orb.png"));

        // Initiating Death Balls

        ball1 = new DeathBall(541, 268, new ImageIcon("orb.png"));
        ball1 = new DeathBall(423, 467, new ImageIcon("orb.png"));
        ball1 = new DeathBall(789, 764, new ImageIcon("orb.png"));

    }

    // This method will check all the collisions in the game
    Rectangle Player1 = null;
    Rectangle Player2 = null;
    public void collisions() {

        // Making Rectangle of Player 1


        // Setting coordinates of rectangles according to moving direction
        if ( up || down )
            Player1 = new Rectangle(boxX, boxY, 35, 49);
        if ( left || right )
            Player1 = new Rectangle(boxX, boxY, 49, 35);

        // Checking collisions for Moving Player box 1 with Walls
        for (Rectangle r : maze) {
            if (r.intersects( Player1 )) {
                boxX = prevX;
                boxY = prevY;
                repaint();
            }
        }

        // Making Rectangle of Player 2


        // Setting coordinates of rectangles according to moving direction
        if ( up2 || down2 )
            Player2 = new Rectangle(box2X, box2Y, 35, 49);
        if ( left2 || right2 )
            Player2 = new Rectangle(box2X, box2Y, 49, 35);

        // Checking collisions for Moving Player box 2 with Walls
        for (Rectangle r : maze) {
            if (r.intersects( Player2 ) ) {
                box2X = prev2X;
                box2Y = prev2Y;
                repaint();
            }
        }

        // Making Rectangles of Bullets/Blasts
        Rectangle bullet1 = new Rectangle(bulletX, bulletY, 40, 44);
        Rectangle bullet2 = new Rectangle(bullet2X, bullet2Y, 40, 44);

        // Checking collisions of bullets with walls (Player 1)
        for (Rectangle r : maze) {
            if (r.intersects( bullet1 )) {
                bulletSpeed.stop();
                fired = false;
                collideSound.play();
                repaint();
            }
        }

        // Checking collisions of bullets with walls (Player 2)
        for (Rectangle r : maze) {
            if (r.intersects( bullet2 )) {
                bulletSpeed2.stop();
                fired2 = false;
                collideSound.play();
                repaint();
            }
        }

        // Checking if bullet of Player 1 hits Player 2
        if ( bullet1.intersects(Player2) ) {
            p2--;
            bulletSpeed.stop();
            fired = false;
            collideSound.play();
            repaint();

            // If second player runs out of health
            if ( p2 == 0) {
                blast.play();
                JOptionPane.showMessageDialog(null, "Player 1 Won!");
                p1 = 50;
                p2 = 50;
                player1Lost = true;
                boxX = 87;
                boxY = 77;
//                blastAnimation.start();
            }
            repaint();
        }

        // Checking if bullet of Player 2 hits Player 1
        if ( bullet2.intersects(Player1) ) {
            p1--;
            bulletSpeed2.stop();
            fired2 = false;
            collideSound.play();
            repaint();

            // If second player runs out of health
            if ( p1 == 0) {
                blast.play();
                JOptionPane.showMessageDialog(null, "Player 2 Won!");
                p1 = 50;
                p2 = 50;
                box2X = 993;
                box2Y = 83;
//                player2Lost = true;
            }
            repaint();
        }

        // If both bullets collide
        if ( bullet1.intersects( bullet2 ) ) {
            collideSound.play();
            repaint();
        }

        // Checking Collisions of Player 1 with Health Orbs

        // To make sure orb is used only once
        if (!orb1.used) {
            if (Player1.intersects(orb1.location)) {
                p1 += 10; // Increasing Health
                orb1.used = true;
                repaint();
            }
        }
        if (!orb2.used) {
            if (Player1.intersects(orb2.location)) {
                p1 += 10;
                orb2.used = true;
                repaint();
            }
        }
        if (!orb3.used) {
            if (Player1.intersects(orb3.location)) {
                p1 += 10;
                orb3.used = true;
                repaint();
            }
        }

        // Checking Collisions of Player 2 with Health Orbs
        if (!orb1.used) {
            if (Player2.intersects(orb1.location)) {
                p2 += 10; // Increasing Health
                orb1.used = true;
                repaint();
            }
        }
        if (!orb2.used) {
            if (Player2.intersects(orb2.location)) {
                p2 += 10;
                orb2.used = true;
                repaint();
            }
        }
        if (!orb3.used) {
            if (Player2.intersects(orb3.location)) {
                p2 += 10;
                orb3.used = true;
                repaint();
            }
        }

        // Checking Collisions of Player 1 with Death Balls

//        // To make sure ball is used only once
//        if (!ball1.used) {
//            if (Player1.intersects(ball1.location)) {
//                p1 -= 10; // Increasing Health
//                ball1.used = true;
//                repaint();
//            }
//        }
//        if (!ball2.used) {
//            if (Player1.intersects(ball2.location)) {
//                p1 -= 10; // Increasing Health
//                ball2.used = true;
//                repaint();
//            }
//        }
//        if (!ball3.used) {
//            if (Player1.intersects(ball3.location)) {
//                p1 -= 10; // Increasing Health
//                ball3.used = true;
//                repaint();
//            }
//        }
//
//        // Checking Collisions of Player 2 with Death Balls
//        if (!ball1.used) {
//            if (Player2.intersects(ball1.location)) {
//                p2 -= 10; // Increasing Health
//                ball1.used = true;
//                repaint();
//            }
//        }
//        if (!ball2.used) {
//            if (Player2.intersects(ball2.location)) {
//                p2 -= 10; // Increasing Health
//                ball2.used = true;
//                repaint();
//            }
//        }
//        if (!ball3.used) {
//            if (Player2.intersects(ball3.location)) {
//                p2 -= 10; // Increasing Health
//                ball3.used = true;
//                repaint();
//            }
//        }
    }

    // Player 1 Images
    // Drawing player1 on screen
    ImageIcon p1Up = new ImageIcon("Player1Up.png"); // Up direction
    Image player1Up = p1Up.getImage();

    ImageIcon plDown = new ImageIcon("Player1Down.png"); // Down direction
    Image player1Down = plDown.getImage();

    ImageIcon plRight = new ImageIcon("Player1Right.png"); // Right direction
    Image player1Right = plRight.getImage();

    ImageIcon plLeft = new ImageIcon("Player1Left.png"); // Left direction
    Image player1Left = plLeft.getImage();

    // Player 2 Images
    // Drawing player2 on screen
    ImageIcon p2Up = new ImageIcon("Player2Up.png"); // Up direction
    Image player2Up = p2Up.getImage();

    ImageIcon p2Down = new ImageIcon("Player2Down.png"); // Down direction
    Image player2Down = p2Down.getImage();

    ImageIcon p2Right = new ImageIcon("Player2Right.png"); // Right direction
    Image player2Right = p2Right.getImage();

    ImageIcon p2Left = new ImageIcon("Player2Left.png"); // Left direction
    Image player2Left = p2Left.getImage();

    // Setting image of specific direction of player 1

    Image player1 = null;

    // Setting image of specific direction of player 2

    Image player2 = null;

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        collisions();

        // Drawing the basic maze on the Panel
        ImageIcon mazeOneIC = new ImageIcon("kiill first stage.png");
        Image mazeOne = mazeOneIC.getImage();
        g.drawImage(mazeOne, 0, 0, null);

        // Drawing Player 1 on Screen
        if (up)
            player1 = player1Up;
        if (down)
            player1 = player1Down;
        if (left)
            player1 = player1Left;
        if (right)
            player1 = player1Right;

        g.drawImage(player1, boxX, boxY, null);

        // Drawing Player 2 on Screen
        if (up2)
            player2 = player2Up;
        if (down2)
            player2 = player2Down;
        if (left2)
            player2 = player2Left;
        if (right2)
            player2 = player2Right;

        g.drawImage(player2, box2X, box2Y, null);

        if ( fired ) {
            // Drawing Bullet (Player 1)
            ImageIcon bullet11 = new ImageIcon("Blast.png");
            Image Player1Blast = bullet11.getImage();
            g.drawImage(Player1Blast, bulletX, bulletY, null);
        }

        if ( fired2 ) {
            // Drawing Bullet (Player 1)
            ImageIcon bullet22 = new ImageIcon("Blast2.png");
            Image Player2Blast = bullet22.getImage();
            g.drawImage(Player2Blast, bullet2X, bullet2Y, null);
        }

        // Showing scores on screen
        g.setColor(Color.WHITE);
        g.setFont( new Font("Serif", Font.BOLD, 20));
        g.drawString("Player 1 Health Remaining: " + p1, 20, 20);
        g.drawString("Player 2 Health Remaining: " + p2, 800, 20);

        // Showing Coordinates on the screen
//        g.setColor(Color.WHITE);
//        g.drawString("X: " + xx + " Y: " + yy, 500, 20);

        // Setting prevX and prevY for collisions(For Player)
        prevX = boxX;
        prevY = boxY;

        prev2X = box2X;
        prev2Y = box2Y;

        // Setting coordinates of bullet that can be fired
        if (!fired) {

            // To arrange the bullet on box according to moving direction

            ///////////Player 1///////////

            if (bUp) {
                bulletX = boxX + 1;
                bulletY = boxY;
            }
            if (bDown) {
                bulletX = boxX + 1;
                bulletY = boxY + 15;
            }
            if (bLeft) {
                bulletX = boxX - 15;
                bulletY = boxY - 4;
            }
            if (bRight) {
                bulletX = boxX + 15;
                bulletY = boxY - 4;
            }
        }

        if (!fired2) {

            // To arrange the bullet on box according to moving direction

            /////////Player 2////////////

            if (b2Up) {
                bullet2X = box2X + 1;
                bullet2Y = box2Y;
            }
            if (b2Down) {
                bullet2X = box2X + 1;
                bullet2Y = box2Y + 15;
            }
            if (b2Left) {
                bullet2X = box2X - 15;
                bullet2Y = box2Y - 4;
            }
            if (b2Right) {
                bullet2X = box2X + 15;
                bullet2Y = box2Y - 4;
            }
        }

        // Drawing Health Orbs
        if (!orb1.used) {
            g.drawImage(orb1.image, orb1.X, orb1.Y, null);
        }
        if (!orb2.used) {
            g.drawImage(orb2.image, orb2.X, orb2.Y, null);
        }
        if (!orb3.used) {
            g.drawImage(orb3.image, orb3.X, orb3.Y, null);
        }

//        Drawing Death Balls
//        if (!ball1.used) {
//            g.drawImage(ball1.image, ball1.X, ball2.Y, null);
//        }
//        if (!ball2.used) {
//            g.drawImage(ball2.image, ball2.X, ball2.Y, null);
//        }
//        if (!ball3.used) {
//            g.drawImage(ball3.image, ball3.X, ball3.Y, null);
//        }

        // Drawing blast animation on screen
//        if ( player1Lost ) {
//            g.drawImage(blast, boxX, boxY, null);
//            count++;
//        }



    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        // For showing mouse coordinates on the Panel
        xx = e.getX();
        yy = e.getY();
        repaint();

    }

    /**
     * KeysManager class will handle the key strokes and relative
     * movement of player on the screen.
     */

    private class KeysManager implements KeyListener, ActionListener {

        // Speed of moving dummy box
        Timer speed = new Timer(2, this);

        @Override
        public void keyTyped(KeyEvent e) {


        }

        @Override
        public void keyPressed(KeyEvent e) {

            speed.start();

            // Storing state and which button is pressed
            int key = e.getKeyCode();

            //////////////////////////////////////////////
            //////////// FIRST PLAYER CONTROLS////////////
            //////////////////////////////////////////////

            // Moving the dummy box
            if (key == KeyEvent.VK_UP) {
                up = true;
                bUp = true; // All bullet booleans are manipulated to avoid bugs
                bDown = false; // because the state of moving bullet cannot
                bLeft = false; // be changed otherwise
                bRight = false;
            } else if (key == KeyEvent.VK_DOWN) {
                down = true;
                bUp = false;
                bDown = true;
                bLeft = false;
                bRight = false;
            } else if (key == KeyEvent.VK_RIGHT) {
                right = true;
                bUp = false;
                bDown = false;
                bLeft = false;
                bRight = true;
            } else if (key == KeyEvent.VK_LEFT) {
                left = true;
                bUp = false;
                bDown = false;
                bLeft = true;
                bRight = false;
            } else if (key == KeyEvent.VK_SPACE) {
                fired = true;
                bulletSpeed.start();
                // Playing sound
                bullet.play();
            }

            //////////////////////////////////////////////
            ///////////SECOND PLAYER CONTROLS/////////////
            //////////////////////////////////////////////

            if (key == KeyEvent.VK_W) {
                up2 = true;
                b2Up = true;
                b2Down = false;
                b2Right = false;
                b2Left = false;

            } else if (key == KeyEvent.VK_S) {
                down2 = true;
                b2Up = false;
                b2Down = true;
                b2Right = false;
                b2Left = false;
            } else if (key == KeyEvent.VK_D) {
                right2 = true;
                b2Up = false;
                b2Down = false;
                b2Right = true;
                b2Left = false;
            } else if (key == KeyEvent.VK_A) {
                left2 = true;
                b2Up = false;
                b2Down = false;
                b2Right = false;
                b2Left = true;
            } else if (key == KeyEvent.VK_CONTROL) {
                fired2 = true;
                bulletSpeed2.start();
                // Playing sound
                bullet.play();

            }
        }


        @Override
        public void keyReleased(KeyEvent e) {

            speed.stop();

            up = false;
            down = false;
            right = false;
            left = false;

            up2 = false;
            down2 = false;
            right2 = false;
            left2 = false;


        }

        @Override
        public void actionPerformed(ActionEvent e) {

            ////////////PLAYER !////////////

            if (up) {
                boxY -= 1;
                repaint();
            } else if (down) {
                boxY += 1;
                repaint();
            } else if (right) {
                boxX += 1;
                repaint();
            } else if (left) {
                boxX -= 1;
                repaint();
            }

            /////////PLAYER 2////////////

            if (up2) {
                box2Y -= 1;
                repaint();
            } else if (down2) {
                box2Y += 1;
                repaint();
            } else if (right2) {
                box2X += 1;
                repaint();
            } else if (left2) {
                box2X -= 1;
                repaint();
            }

        }
    }

    // For motion of Bullets
    private class Bullets implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (fired) {

                if (bUp) {
                    bulletY -= 5;
                    repaint();
                }
                if (bDown) {
                    bulletY += 5;
                    repaint();
                }
                if (bLeft) {
                    bulletX -= 5;
                    repaint();
                }
                if (bRight) {
                    bulletX += 5;
                    repaint();
                }
            }

        }
    }

    private class Bullets2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (fired2) {

                if (b2Up) {
                    bullet2Y -= 5;
                    repaint();
                }
                if (b2Down) {
                    bullet2Y += 5;
                    repaint();
                }
                if (b2Left) {
                    bullet2X -= 5;
                    repaint();
                }
                if (b2Right) {
                    bullet2X += 5;
                    repaint();
                }
            }
        }
    }
}

