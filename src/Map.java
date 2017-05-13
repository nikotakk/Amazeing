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
    private Image ground, wall, goal;
    private int currentMap;

    public Map(int mapNumber) {
        ImageIcon image = new ImageIcon("src/resources/ground.png");
        ground = image.getImage();
        image = new ImageIcon("src/resources/wall.png");
        wall = image.getImage();
        image = new ImageIcon("src/resources/goal.png");
        goal = image.getImage();
        currentMap = mapNumber;

        openFile(currentMap);
        readFile();
        closeFile();
    }

    /* Returns the current map number. */
    int getCurrentMap() {
        return currentMap;
    }

    /* Returns the tile at the coordinates given as parameters. */
    String getMap(int x, int y) {
        return Map[y].substring(x, x+1);
    }

    /* Returns the image of ground. */
    Image getGround() {
        return ground;
    }

    /* Returns the image of wall. */
    Image getWall() {
        return wall;
    }

    /* Returns the image of the goal. */
    Image getGoal() { return goal; }

    /* Opens the file at src/resources/ with the name of Map#.txt, where # is the number of map wanted to open. */
    private void openFile(int number) {
        try {
            scanner = new Scanner(new File("src/resources/Map"+number+".txt"));
        } catch(Exception e) {
            System.out.println("Error while loading the map.");
        }
    }

    /* Reads the file that has been opened and constructs the map variable from it. */
    private void readFile() {
        while(scanner.hasNext()) {
            for (int i = 0; i <16; i++) {
                Map[i] = scanner.next();
            }
        }
    }

    /* Closes the opened file. */
    private void closeFile() {
        scanner.close();
    }

}
