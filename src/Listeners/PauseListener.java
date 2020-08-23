package Listeners;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PauseListener extends MouseAdapter {
    private static PauseListener Instance;
    private JButton btn ;
    private static Clip clip;
    private ImageIcon[] icons = { new ImageIcon(getClass().getResource("/Resources/Icons/icons8-pause-24.png"))
    , new ImageIcon(getClass().getResource("/Resources/Icons/icons8-play-24.png"))};
    private int iconNum = 0;

    private PauseListener() {
        this.btn = btn;
        this.clip = clip;
        btn = new JButton();
        btn.setIcon(getIcons()[getIconNum()]);
        btn.setBorderPainted(false);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorder(null);
        btn.addMouseListener(this);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static PauseListener getInstance() {
        if (Instance == null)
            Instance = new PauseListener();
        return Instance;
    }

    public ImageIcon[] getIcons() {
        return icons;
    }

    public int getIconNum() {
        return iconNum;
    }
    public void mouseClicked(MouseEvent event){
        iconNum = ~iconNum+2;
        btn.setIcon(icons[iconNum]);
        if (iconNum == 1)
            clip.stop();
        else
            clip.start();
    }

    public static void setClip(Clip clip) {
        PauseListener.clip = clip;
    }

    public JButton getBtn() {
        return btn;
    }
}
