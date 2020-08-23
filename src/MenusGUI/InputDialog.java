package MenusGUI;

import javax.swing.*;
import java.awt.*;

public class InputDialog {

    private JFrame frame;
    private JPanel pane;
    private JTextField username;
    private JTextField password;
    private JTextField newPassword;
    private JTextField newPasswordReEnter;
    private int option;

    public void twoFieldDialog() {
        pane = new JPanel();
        pane.setLayout(new GridLayout(0, 2, 2, 2));
        username = new JTextField(5);
        password = new JPasswordField(5);

        pane.add(new JLabel("username"));
        pane.add(username);

        pane.add(new JLabel("Password : "));
        pane.add(password);

        option = JOptionPane.showConfirmDialog(frame, pane, "Please enter info", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

    public void fourFieldDialog() {
        pane = new JPanel();
        pane.setLayout(new GridLayout(0, 2, 4, 2));
        username = new JTextField(5);
        password = new JPasswordField(5);
        newPassword = new JTextField(5);
        newPasswordReEnter = new JTextField(5);
        pane.add(new JLabel("Username : "));
        pane.add(username);
        pane.add(new JLabel("Password : "));
        pane.add(password);
        pane.add(new JLabel("New Password : "));
        pane.add(newPassword);
        pane.add(new JLabel("Reenter New Password : "));
        pane.add(newPasswordReEnter);

        option = JOptionPane.showConfirmDialog(frame, pane, "Please enter info", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

    public JTextField getUsername() {
        return username;
    }


    public JTextField getPassword() {
        return password;
    }


    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public JTextField getNewPassword() {
        return newPassword;
    }

    public JTextField getNewPasswordReEnter() {
        return newPasswordReEnter;
    }
}