
import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
/**
 * Created by nigel on 2/26/16.
 */

public class Amazeing extends JFrame {

    private int movebarHeight = 100;
    private int mazeSize = 25*16;
    private Board board;
    private JPanel info;
    private JLabel time;


    public Amazeing() {

        initUI();
        watch();
    }
    // Initializing the UI.
    private void initUI() {

        initMenuBar();
        time = new JLabel();

        /* Testing JLabel on the south side of the window. */
        JLabel statusBar = new JLabel();
        statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS));
        statusBar.setPreferredSize(new Dimension(mazeSize, movebarHeight));
        statusBar.setBackground(Color.LIGHT_GRAY);
        statusBar.setBorder(LineBorder.createBlackLineBorder());

        statusBar.add(time);
        statusBar.add(Box.createHorizontalGlue());

        /* Creating a box of buttons into the statusBar. */
        JPanel buttonsBox = new JPanel();
        buttonsBox.setLayout(new GridBagLayout());
//        buttonsBox.setBorder(LineBorder.createBlackLineBorder());
//        buttonsBox.setPreferredSize(new Dimension(50,movebarHeight));

        JButton up = new JButton("^");
        JButton left = new JButton("<");
        JButton down = new JButton("v");
        JButton right = new JButton(">");
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonsBox.add(up, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonsBox.add(down, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonsBox.add(left, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        buttonsBox.add(right, gbc);
//TODO: Move the ball when clicking the buttons.
        statusBar.add(buttonsBox);

        add(statusBar, BorderLayout.SOUTH);

        /* Adding a board to the center of the window. */
        board = new Board();
        add(board, BorderLayout.CENTER);

        setSize(mazeSize, mazeSize+movebarHeight+21);
        setResizable(false);
        setTitle("Amazeing");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void watch () {
        Thread watch = new Thread() {

            public void run() {
                while (true) {
                    long milliSeconds = board.getTime("millis") - board.getTime("seconds")*100;
                    time.setText("Time:" + board.getTime("seconds") + "." + milliSeconds);
                }
            }
        };

        watch.start();
    }

    private void initMenuBar() {

        /* Creating a menuBar, to which menus File and Help are added. */
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        file.setMnemonic(KeyEvent.VK_F);
        help.setMnemonic(KeyEvent.VK_H);

        /*Creating Reset menuItem. */
        JMenuItem menuReset = new JMenuItem("Reset the level");
        menuReset.setMnemonic(KeyEvent.VK_R);
        menuReset.setToolTipText("Reset the ongoing level");
        menuReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
// TODO: reset the map.
            }
        });
        /*Creating Exit menuItem. */
        JMenuItem menuExit= new JMenuItem("Exit");
        menuExit.setMnemonic(KeyEvent.VK_E);
        menuExit.setToolTipText("Exit application");
        menuExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("E pressed.");
                System.exit(0);
            }
        });

        /*Creating Instructions menuItem. */
        JMenuItem menuInstructions = new JMenuItem("Show instructions");
        menuExit.setMnemonic(KeyEvent.VK_E);
        menuExit.setToolTipText("Show the instructions to play the game");
        menuExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
// TODO: show instructions.
            }
        });

        /* Addin Exit and Reset into File, Instructions into Help, and adding them all into menuBar. */
        file.add(menuReset);
        file.add(menuExit);
        help.add(menuInstructions);
        menuBar.add(file);
        menuBar.add(help);
        setJMenuBar(menuBar);

    }

    /* Main. */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            Amazeing game = new Amazeing();
            game.setVisible(true);
        });
    }
}