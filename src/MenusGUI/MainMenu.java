/*
 * Created by JFormDesigner on Sat May 23 10:09:33 IRDT 2020
 */

package MenusGUI;

import Listeners.PauseListener;

import Resources.Locator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * @author unknown
 */
public class MainMenu extends JFrame {
    private static MainMenu Instance;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JButton loginBttn;
    private JButton removeUsrBttn;
    private JButton listUsrsBttn;
    private JButton exitBttn;
    private JButton registerBttn;
    private JLabel label1;
    private JButton pausePlayBttn;
    private Menus.MainMenu mainMenu;

    private MainMenu() {
        super("Easy Chess");
        super.setIconImage(new ImageIcon(new Locator().getURL("Icons\\icons8-king-48.png")).getImage());
        try {
            UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[3].getClassName());
            Image image = ImageIO.read(new Locator().getURL("ImageSources\\Background.jpg"));
            Dimension size = new Dimension(900, 650);
            setContentPane(new JLabel(new ImageIcon(image.getScaledInstance(900, 650, 1))));
            setPreferredSize(size);
            setResizable(false);
            this.setMinimumSize(size);
            this.setMaximumSize(size);
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();

        pack();

    }

    public static MainMenu getInstance() {
        if (Instance == null)
            Instance = new MainMenu();
        return Instance;
    }

    public static MainMenu getNewInstance() {
        Instance = new MainMenu();
        return getInstance();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        loginBttn = new JButton();
        removeUsrBttn = new JButton();
        listUsrsBttn = new JButton();
        exitBttn = new JButton();
        registerBttn = new JButton();
        label1 = new JLabel();
        pausePlayBttn = PauseListener.getInstance().getBtn();

        //======== this ========
        setResizable(false);
        setMinimumSize(new Dimension(400, 270));
        var contentPane = getContentPane();

        //---- loginBttn ----
        loginBttn.setText("Login");

        //---- removeUsrBttn ----
        removeUsrBttn.setText("Remove User");

        //---- listUsrsBttn ----
        listUsrsBttn.setText("Change Password");

        //---- exitBttn ----
        exitBttn.setText("Exit");

        //---- registerBttn ----
        registerBttn.setText("Register");
        registerBttn.setIcon(null);

        //---- label1 ----
        label1.setText("Welcome to My Chess Game! Enjoy");
        label1.setFont(new Font("Segoe UI Semilight", Font.BOLD, 20));

        //---- pausePlayBttn ----

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(504, 504, 504)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(registerBttn, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(loginBttn, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(removeUsrBttn, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(listUsrsBttn, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(exitBttn, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(pausePlayBttn, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(56, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pausePlayBttn)
                                .addGap(5, 5, 5)
                                .addComponent(label1)
                                .addGap(106, 106, 106)
                                .addComponent(registerBttn, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(loginBttn, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(removeUsrBttn, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(listUsrsBttn, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(exitBttn, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(206, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        setListeners();
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void setListeners() {
        loginBttn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        InputDialog inputDialog;
                        (inputDialog = new InputDialog()).twoFieldDialog();
                        if (inputDialog.getOption() == JOptionPane.OK_OPTION) {
                            try {
                                Menus.MainMenu.getInstance().login(inputDialog.getUsername().getText(), inputDialog.getPassword().getText());
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(MenusGUI.MainMenu.getInstance(), e.getMessage());
                            }

                        }
                    }
                });
            }
        });
        registerBttn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        InputDialog inputDialog;
                        (inputDialog = new InputDialog()).twoFieldDialog();
                        if (inputDialog.getOption() == JOptionPane.OK_OPTION) {
                            try {
                                Menus.MainMenu.getInstance().register(inputDialog.getUsername().getText(), inputDialog.getPassword().getText());
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(MenusGUI.MainMenu.getInstance(), e.getMessage());
                            }

                        }
                    }
                });
            }
        });
        exitBttn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(1);
            }
        });
        listUsrsBttn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                InputDialog inputDialog = new InputDialog();
                inputDialog.fourFieldDialog();
                if (inputDialog.getOption()==JOptionPane.CANCEL_OPTION)
                    return;
                String message;
                try {
                    message = Menus.MainMenu.getInstance().changePassword(inputDialog.getUsername().getText(),inputDialog
                            .getPassword().getText(),inputDialog.getNewPassword().getText(),inputDialog.getNewPasswordReEnter().getText());
                } catch (Exception exception) {

                    message = exception.getMessage();
                }
                JOptionPane.showMessageDialog(null,message);
            }
        });
        removeUsrBttn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        InputDialog inputDialog;
                        (inputDialog = new InputDialog()).twoFieldDialog();
                        if (inputDialog.getOption() == JOptionPane.OK_OPTION) {
                            try {
                                Menus.MainMenu.getInstance().remove(inputDialog.getUsername().getText(), inputDialog.getPassword().getText());
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(MenusGUI.MainMenu.getInstance(), e.getMessage());
                                return;
                            }
                            JOptionPane.showMessageDialog(MenusGUI.MainMenu.getInstance(), "Action Completed.");
                        }
                    }
                });
            }
        });
    }

    public void setPausePlayBttn(JButton pausePlayBttn) {
        this.pausePlayBttn = pausePlayBttn;
    }
// JFormDesigner - End of variables declaration  //GEN-END:variables
}
