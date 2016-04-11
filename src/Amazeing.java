
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

    private int movebarHeight = 70;
    private int mazeSize = 25*16;
    private Board board;
    private JPanel info;
    private JLabel time;


    public Amazeing() {

        initMenuBar();
        initUI();
        watch();
    }
    /* Initializing the menuBar. */
    private void initMenuBar() {

        /* Creating a menuBar, to which menus Game and Help are added. */
        JMenuBar menuBar = new JMenuBar();
        JMenu game = new JMenu("Game");
        JMenu help = new JMenu("Help");
        game.setMnemonic(KeyEvent.VK_A);
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

        /* Adding Exit and Reset into Game, Instructions into Help, and adding them all into menuBar. */
        game.add(menuReset);
        game.addSeparator();
        game.add(menuExit);
        help.add(menuInstructions);
        menuBar.add(game);
        menuBar.add(help);
        setJMenuBar(menuBar);

    }

    /* Initializing the UI. */
    private void initUI() {

        /* statusBar JLabel on the south side of the window. */
        JLabel statusBar = new JLabel();
        statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS));
        statusBar.setPreferredSize(new Dimension(mazeSize, movebarHeight));
        statusBar.setBackground(Color.LIGHT_GRAY);
        statusBar.setBorder(LineBorder.createBlackLineBorder());

        /* Creating a box of buttons to be put into the statusBar. */
        JPanel buttonsBox = new JPanel();
        buttonsBox.setLayout(new GridBagLayout());
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
//        buttonsBox.setBorder(LineBorder.createBlackLineBorder());

        /* Adding best time, current time and current lvl into the statusBar. */
        JPanel infoBox = new JPanel();
        infoBox.setAlignmentX(1f);
        infoBox.setLayout(new BoxLayout(infoBox, BoxLayout.Y_AXIS));
        infoBox.setPreferredSize(new Dimension(150,50));
//        infoBox.setBorder(LineBorder.createGrayLineBorder());

        infoBox.add(new JLabel("Level: 1"));
        infoBox.add(new JLabel("Highscore: "));

        time = new JLabel();
        time.setPreferredSize(new Dimension(75,25));
//        time.setBorder(LineBorder.createBlackLineBorder());

        statusBar.add(Box.createRigidArea(new Dimension(5, 0)));
        statusBar.add(infoBox);
        statusBar.add(time);
        statusBar.add(buttonsBox);
//        statusBar.add(Box.createHorizontalGlue());
//        statusBar.add(time);
//        statusBar.add(Box.createHorizontalGlue());
//        statusBar.add(Box.createRigidArea(new Dimension(110, 0)));

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

    /* The stopwatch to be used in the game. */
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

    /* Main. */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            Amazeing game = new Amazeing();
            game.setVisible(true);
        });
    }
}