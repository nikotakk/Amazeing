
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
/**
 * Created by nigel on 2/26/16.
 */

public class Amazeing extends JFrame {

    private int infobarWidth = 130;
    private int movebarHeight = 130;
    private int mazeSize = 25*16;
    private Board board;
    private JLabel info;


    public Amazeing() {

        initUI();
        watch();
    }
    // Initializing the UI.
    private void initUI() {

        /* Creating a menubar, to which menus are added. */
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        file.setMnemonic(KeyEvent.VK_F);
        help.setMnemonic(KeyEvent.VK_H);

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
        JMenuItem menuInstructions = new JMenuItem("Show instructions");
        menuExit.setMnemonic(KeyEvent.VK_I);
        menuExit.setToolTipText("Show the instructions to play the game");
        menuExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
// TODO: show instructions.
            }
        });

        file.add(menuExit);
        file.add(menuReset);
        help.add(menuInstructions);
        menubar.add(file);
        menubar.add(help);
        setJMenuBar(menubar);

        board = new Board();


        /* Testing a info JLabel on the right side of the window. */
        info = new JLabel();




        /* Testing JLabel on the south side of the window. */
        JLabel statusbar = new JLabel();
        statusbar.setPreferredSize(new Dimension(-1, movebarHeight));
        /* statusbar.setBorder(LineBorder.createGrayLineBorder()); */

        add(info, BorderLayout.EAST);
        add(statusbar, BorderLayout.SOUTH);

        /* Adding a board to the center of the window. */
        add(board, BorderLayout.CENTER);

        setSize(mazeSize+infobarWidth+4, mazeSize+movebarHeight+21);
        setResizable(false);
        setTitle("Amazeing");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void watch () {
        Thread watch = new Thread() {

            public void run() {
                while (true) {
                    info.setText("Time:" + board.getTime());
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