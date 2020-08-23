package Menus;

import Game.Board;
import Game.Player;
import MenusGUI.BoardMenu;
import MenusGUI.MainMenu;
import Pieces.Piece;

import javax.swing.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Pattern;

public class GameMenu {
    private static GameMenu Instance;
    private User callerUser;

    public GameMenu(User callerUser) {
        this.callerUser = callerUser;
        Instance = this;
    }

    public static GameMenu getInstance() {
        return Instance;
    }

    public String scoreboard() {
        String tab = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        Collections.sort(User.allUsers, Comparator.comparing(User::getScoreBoard));
        String s = "";
        for (int i = 0; i < User.allUsers.size(); ++i) {
            s += (i + 1) + ". " + User.allUsers.get(i).scoreboardToString() + "\n";
        }
        return s;
    }


    public void newGame(String username, int limit, boolean hasTimer,int undoLeft) {
        if (!inputMatchPattern(username))
            throw new RuntimeException("Username format is invalid");
        else if (limit < 0)
            throw new RuntimeException("Number should be positive to have a limit or 0 for no limit");
        else if (username.equals(callerUser.getUsername()))
            throw new RuntimeException("You must choose another player to start a game");
        else if (!User.usernameExists(username))
            throw new RuntimeException("No user exists with this username");
        else {
            new Board(new Player(User.getUserByUsername(username), Piece.BLACK,undoLeft), new Player(callerUser, Piece.WHITE,undoLeft), limit);
            MenusGUI.GameMenu.getInstance().showMessage(String.format("New game started successfully between %s and %s with limit %d", callerUser.getUsername(), username, limit));
            MenusGUI.GameMenu.getInstance().dispose();
            try {
                UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[4].getClassName());
            } catch (Exception e) {

            }
            new BoardMenu(hasTimer);
            BoardMenu.getInstance().setVisible(true);
        }
    }

    public boolean inputMatchPattern(String input) {
        if (Pattern.compile("^[a-zA-Z\\d_]+$").matcher(input).matches())
            return true;
        return false;
    }


    public void logout() {
        MenusGUI.GameMenu.getInstance().setVisible(false);
        MenusGUI.GameMenu.getInstance().dispose();
        MenusGUI.MainMenu.getNewInstance();
        MainMenu.getInstance().setVisible(true);
    }
}
