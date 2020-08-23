package Menus;

import java.util.ArrayList;
import java.util.Objects;

public class User implements Comparable {
    public static ArrayList<User> allUsers = new ArrayList<User>();
    private String username;
    private String password;
    private ScoreBoard scoreBoard;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        scoreBoard = new ScoreBoard(this.username, 0, 0, 0, 0);
        User.allUsers.add(this);
    }

    public static boolean usernameExists(String username) {
        for (int i = 0;i < allUsers.size();++i) {
            if (allUsers.get(i).getUsername().equals(username))
                return true;
        }
        return false;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public static User getUserByUsername(String username) {
        for (int i = 0;i < allUsers.size();++i) {
            if (allUsers.get(i).getUsername().equals(username))
                return allUsers.get(i);
        }
        throw new RuntimeException("User Doesnt exists.");
    }

    public static void removeUser(User user) {
        User.allUsers.remove(user);
    }

    @Override
    public int compareTo(Object o) {
        User user = (User) o;
        return this.username.compareTo(user.username);
    }

    @Override
    public String toString() {
        return username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    public String scoreboardToString() {
        return String.format("Username: %s   %s" ,username, scoreBoard.toString());
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }
}

