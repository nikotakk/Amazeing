

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;
import javax.swing.*;
import org.apache.commons.lang3.time.*;

/**
 * Created by Niko Takkinen on 2/26/16.
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
    private boolean goalReached = false;
    private double[] highscore = {0.0, 0.0, 0.0, 0.0};


    Board() {
        initBoard();
    }

    // Initializing the gameboard.
    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);

        initVariables(1, "red", false);
    }
    /* Initializing the variables used in Board class.
     * Also used when changing the map.
     * int mapNumber tells which map to initialize, boolean gameStarted tells if a prior game has been initialized before changing maps.*/
    public void initVariables(int mapNumber, String ballColor, boolean gameStarted) {

        inGame = gameStarted;
        paused = false;
        map = new Map(mapNumber);
        ball = new Ball(ballColor);
        timer = new Timer(DELAY, this);
        timer.start();
        watch = new StopWatch();
    }

    /* Resets current map to starting point. */
    void resetMap(){
        initVariables(map.getCurrentMap(), ball.getColor(), true);
        watch.start();
    }

    /* Returns how much time has elapsed since beginning current map. */
    double getTime() {
        return ((watch.getTime()/1000 + (double)(watch.getTime()/10 - watch.getTime()/1000)) / 100);
    }

    /* Returns the number of which map is currently going on. */
    int getMap() {
        return map.getCurrentMap();
    }

    double getHighscore(int map) {
        return highscore[map];
    }

    // Painting components.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.requestFocusInWindow();

        /* If already in game, keep drawing the game. If not, then show starting screen */
        if (inGame) {
            if (watch.isSuspended()) {
                watch.resume();
            }

            drawMap(g);
            drawBall(g);
            Toolkit.getDefaultToolkit().sync();

            /* If paused, suspend the stopwatch and show pause screen. */
            if (paused) {
                watch.suspend();
                showScreen(g, true);
            }

            /* Check if goal is reached and end the game if so. */
            if (ball.getX() == 14 && ball.getY() == 14) {
                goalReached = true;
                System.out.println("GOAL REACHED");
                endGame();
            }
        }
        else {
            showScreen(g, false);
        }
    }

    private void endGame() {
        if ( getTime() > highscore[getMap()]) {
            highscore[getMap()] = getTime();
        }
        System.out.format("Map "+getMap()+" highscore = "+highscore[getMap()]);
        watch.suspend();
        inGame = false;
        // notifyObservers?
    }

    // Drawing instructions or pause screen.
    private void showScreen(Graphics g, boolean paused) {
        ImageIcon instr = new ImageIcon("src/resources/instructions.png");
        ImageIcon ps = new ImageIcon("src/resources/pause.png");
        Image pause = ps.getImage();
        Image instructions = instr.getImage();
        Image start = new ImageIcon("src/resources/start.png").getImage();

        if (paused) {
            g.drawImage(pause,0,100,this);
            timer.stop();
            System.out.println("Drawing 'Paused'");
        }
        else {
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
                if (map.getMap(x,y).equals("f")) {
                    g.drawImage(map.getGoal(), x * blockSize, y * blockSize, null);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    //Using keyboard, moving and collision checks.
    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            System.out.println("Input given: ");
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
                        System.out.println("Setting 'paused' to True");
                    } else {
                        timer.start();
                        paused = false;
                        System.out.println("Setting 'paused' to False");
                    }
                }
            } else {
                inGame = true;
                watch.start();
                System.out.println("Button pressed");

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