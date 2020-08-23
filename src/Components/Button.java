package Components;

import Pieces.Location;

import javax.swing.*;

public class Button extends JButton {
    private Location myLoc;

    public Button(Icon icon, Location myLoc) {
        super(icon);
        this.myLoc = myLoc;

    }

    public Button() {

    }


    public Location getMyLoc() {
        return myLoc;
    }

    public void setMyLoc(Location myLoc) {
        this.myLoc = myLoc;
    }
}
