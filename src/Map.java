import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.io.*;
/**
 * Created by nigel on 2/29/16.
 */
public class Map {
    private Scanner scanner;
    private String Map[] = new String[16];
    private Image ground, wall;

    public Map() {
        ImageIcon image = new ImageIcon("src/resources/ground.png");
        ground = image.getImage();
        image = new ImageIcon("src/resources/wall.png");
        wall = image.getImage();

        openFile();
        readFile();
        closeFile();

    }

    public String getMap(int x, int y) {
        return Map[y].substring(x, x+1);
    }

    public Image getGround() {
        return ground;
    }
    public Image getWall() {
        return wall;
    }

    private void openFile() {
        try {
            scanner = new Scanner(new File("src/resources/Map.txt"));
        } catch(Exception e) {
            System.out.println("Error while loading the map");
        }
    }
    private void readFile() {
        while(scanner.hasNext()) {
            for (int i = 0; i <16; i++) {
                Map[i] = scanner.next();
            }
        }
    }
    private void closeFile() {
        scanner.close();
    }

}
