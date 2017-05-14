

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
    private boolean inGame = false;
    private boolean paused = false;
    private int blockSize = 25;
    private volatile boolean goalReached = false;
    private double[] highscore = {0.0, 0.0, 0.0, 0.0};


    Board() {
        initBoard();
    }

    // Initializing the gameboard.
    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);

        initVariables(1, "punainen", false);
    }
    /* Initializing the variables used in Board class.
      * Used at start and when the game needs to read new values, such as a new map or ball color.
      * int mapNumber tells which map to initialize, ballColor tells what color ball to use
      * boolean gameStarted tells if a prior game has been initialized before changing maps.
      * */
    void initVariables(int mapNumber, String ballColor, boolean gameStarted) {
        //System.out.format("InitVariables: inGame = "+ inGame + ", now setting it to " + gameStarted + "\n");
        inGame = gameStarted;
        paused = false;
        map = new Map(mapNumber);
        ball = new Ball(ballColor);
        timer = new Timer(10, this);
        timer.start();
        watch = new StopWatch();
       if (inGame) {
           watch.start();
       }
    }

    /* Resets current map to starting point. */
    void resetMap(){
        initVariables(map.getCurrentMap(), ball.getColor(), true);

        if (watch.isStopped()) {
            watch.start();
        }
    }

    /* Returns how much time has elapsed since beginning current map. */
    double getTime() {
        return ((watch.getTime()/1000 + (double)(watch.getTime()/10 - watch.getTime()/1000)) / 100);
    }

    /* Returns the number of map currently being played. */
    int getMap() {
        return map.getCurrentMap();
    }

    /* Returns the value of highscore according to map number given as parameter. */
    double getHighscore(int map) {
        return highscore[map-1];
    }

    /* Returns the value of goalReached. */
    boolean getGoalReached() {
        return goalReached;
    }

    /* Sets goalReached to false. */
    void setGoalReachedFalse() {
        goalReached = false;
    }

    /* Paint map and ball. Also checks if goal has been reached. If so, call endGame(). */
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
                endGame();
            }
        }
        else {
                showScreen(g, false);
        }
    }

    // Drawing instructions or pause screen .
    private void showScreen(Graphics g, boolean paused) {
        Image pause = new ImageIcon("src/resources/pause.png").getImage();
        Image instructions = new ImageIcon("src/resources/instructions.png").getImage();

        if (paused) {
            g.drawImage(pause,0,100,this);
            timer.stop();
            //System.out.println("Drawing 'Paused'");
        }
        else {
            g.drawImage(instructions,0,0,this);
        }
    }

    /* Ends the game and updates highscore if its lower than current highscore. Sets goalReached = true.*/
    private void endGame() {
        inGame = false;
        goalReached = true;
        //System.out.println("endGame(Board): setting inGame to false.");
        if ( highscore[getMap()-1] == 0.0 ) {
            highscore[getMap()-1] = getTime();
        }
        else if ( getTime() < highscore[getMap()-1]) {
            highscore[getMap()-1] = getTime();
        }

        if (watch.isStarted()) {
            watch.stop();
            //System.out.format("endGame(Board): watch.isStarted() is true so stopping watch.\n");
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

    /* When an action event has happened, repaint. */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    //Moves the ball using keyboard. Also has collision checks.
    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (inGame) {
                if ( (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && timer.isRunning() ) {
                    if (!map.getMap(ball.getX() - 1, ball.getY()).equals("w")) {
                        ball.move(-1, 0);
                    }
                }

                if ( (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && timer.isRunning() ) {
                    if (!map.getMap(ball.getX() + 1, ball.getY()).equals("w")) {
                        ball.move(1, 0);
                    }
                }

                if ( (key == KeyEvent.VK_UP || key == KeyEvent.VK_W ) && timer.isRunning() ) {
                    if (!map.getMap(ball.getX(), ball.getY() - 1).equals("w")) {
                        ball.move(0, -1);
                    }
                }

                if ( (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && timer.isRunning() ) {
                    if (!map.getMap(ball.getX(), ball.getY() + 1).equals("w")) {
                        ball.move(0, 1);
                    }
                }

                if (key == KeyEvent.VK_ESCAPE) {
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
                //System.out.format("keyPressed: Setting inGame to True. Watch.isStarted = false");
                inGame = true;
                if (!watch.isStarted()) {
                    watch.reset();
                    watch.start();
                    //System.out.print(", starting the Watch.\n");
                    initVariables(getMap(), ball.getColor(), true);
                }
            }
        }
    }
}