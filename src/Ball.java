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
    private String color;

    public Ball(String ballColor) {

        initBall(ballColor);
    }
    // Initialize ball.
    private void initBall(String ballColor) {
        color = ballColor;
        image = new ImageIcon("src/resources/pallo"+color+".png").getImage();
        tileX = 1;
        tileY = 1;
    }

    // Move the ball.
    public void move(int dx, int dy ) {
        tileX += dx;
        tileY += dy;
    }

    /* Returns the int value of balls x coordinate. */
    public int getX() {
        return tileX;
    }

    /* Returns the int value of balls y coordinate. */
    public int getY() {
        return tileY;
    }

    /* Returns the image of the ball. */
    Image getImage() {
        return image;
    }

    /* Returns the color of the ball. */
    String getColor() { return color; }


}
