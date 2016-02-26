import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Ball {

    private int dx;
    private int dy;
    private int x;
    private int y;
    private Image image;

    public Ball() {

        initBall();
    }
    // Initialize ball.
    private void initBall() {

        ImageIcon ii = new ImageIcon("src/resources/ball.png");
        image = ii.getImage();
        x = 40;
        y = 60;
    }

    // Move the ball.
    public void move() {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
            System.out.println("Left pressed.");
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
            System.out.println("Right pressed.");
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
            System.out.println("Up pressed.");
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
            System.out.println("Down pressed.");
        }
        if (key == KeyEvent.VK_ESCAPE) {
            System.out.println("Esc pressed.");
            System.exit(1);
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}
