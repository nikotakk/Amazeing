import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.GroupLayout.Alignment.CENTER;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

/**
 * Created by Niko Takkinen on 2/26/16.
 */

public class Amazeing extends JFrame {

    private int movebarHeight = 70;
    private int mazeSize = 25*16;
    private Board board;
    private JLabel time;

    private Amazeing() {

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
        JMenuItem menuReset = new JMenuItem("Reset the map");
        menuReset.setMnemonic(KeyEvent.VK_R);
        menuReset.setToolTipText("Reset the ongoing map");
        menuReset.addActionListener(event -> board.resetMap());

        JMenuItem menuChooseMap = new JMenuItem("Choose map");
        menuChooseMap.setMnemonic(KeyEvent.VK_R);
        menuChooseMap.setToolTipText("Choose which map to play.");
        menuChooseMap.addActionListener(event -> showMap());

        /*Creating Exit menuItem. */
        JMenuItem menuExit= new JMenuItem("Exit");
        menuExit.setMnemonic(KeyEvent.VK_E);
        menuExit.setToolTipText("Exit application");
        menuExit.addActionListener(event -> System.exit(0));

        /* Creating Instructions menuItem. */
        JMenuItem menuInstructions = new JMenuItem("Show instructions");
        menuInstructions.setMnemonic(KeyEvent.VK_I);
        menuInstructions.setToolTipText("Show the instructions to play the game");
        menuInstructions.addActionListener(event -> showHelp());

        /* Creating highScore menuItem. */
        JMenuItem menuHighScore = new JMenuItem("Highscores");
        menuHighScore.setMnemonic(KeyEvent.VK_H);
        menuHighScore.setToolTipText("Show the highscores of the maps.");
        menuHighScore.addActionListener(event -> {
        // TODO: show highscore according to which map you're in.
        });

          /* Creating Settings menuItem. */
        JMenuItem menuSettings = new JMenuItem("Settings");
        menuSettings.setMnemonic(KeyEvent.VK_S);
        menuSettings.setToolTipText("Show the settings.");
        menuSettings.addActionListener(event -> showSettings());

        /* Creating Solution menuItem. */
        JMenuItem menuSolution = new JMenuItem("Solution");
        menuSolution.setMnemonic(KeyEvent.VK_O);
        menuSolution.setToolTipText("Show the solution to this map.");
        menuSolution.addActionListener(event -> {
            showSolution();
        });

        /* Adding Reset, Choose map, Highscore, Solution, Settings and Exit into Game, Instructions into Help, and adding them all into menuBar. */
        game.add(menuReset);
        game.add(menuChooseMap);
        game.addSeparator();
        game.add(menuHighScore);
        game.add(menuSolution);
        game.add(menuSettings);
        game.addSeparator();
        game.add(menuExit);
        help.add(menuInstructions);
        menuBar.add(game);
        menuBar.add(help);
        setJMenuBar(menuBar);
    }

    private void showSolution() {
        SolutionDialog solution = new SolutionDialog(this);
        solution.setVisible(true);
    }

    private void showSettings() {
        SettingsDialog settings = new SettingsDialog(this);
        settings.setVisible(true);
    }

    private void showHelp() {
        HelpDialog help = new HelpDialog(this);
        help.setVisible(true);
    }

    private void showMap() {
        MapDialog map = new MapDialog(this);
        map.setVisible(true);
    }

