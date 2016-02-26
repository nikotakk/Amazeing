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


public class Board extends JPanel implements ActionListener {

    private Timer timer;
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

        ball = new Ball();

        timer = new Timer(DELAY, this);
        timer.start();

    }

    //Painting components.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }
    // Drawing the graphics.
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ball.move();
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