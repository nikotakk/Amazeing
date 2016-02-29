import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
/**
 * Created by nigel on 2/26/16.
 */
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

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            dx = -1;
            System.out.println("Left or A pressed.");
        }

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            dx = 1;
            System.out.println("Right or D pressed.");
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            dy = -1;
            System.out.println("Up or W pressed.");
        }

        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            dy = 1;
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
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            dy = 0;
        }
    }
}
