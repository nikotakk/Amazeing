

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * Created by nigel on 2/26/16.
 */

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Map map;
    private Ball ball;
    private final int DELAY = 10;

    public Board() {

        initBoard();
    }
    // Initializing the gameboard.
    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);

        map = new Map();
        ball = new Ball();
        timer = new Timer(DELAY, this);
        timer.start();

    }

    //Painting components.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
        drawBall(g);

        Toolkit.getDefaultToolkit().sync();
    }
    // Drawing the balls graphics.
    private void drawBall(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(ball.getImage(), ball.getX() * 25, ball.getY() * 25, this);
    }
    private void drawMap(Graphics g) {

        for (int y = 0; y < 16; y++) {
            for (int x = 0; x < 16; x++) {
                if (map.getMap(x,y).equals("g")) {
                    g.drawImage(map.getGround(), x * 25, y * 25, null);
                }
                if (map.getMap(x,y).equals("w")) {
                    g.drawImage(map.getWall(), x * 25, y * 25, null);
                }
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                if ( !map.getMap( ball.getX() -1, ball.getY() ).equals("w") ) {
                    ball.move(-1, 0);
                }
                System.out.println("Left or A pressed.");
            }

            if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                if ( !map.getMap( ball.getX() +1, ball.getY() ).equals("w") ) {
                    ball.move(1, 0);
                }
                System.out.println("Right or D pressed.");
            }

            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
                if ( !map.getMap( ball.getX(), ball.getY() -1 ).equals("w") ) {
                    ball.move(0, -1);
                }
                System.out.println("Up or W pressed.");
            }

            if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
                if ( !map.getMap( ball.getX(), ball.getY() +1 ).equals("w") ) {
                    ball.move(0, 1);
                }
                System.out.println("Down or S pressed.");
            }
            if (key == KeyEvent.VK_ESCAPE) {
                System.out.println("Esc pressed.");
                System.exit(1);
            }
        }

        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                ball.move(0,0);
            }

            if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                ball.move(0,0);
            }

            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
                ball.move(0,0);
            }

            if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
                ball.move(0,0);
            }
        }
    }
}