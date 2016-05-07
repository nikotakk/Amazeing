

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;
import javax.swing.*;
import org.apache.commons.lang3.time.*;

/**
 * Created by nigel on 2/26/16.
 */

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Map map;
    private Ball ball;
    private StopWatch watch;
    private final int DELAY = 10;
    private boolean inGame = false;
    private boolean paused = false;
    private int blockSize = 25;
    private Image instructions;
    private Image pause;



    public void print(String text) {
        System.out.println(text);
    }

    public Board() {

        initBoard();
    }
    // Initializing the gameboard.
    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);

        initVariables();
    }
    /* Initializing the variables. */
    private void initVariables() {

        inGame = false;
        paused = false;
        map = new Map();
        ball = new Ball();
        timer = new Timer(DELAY, this);
        timer.start();
        watch = new StopWatch();
    }

    public long getTime(String secondOrMillis) {
        if (Objects.equals(secondOrMillis, "seconds")) {
            return watch.getTime()/1000;
        } else {
            return watch.getTime()/10;
        }
    }

    public void setInGame(boolean value) {
        inGame = value;
    }

    // Painting components.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.requestFocusInWindow();
        if (inGame) {
            if (watch.isSuspended()) {
                watch.resume();
            }
            drawMap(g);
            print("Drawing map");
            drawBall(g);
            print("Drawing ball");

            Toolkit.getDefaultToolkit().sync();
            if (paused) {
                watch.suspend();
                showScreen(g, true);
            }
        }
        else {
            showScreen(g, false);
        }
    }

    // Drawing instructions or pause screen.
    private void showScreen(Graphics g, boolean paused) {
        ImageIcon instr = new ImageIcon("src/resources/instructions.png");
        ImageIcon ps = new ImageIcon("src/resources/pause.png");
        pause = ps.getImage();
        instructions = instr.getImage();

        if (paused) {
            g.drawImage(pause,0,100,this);
            timer.stop();
            print("Drawing 'Paused'");
        } else {
            g.drawImage(instructions,0,0,this);
        }

    }

    // Drawing the balls graphics.
    private void drawBall(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(ball.getImage(), ball.getX() * blockSize, ball.getY() * blockSize, this);
    }
    // Drawing the maps graphics.
    private void drawMap(Graphics g) {

        for (int y = 0; y < 16; y++) {
            for (int x = 0; x < 16; x++) {
                if (map.getMap(x,y).equals("g")) {
                    g.drawImage(map.getGround(), x * blockSize, y * blockSize, null);
                }
                if (map.getMap(x,y).equals("w")) {
                    g.drawImage(map.getWall(), x * blockSize, y * blockSize, null);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    //Using keyboard, moving and collision checks.
    public class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            print("Input given:");
            int key = e.getKeyCode();

            if (inGame) {
                if ( (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && timer.isRunning() ) {
                    if (!map.getMap(ball.getX() - 1, ball.getY()).equals("w")) {
                        ball.move(-1, 0);
                    }
                    System.out.println("Left or A pressed.");
                }

                if ( (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && timer.isRunning() ) {
                    if (!map.getMap(ball.getX() + 1, ball.getY()).equals("w")) {
                        ball.move(1, 0);
                    }
                    System.out.println("Right or D pressed.");
                }

                if ( (key == KeyEvent.VK_UP || key == KeyEvent.VK_W ) && timer.isRunning() ) {
                    if (!map.getMap(ball.getX(), ball.getY() - 1).equals("w")) {
                        ball.move(0, -1);
                    }
                    System.out.println("Up or W pressed.");
                }

                if ( (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && timer.isRunning() ) {
                    if (!map.getMap(ball.getX(), ball.getY() + 1).equals("w")) {
                        ball.move(0, 1);
                    }
                    System.out.println("Down or S pressed.");
                }
                if (key == KeyEvent.VK_ESCAPE) {
                    System.out.println("Esc pressed.");
                    if (timer.isRunning()) {
                        paused = true;
                        print("Setting 'paused' to True");
                    } else {
                        timer.start();
                        paused = false;
                        print("Setting 'paused' to False");
                    }
                }
            } else {
                if (key == KeyEvent.VK_S || key == KeyEvent.VK_ESCAPE) {
                    inGame = true;
                    watch.start();
                    System.out.println("S pressed");
                }
            }

        }

    /*    public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A
                    || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S
                    || key == KeyEvent.VK_UP || key == KeyEvent.VK_W
                    || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                ball.move(0,0);
            }

        }*/
    }
}