    /* Initializing the UI. */
    private void initUI() {

        /* Creating a statusBar JLabel on the south side of the window. */
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

        up.addActionListener(event -> System.out.println("Up-key clicked."));
        left.addActionListener(event -> System.out.println("Left-key clicked."));
        down.addActionListener(event -> System.out.println("Down-key clicked."));
        right.addActionListener(event -> System.out.println("Right-key clicked."));

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

        /* Adding best time, current time and current lvl into the statusBar. */
        JPanel infoBox = new JPanel();
        infoBox.setAlignmentX(1f);
        infoBox.setLayout(new BoxLayout(infoBox, BoxLayout.Y_AXIS));
        infoBox.setPreferredSize(new Dimension(130,50));

        infoBox.add(new JLabel("Map: 1"));
        infoBox.add(new JLabel("Highscore: "));

        /* Creating time label */
        time = new JLabel();
        time.setPreferredSize(new Dimension(90,25));

        /* Adding infobox, time and buttonsbox into statusBar. */
        statusBar.add(Box.createRigidArea(new Dimension(5, 0)));
        statusBar.add(infoBox);
        statusBar.add(time);
        statusBar.add(buttonsBox);

        /* Adding statusbar to the south of the window. */
        add(statusBar, BorderLayout.SOUTH);

        /* Adding a board to the center of the window. */
        board = new Board();
        add(board, BorderLayout.CENTER);

        setMinimumSize(new Dimension(400,500));
        setSize(mazeSize+20, mazeSize+movebarHeight+70);

        setResizable(true);
        setTitle("Amazeing");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /* The stopwatch to be used in the game. */
    private void watch () {
        Thread watch = new Thread(() -> {
            while (true) {
                time.setText("Time:" + board.getTime());
            }
        });

        watch.start();
    }

    /* -------------  Main  -------------------------------------- */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            Amazeing game = new Amazeing();
            game.setVisible(true);
        });
    }

    private class SettingsDialog extends JDialog implements ItemListener {
        SettingsDialog(Frame parent) {
            super(parent);
            initSettingsDialog();
        }

        private void initSettingsDialog() {
            setTitle("Settings");
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocation(getParent().getLocation());
            setModalityType(ModalityType.APPLICATION_MODAL);

            JButton btn = new JButton("OK");
            btn.addActionListener(event -> dispose());

            JLabel color = new JLabel("Choose your balls color:");

            ButtonGroup buttons = new ButtonGroup();

            JRadioButton rb1 = new JRadioButton("Red", true);
            JRadioButton rb2 = new JRadioButton("Green");
            JRadioButton rb3 = new JRadioButton("Blue");
            JRadioButton rb4 = new JRadioButton("Yellow");
            JRadioButton rb5 = new JRadioButton("White");

            buttons.add(rb1);
            buttons.add(rb2);
            buttons.add(rb3);
            buttons.add(rb4);
            buttons.add(rb5);

            rb1.addItemListener(this);
            rb2.addItemListener(this);
            rb3.addItemListener(this);
            rb4.addItemListener(this);
            rb5.addItemListener(this);

            createLayout(color, rb1, rb2, rb3, rb4, rb5, btn);
            setSize(350, 300);

        }

        private void createLayout(JComponent... arg) {
            Container pane = getContentPane();
            GroupLayout gl = new GroupLayout(pane);
            pane.setLayout(gl);

            gl.setAutoCreateContainerGaps(true);
            gl.setHorizontalGroup(gl.createParallelGroup()
                    .addComponent(arg[0])
                    .addComponent(arg[1])
                    .addComponent(arg[2])
                    .addComponent(arg[3])
                    .addComponent(arg[4])
                    .addComponent(arg[5])
                    .addComponent(arg[6])
            );

            gl.setVerticalGroup(gl.createSequentialGroup()
                    .addComponent(arg[0])
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(arg[1])
                    .addComponent(arg[2])
                    .addComponent(arg[3])
                    .addComponent(arg[4])
                    .addComponent(arg[5])
                    .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(arg[6])
            );
        }

        @Override
        public void itemStateChanged(ItemEvent e) {

            int selection = e.getStateChange();
            if (selection == ItemEvent.SELECTED) {
                JRadioButton button = (JRadioButton) e.getSource();
                String text = button.getText();
                //TODO: Changing the balls color.
            }
        }
    }


    private class MapDialog extends JDialog implements ItemListener {

        private int chosenMap = 1;

        MapDialog(Frame parent) {
            super(parent);
            initMapDialog();
        }

        private void initMapDialog(){
            setTitle("Choosing the map");
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocation(getParent().getLocation());
            setModalityType(ModalityType.APPLICATION_MODAL);
            JButton btn = new JButton("OK");
            btn.addActionListener(event -> {
                board.initVariables(chosenMap, true);
                dispose();
            });

            JLabel map = new JLabel("Choose a map to play:");

            ButtonGroup buttons = new ButtonGroup();

            JRadioButton rb1 = new JRadioButton("Map 1", true);
            JRadioButton rb2 = new JRadioButton("Map 2");
            JRadioButton rb3 = new JRadioButton("Map 3");
            JRadioButton rb4 = new JRadioButton("Map 4");

            buttons.add(rb1);
            buttons.add(rb2);
            buttons.add(rb3);
            buttons.add(rb4);

            rb1.addItemListener(this);
            rb2.addItemListener(this);
            rb3.addItemListener(this);
            rb4.addItemListener(this);

            createLayout(map, rb1, rb2, rb3, rb4, btn);
            setSize(350, 300);
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            int selection = e.getStateChange();
            if (selection == ItemEvent.SELECTED) {
                JRadioButton button = (JRadioButton) e.getSource();
                chosenMap = Integer.parseInt(button.getText().substring(4, 5));
                //System.out.println(chosenMap);
            }
        }

        private void createLayout(JComponent... arg) {
            Container pane = getContentPane();
            GroupLayout gl = new GroupLayout(pane);
            pane.setLayout(gl);

            gl.setAutoCreateContainerGaps(true);
            gl.setHorizontalGroup(gl.createParallelGroup()
                    .addComponent(arg[0])
                    .addComponent(arg[1])
                    .addComponent(arg[2])
                    .addComponent(arg[3])
                    .addComponent(arg[4])
                    .addComponent(arg[5])
            );

            gl.setVerticalGroup(gl.createSequentialGroup()
                    .addComponent(arg[0])
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(arg[1])
                    .addComponent(arg[2])
                    .addComponent(arg[3])
                    .addComponent(arg[4])
                    .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(arg[5])
            );
        }
    }

    private class HelpDialog extends JDialog {

        HelpDialog(Frame parent) {
            super(parent);
            initHelpDialog();
        }

        private void initHelpDialog() {
            setTitle("Instructions");
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocation(getParent().getLocation());
            setModalityType(ModalityType.APPLICATION_MODAL);

            // Create a button and image.
            JButton btn = new JButton("OK");
            btn.addActionListener(event -> dispose());
            ImageIcon instr = new ImageIcon("src/resources/instructions.png");
            JLabel instructions = new JLabel(instr);

            createLayout(instructions, btn);
        }

        private void createLayout(JLabel pic, JButton btn) {
            Container pane = getContentPane();
            GroupLayout gl = new GroupLayout(pane);
            pane.setLayout(gl);

            gl.setAutoCreateContainerGaps(true);
            gl.setAutoCreateGaps(true);

            gl.setHorizontalGroup(gl.createParallelGroup(CENTER)
                    .addComponent(pic)
                    .addComponent(btn)
            );

            gl.setVerticalGroup(gl.createSequentialGroup()
                    .addComponent(pic)
                    .addGap(20)
                    .addComponent(btn)
                    .addGap(20)
            );
            pack();
        }
    }

    private class SolutionDialog extends JDialog {
        SolutionDialog(Frame parent) {
            super(parent);
            initSolutionDialog();
        }

        private void initSolutionDialog() {
            setTitle("Solution");
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocation(getParent().getLocation());
            setModalityType(ModalityType.APPLICATION_MODAL);

            // Create a button and image.
            JButton btn = new JButton("OK");
            btn.addActionListener(event -> dispose());

            /* Get the correct solution picture */
            //String fileName = "src/resources/solution"+board.getMap()+".png";
            ImageIcon solutionPic = new ImageIcon("src/resources/solution"+board.getMap()+".png");

            JLabel solution = new JLabel(solutionPic);

            createLayout(solution, btn);
        }

        private void createLayout(JLabel pic, JButton btn) {
            Container pane = getContentPane();
            GroupLayout gl = new GroupLayout(pane);
            pane.setLayout(gl);

            gl.setAutoCreateContainerGaps(true);
            gl.setAutoCreateGaps(true);

            gl.setHorizontalGroup(gl.createParallelGroup(CENTER)
                    .addComponent(pic)
                    .addComponent(btn)
            );

            gl.setVerticalGroup(gl.createSequentialGroup()
                    .addComponent(pic)
                    .addGap(20)
                    .addComponent(btn)
                    .addGap(20)
            );
            pack();
        }
    }
}


