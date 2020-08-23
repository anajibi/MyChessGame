package Menus;

import MenusGUI.GameMenu;

import java.util.Collections;
import java.util.regex.Pattern;

public class MainMenu {
    private static MainMenu Instance;

    private MainMenu() {
    }

    public static boolean inputMatchPattern(String input) {
        if (Pattern.compile("^[a-zA-Z\\d_]+$").matcher(input).find())
            return true;
        return false;
    }

    public static MainMenu getInstance() {
        if (Instance == null)
            Instance = new MainMenu();
        return Instance;
    }

    public String listUsers() {
        String output = "";
        Collections.sort(User.allUsers);
        for (int i = 0; i < User.allUsers.size(); i++) {
            output += (i + 1) + ". " + User.allUsers.get(i) + "\n";
        }
        return output;
    }

    public void register(String username, String password) throws RuntimeException {
        if (!inputIsCorrect(username, password))
            throw new RuntimeException("Please enter input correctly.");
        else if (User.usernameExists(username))
            throw new RuntimeException("A user exists with this username");
        else {
            new User(username, password);
        }
    }

    public void login(String username, String password) throws RuntimeException {
        if (!inputIsCorrect(username, password))
            throw new RuntimeException("Please enter input correctly.");
        else if (!User.usernameExists(username))
            throw new RuntimeException("no user exists with this username");
        else if (!User.getUserByUsername(username).getPassword().equals(password))
            throw new RuntimeException("Incorrect password");
        else {
            MenusGUI.MainMenu.getInstance().setVisible(false);
            MenusGUI.MainMenu.getInstance().dispose();
            new Menus.GameMenu(User.getUserByUsername(username));
            GameMenu.getInstance().setVisible(true);
        }
    }

    public void remove(String username, String password) throws RuntimeException {
        if (!inputIsCorrect(username, password)) ;
        else if (!User.usernameExists(username))
            throw new RuntimeException("No user exists with this username");
        else if (!User.getUserByUsername(username).getPassword().equals(password))
            throw new RuntimeException("Incorrect password");
        else {
            System.out.println("Removed " + username + " Successfully");
            User.removeUser(User.getUserByUsername(username));
        }
    }

    public boolean inputIsCorrect(String username, String password) {
        if (!inputMatchPattern(username))
            throw new RuntimeException("Username format is invalid");
        else if (!inputMatchPattern(password))
            throw new RuntimeException("Password format is invalid");
        else
            return true;
    }

    public String changePassword(String username, String pass, String newPass, String newPassRe) {
        if (!inputIsCorrect(username, pass))
            throw new RuntimeException("Please enter input correctly.");
        else if (!User.usernameExists(username))
            throw new RuntimeException("No user exists with this username");
        else if (!User.getUserByUsername(username).getPassword().equals(pass))
            throw new RuntimeException("Incorrect password");
        else if (!newPass.equals(newPassRe))
            throw new RuntimeException("Passwords dont match");
        else if (pass.equals(newPass))
            throw new RuntimeException("New password is the same as old password.");
        else
            User.getUserByUsername(username).setPassword(newPass);
        return "Successful";
    }
}
