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
    private int mazeSize = 25 * 16;
    private Board board;
    private JLabel time;
    private JLabel map;
    private JLabel highscore;
    private int chosenMap = 1;
    private String chosenBall = "punainen";

    private Amazeing() {

        initMenuBar();
        initUI();
        endGame();
//        timeWatch();
        updateUI();
    }

    /* Initializing the menuBar. */
    private void initMenuBar() {

        /* Creating a menuBar, to which menus Game and Help are added. */
        JMenuBar menuBar = new JMenuBar();
        JMenu game = new JMenu("Peli");
        JMenu help = new JMenu("Ohje");
        game.setMnemonic(KeyEvent.VK_E);
        help.setMnemonic(KeyEvent.VK_H);

        /* Creating Reset menuItem. */
        JMenuItem menuReset = new JMenuItem("Resetoi kenttä");
        menuReset.setMnemonic(KeyEvent.VK_R);
        menuReset.setToolTipText("Resetoi meneillä oleva kenttä");
        menuReset.addActionListener(event -> board.resetMap());

        /* Creating ChooseMap menuItem. */
        JMenuItem menuChooseMap = new JMenuItem("Kentän valinta");
        menuChooseMap.setMnemonic(KeyEvent.VK_R);
        menuChooseMap.setToolTipText("Valitse mitä kenttää pelata");
        menuChooseMap.addActionListener(event -> showMap());

        /* Creating Exit menuItem. */
        JMenuItem menuExit = new JMenuItem("Sulje");
        menuExit.setMnemonic(KeyEvent.VK_E);
        menuExit.setToolTipText("Sulje sovellus");
        menuExit.addActionListener(event -> System.exit(0));

        /* Creating Instructions menuItem. */
        JMenuItem menuInstructions = new JMenuItem("Ohjeet");
        menuInstructions.setMnemonic(KeyEvent.VK_I);
        menuInstructions.setToolTipText("Näytä ohjeet kuinka peliä pelataan");
        menuInstructions.addActionListener(event -> showHelp());

        /* Creating highScore menuItem. */
        JMenuItem menuHighScore = new JMenuItem("Ennätykset");
        menuHighScore.setMnemonic(KeyEvent.VK_H);
        menuHighScore.setToolTipText("Näytä eri kenttien ennätykset");
        menuHighScore.addActionListener(event -> showScores());

          /* Creating Settings menuItem. */
        JMenuItem menuSettings = new JMenuItem("Asetukset");
        menuSettings.setMnemonic(KeyEvent.VK_S);
        menuSettings.setToolTipText("Näytä asetukset.");
        menuSettings.addActionListener(event -> showSettings());

        /* Creating Solution menuItem. */
        JMenuItem menuSolution = new JMenuItem("Ratkaisu");
        menuSolution.setMnemonic(KeyEvent.VK_O);
        menuSolution.setToolTipText("Näytä tämän kentän ratkaisu");
        menuSolution.addActionListener(event -> showSolution());

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

        /* Adding best time and current lvl into the statusBar. */
        JPanel infoBox = new JPanel();
        infoBox.setAlignmentX(1f);
        infoBox.setLayout(new BoxLayout(infoBox, BoxLayout.Y_AXIS));
        infoBox.setPreferredSize(new Dimension(130, 50));
        map = new JLabel();
        highscore = new JLabel();
        infoBox.add(highscore);
        infoBox.add(map);


        /* Creating time label */
        time = new JLabel();
        time.setPreferredSize(new Dimension(90, 25));

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

        setMinimumSize(new Dimension(400, 500));
        setSize(mazeSize + 20, mazeSize + movebarHeight + 70);

        setResizable(true);
        setTitle("Amazeing");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

    private void showScores() {
        ScoresDialog scores = new ScoresDialog(this);
        scores.setVisible(true);
    }

    private void showEnd() {
        EndDialog end = new EndDialog(this);
        end.setVisible(true);
    }

//    /* The stopwatch used in the game. */
//    private void timeWatch() {
//        Thread watch = new Thread(() -> {
//            while (true) {
//
//            }
//        });
//        watch.start();
//    }

    /* Updating the UI map and highscore texts. */
    @SuppressWarnings("InfiniteLoopStatement")
    private void updateUI() {
        Thread update = new Thread(() -> {
            while (true) {
                map.setText("Kenttä: " + board.getMap());
                highscore.setText("Ennätys: " + board.getHighscore(board.getMap()));
                time.setText("Aika: " + board.getTime());
            }
        });
        update.start();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void endGame() {
        Thread end = new Thread(() -> {
            while (true) {
                boolean isGoalReached = board.getGoalReached();
                //System.out.println("endGame(AMAZEING): isGoalReached = " + isGoalReached);
                if (isGoalReached) {
                    System.out.println("endGame(Amazeing): goalReached = "+ board.getGoalReached());
                    board.setGoalReachedFalse();
                    showEnd();
                }
            }
        });
        end.start();
    }

    /* Nested classes */
    private class SettingsDialog extends JDialog implements ItemListener {
        SettingsDialog(Frame parent) {
            super(parent);
            initSettingsDialog();
        }

        private void initSettingsDialog() {
            setTitle("Asetukset");
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocation(getParent().getLocation());
            setModalityType(ModalityType.APPLICATION_MODAL);

            JButton btn = new JButton("OK");
            btn.addActionListener(event -> {
                board.initVariables(chosenMap, chosenBall.toLowerCase(), true);
                dispose();
            });

            JButton cancel = new JButton("Peruuta");
            cancel.addActionListener(event -> dispose());

            JLabel color = new JLabel("Valitse pallosi väri:");

            ButtonGroup buttons = new ButtonGroup();

            JRadioButton rb1 = new JRadioButton("Punainen", true);
            JRadioButton rb2 = new JRadioButton("Vihreä");
            JRadioButton rb3 = new JRadioButton("Sininen");
            JRadioButton rb4 = new JRadioButton("Keltainen");
            JRadioButton rb5 = new JRadioButton("Valkoinen");

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

            setSize(350, 300);
            setMinimumSize(new Dimension(200, 250));
            createLayout(color, rb1, rb2, rb3, rb4, rb5, btn, cancel);
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
                    .addGroup(gl.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(arg[6])
                            .addGap(20)
                            .addComponent(arg[7]))
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
                    .addGroup(gl.createParallelGroup()
                            .addComponent(arg[6])
                            .addComponent(arg[7]))
            );
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            int selection = e.getStateChange();
            if (selection == ItemEvent.SELECTED) {
                JRadioButton button = (JRadioButton) e.getSource();
                chosenBall = button.getText().toLowerCase();
            }
        }
    }

    private class MapDialog extends JDialog implements ItemListener {

        MapDialog(Frame parent) {
            super(parent);
            initMapDialog();
        }

        private void initMapDialog() {
            setTitle("Kentän valinta");
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocation(getParent().getLocation());
            setModalityType(ModalityType.APPLICATION_MODAL);

            JButton okBtn = new JButton("OK");
            okBtn.addActionListener(event -> {
                board.initVariables(chosenMap, chosenBall, true);
                dispose();
            });

            JButton cancel = new JButton("Peruuta");
            cancel.addActionListener(event -> dispose());

            JLabel map = new JLabel("Valitse kenttä jota pelata:");

            ButtonGroup buttons = new ButtonGroup();

            JRadioButton rb1 = new JRadioButton("Kenttä 1", true);
            JRadioButton rb2 = new JRadioButton("Kenttä 2");
            JRadioButton rb3 = new JRadioButton("Kenttä 3");
            JRadioButton rb4 = new JRadioButton("Kenttä 4");

            buttons.add(rb1);
            buttons.add(rb2);
            buttons.add(rb3);
            buttons.add(rb4);

            rb1.addItemListener(this);
            rb2.addItemListener(this);
            rb3.addItemListener(this);
            rb4.addItemListener(this);

            setMinimumSize(new Dimension(200, 230));
            setSize(350, 300);

            createLayout(map, rb1, rb2, rb3, rb4, okBtn, cancel);
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            int selection = e.getStateChange();
            if (selection == ItemEvent.SELECTED) {
                JRadioButton button = (JRadioButton) e.getSource();
                chosenMap = Integer.parseInt(button.getText().substring(7, 8));
            }
        }

        private void createLayout(JComponent... arg) {
            Container pane = getContentPane();
            GroupLayout gl = new GroupLayout(pane);
            pane.setLayout(gl);

            gl.setAutoCreateContainerGaps(true);
            gl.setAutoCreateGaps(true);
            gl.setHorizontalGroup(gl.createParallelGroup()
                    .addComponent(arg[0])
                    .addComponent(arg[1])
                    .addComponent(arg[2])
                    .addComponent(arg[3])
                    .addComponent(arg[4])
                    .addGroup(gl.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(arg[5])
                            .addGap(20)
                            .addComponent(arg[6]))
            );

            gl.setVerticalGroup(gl.createSequentialGroup()
                    .addComponent(arg[0])
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(arg[1])
                    .addComponent(arg[2])
                    .addComponent(arg[3])
                    .addComponent(arg[4])
                    .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(gl.createParallelGroup()
                            .addComponent(arg[5])
                            .addComponent(arg[6]))
            );
        }
    }

    private class HelpDialog extends JDialog {

        HelpDialog(Frame parent) {
            super(parent);
            initHelpDialog();
        }

        private void initHelpDialog() {
            setTitle("Ohjeet");
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocation(getParent().getLocation());
            setModalityType(ModalityType.APPLICATION_MODAL);

            // Create a button and image.
            JButton btn = new JButton("OK");
            btn.addActionListener(event -> dispose());

            JLabel instructions = new JLabel(new ImageIcon("src/resources/instructions.png"));
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
            setTitle("Ratkaisu");
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocation(getParent().getLocation());
            setModalityType(ModalityType.APPLICATION_MODAL);

            // Create a button and image.
            JButton btn = new JButton("OK");
            btn.addActionListener(event -> dispose());

            /* Get the correct solution picture */
            //String fileName = "src/resources/solution"+board.getMap()+".png";
            ImageIcon solutionPic = new ImageIcon("src/resources/solution" + board.getMap() + ".png");

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

    private class ScoresDialog extends JDialog {
        ScoresDialog(Frame parent) {
            super(parent);
            initScoresDialog();
        }

        private void initScoresDialog() {
            setTitle("Ennätykset");
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocation(getParent().getLocation());
            setModalityType(ModalityType.APPLICATION_MODAL);

            JButton btn = new JButton("OK");
            btn.addActionListener(event -> dispose());

            setMinimumSize(new Dimension(100, 180));
            setSize(150, 200);

            JPanel scores = new JPanel();
            scores.setAlignmentX(1f);
            scores.setLayout(new BoxLayout(scores, BoxLayout.Y_AXIS));
            scores.setPreferredSize(new Dimension(130, 50));

            JLabel score1 = new JLabel("Kentän 1 ennätys: " + board.getHighscore(1));
            JLabel score2 = new JLabel("Kentän 2 ennätys: " + board.getHighscore(2));
            JLabel score3 = new JLabel("Kentän 3 ennätys: " + board.getHighscore(3));
            JLabel score4 = new JLabel("Kentän 4 ennätys: " + board.getHighscore(4));

            Dimension gap = new Dimension(5, 5);

            scores.add(Box.createRigidArea(gap));
            scores.add(score1);
            scores.add(Box.createRigidArea(gap));
            scores.add(score2);
            scores.add(Box.createRigidArea(gap));
            scores.add(score3);
            scores.add(Box.createRigidArea(gap));
            scores.add(score4);
            scores.add(Box.createRigidArea(new Dimension(5, 20)));
            scores.add(btn);

            add(scores);
        }
    }

    private class EndDialog extends JDialog {
        EndDialog(Frame parent) {
            super(parent);
            initEndDialog();
        }

        private void initEndDialog() {
            System.out.println("initEndDialog(): initializing the window.");
            setTitle("Kenttä läpi!");
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocation(getParent().getLocation());
            setModalityType(ModalityType.APPLICATION_MODAL);

            JButton next = new JButton("Seuraava kenttä");
            JButton newMap = new JButton("Kentän valinta");

            JLabel time = new JLabel("Aikasi: " + board.getTime());

            setSize(150,200);
            createLayout(time, newMap, next);
        }

        private void createLayout(JLabel pic, JButton newMap, JButton next) {

            newMap.addActionListener(event -> {
                showMap();
                dispose();
            });
            next.addActionListener(event -> {
                int nextMap = board.getMap() + 1;
                if (board.getMap() == 4) {
                    nextMap = 1;
                }
                board.initVariables(nextMap, chosenBall, true);
                dispose();
            });

            Container pane = getContentPane();
            GroupLayout gl = new GroupLayout(pane);
            pane.setLayout(gl);

            gl.setAutoCreateContainerGaps(true);
            gl.setAutoCreateGaps(true);

            gl.setHorizontalGroup(gl.createParallelGroup(CENTER)
                    .addComponent(pic)
                    .addGroup(gl.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(newMap)
                            .addGap(20)
                            .addComponent(next))
            );

            gl.setVerticalGroup(gl.createSequentialGroup()
                    .addComponent(pic)
                    .addGap(20)
                    .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(gl.createParallelGroup()
                            .addComponent(newMap)
                            .addComponent(next))
                    .addGap(20)
            );
            pack();
        }
    }

    /* -------------  Main  -------------------------------------- */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            Amazeing game = new Amazeing();
            game.setVisible(true);
        });
    }
}


