/*
 * Created by JFormDesigner on Sat May 23 14:24:40 IRDT 2020
 */

package MenusGUI;

import Listeners.PauseListener;
import Menus.MainMenu;
import Resources.Locator;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * @author unknown
 */
public class GameMenu extends JFrame {
    private static GameMenu Instance;
    private GameMenu()
    {
        super("Easy chess");
        super.setIconImage(new ImageIcon(new Locator().getURL("Icons\\icons8-queen-48.png")).getImage());
        try {
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            Image image = ImageIO.read(new Locator().getURL("ImageSources\\GameMenuBckg.jpg"));
            Dimension size = new Dimension(900, 650);
            setContentPane(new JLabel(new ImageIcon(image.getScaledInstance(900, 650, 1))));
            setPreferredSize(size);
            setResizable(false);
            this.setMinimumSize(size);
            this.setMaximumSize(size);
        } catch (IOException /*| ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException*/ e) {
            e.printStackTrace();
        }
        initComponents();
        initActionListeners();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static GameMenu getInstance() {
        if (Instance == null)
            Instance = new GameMenu();
        Instance.pausePlayBttn = PauseListener.getInstance().getBtn();
        return Instance;
    }

    public static void getNewInstance() {
        Instance = new GameMenu();
    }

    public void setPausePlayBttn(JButton pausePlayBttn) {
        this.pausePlayBttn = pausePlayBttn;
    }

    public  void showMessage(String format) {
        JOptionPane.showMessageDialog(this,format);
    }

    private void initActionListeners() {
        logoutBttn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Menus.GameMenu.getInstance().logout();
            }
        });
        listUsersBttn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(GameMenu.getInstance(),MainMenu.getInstance().listUsers());
            }
        });
        scoreBoardBttn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(GameMenu.getInstance(), Menus.GameMenu.getInstance().scoreboard());
            }
        });
        newGameBttn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int limit = 0;
                String username = JOptionPane.showInputDialog(GameMenu.getInstance(),"Please enter the other player's username who is gonna be the black color ");
                if (username==null)
                    return;
                try {
                    String option  = JOptionPane.showInputDialog(GameMenu.getInstance(),"Please enter a number as moves limit for the chess game or enter 0 for no limit.",2);
                    if (option == null)
                        return;
                    limit = Integer.parseInt(option);
                } catch (NumberFormatException numberFormatException) {
                    limit = 0;
                }
                int numberOfUndo;
                try {
                    try {
                        String s =JOptionPane.showInputDialog("Please enter the number of undoes for every player.",2);
                        if (s == null)return;
                        numberOfUndo = Integer.parseInt(s);
                    } catch (NumberFormatException numberFormatException) {
                        numberOfUndo = 2;
                    }
                    int option = JOptionPane.showConfirmDialog(null,"Do you want to set a timer for yourGame?");
                    boolean hasTimer = (option==JOptionPane.YES_OPTION?true:false);
                    if (option == JOptionPane.CANCEL_OPTION)
                        return;
                    Menus.GameMenu.getInstance().newGame(username,limit,hasTimer,numberOfUndo);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(GameMenu.getInstance(),exception.getMessage());
                }
            }
        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();
        newGameBttn = new JButton();
        scoreBoardBttn = new JButton();
        listUsersBttn = new JButton();
        logoutBttn = new JButton();
        pausePlayBttn = PauseListener.getInstance().getBtn();

        //======== this ========
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Game Menu");
        label1.setFont(new Font("Segoe UI Semilight", Font.BOLD, 21));

        //---- newGameBttn ----
        newGameBttn.setText("New Game");

        //---- scoreBoardBttn ----
        scoreBoardBttn.setText("Score Board");

        //---- listUsersBttn ----
        listUsersBttn.setText("List Users");

        //---- logoutBttn ----
        logoutBttn.setText("Logout");

        //---- pausePlayBttn ----


        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(105, 105, 105)
                                    .addComponent(label1))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(61, 61, 61)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(listUsersBttn, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                        .addComponent(logoutBttn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(newGameBttn, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                        .addComponent(scoreBoardBttn, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))))
                            .addGap(0, 672, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGap(0, 856, Short.MAX_VALUE)
                            .addComponent(pausePlayBttn, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pausePlayBttn)
                    .addGap(18, 18, 18)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addGap(127, 127, 127)
                    .addComponent(newGameBttn, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(scoreBoardBttn, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(listUsersBttn, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(logoutBttn, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(199, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }



    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label1;
    private JButton newGameBttn;
    private JButton scoreBoardBttn;
    private JButton listUsersBttn;
    private JButton logoutBttn;
    private JButton pausePlayBttn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
