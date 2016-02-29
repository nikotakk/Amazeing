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

    public Amazeing() {

        initUI();
    }
    // Initializing the UI.
    private void initUI() {
        /* Luodaan menubar, johon pistetään menuja. */
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        file.setMnemonic(KeyEvent.VK_F);
        help.setMnemonic(KeyEvent.VK_H);

        /* Luodaan Exit menuItem ja pistetään se menubariin. */
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

        file.add(menuExit);
        menubar.add(file);
        menubar.add(help);
        setJMenuBar(menubar);
        /* Testataa vertikaalista JToolBaria oikealla. */
        JToolBar vertical = new JToolBar(JToolBar.VERTICAL);
        vertical.setFloatable(false);
        vertical.setMargin(new Insets(10, 70, 5, 70));
        add(vertical, BorderLayout.EAST);
        /* Testataa JLabelia alhaalla. */
        JLabel statusbar = new JLabel();
        statusbar.setPreferredSize(new Dimension(-1, 130));
        statusbar.setBorder(LineBorder.createGrayLineBorder());
        add(statusbar, BorderLayout.SOUTH);
        /* Lisätään Board keskelle näyttöä. */
        add(new Board(), BorderLayout.CENTER);

        setSize(740, 600);
        setResizable(false);
        setTitle("Amazeing");
        //setBackground(Color.lightGray);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /* Main. */
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