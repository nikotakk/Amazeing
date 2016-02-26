import java.awt.EventQueue;
import javax.swing.JFrame;


public class Amazeing extends JFrame {

    public Amazeing() {

        initUI();
    }
    // Initializing the UI.
    private void initUI() {

        add(new Board());

        setSize(600, 600);
        setResizable(false);
        setTitle("Amazeing");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    // Main.
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                Amazeing game = new Amazeing();
                game.setVisible(true);
            }
        });
    }
}