import Listeners.PauseListener;
import Menus.MainMenu;
import Resources.Locator;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        startMusic();
        MainMenu.getInstance();
        MenusGUI.MainMenu.getInstance().setVisible(true);
    }

    private static void startMusic() {
        Clip clip;
        try {
             AudioInputStream audioIn = AudioSystem.getAudioInputStream(new Locator().getURL("music.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            PauseListener.setClip(clip);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            JOptionPane.showMessageDialog(null, "Could not play music!!");
        }
    }
}
