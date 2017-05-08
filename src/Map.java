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

    public int getCurrentMap() {
        return currentMap;
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
    public Image getGoal() { return goal; }

    private void openFile(int number) {
        String pathToMap = "src/resources/Map" + number + ".txt";
        try {
            scanner = new Scanner(new File(pathToMap));
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
