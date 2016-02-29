

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
    public Map getMap() {
        return map;
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

        @Override
        public void keyReleased(KeyEvent e) {
            ball.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            ball.keyPressed(e);
        }
    }
}