import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
/**
 * Created by nigel on 2/26/16.
 */
public class Ball {

    private int tileX;
    private int tileY;
    private Image image;

    public Ball() {

        initBall();
    }
    // Initialize ball.
    private void initBall() {

        ImageIcon ii = new ImageIcon("src/resources/ball.png");
        image = ii.getImage();
        tileX = 1;
        tileY = 1;
    }

    // Move the ball.
    private void move(int dx, int dy ) {
        tileX += dx;
        tileY += dy;
    }

    public int getX() {
        return tileX;
    }

    public int getY() {
        return tileY;
    }

    public Image getImage() {
        return image;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            move(-1, 0);
            System.out.println("Left or A pressed.");
        }

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            move(1, 0);
            System.out.println("Right or D pressed.");
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            move(0, -1);
            System.out.println("Up or W pressed.");
        }

        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            move(0, 1);
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
            tileX = getX();
        }

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            tileX = getX();
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            tileY = getY();
        }

        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            tileY = getY();
        }
    }
}
