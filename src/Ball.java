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
        ImageIcon ii = new ImageIcon("src/resources/ball"+color+".png");
        image = ii.getImage();
        tileX = 1;
        tileY = 1;

    }

    // Move the ball.
    public void move(int dx, int dy ) {
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

    public String getColor() { return color; }


}
