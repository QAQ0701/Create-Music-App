package ui;

import javax.swing.*;

//music note and rest buttons
public class IconButton extends JButton {


    private static Boolean active;

    //Modifies: this
    //EFFECT: instantiates object and sets buttons up
    public IconButton() {
        //this.setBounds();
        this.setFocusable(false); //remove border around text
        this.setOpaque(true);
        this.setEnabled(true);
        this.setVisible(true);
        active = false;
    }

    //Modifies: this
    //EFFECT: disables the button and makes it invisible
    public void disableButton() {
        this.setEnabled(false);
        this.setVisible(false);
    }

    // EFFECTS: sets this Tool's active field to false
    public void deactivate() {
        active = false;
    }

    // EFFECTS: sets this Tool's active field to true
    public void activate() {
        active = true;
    }

    //getter
    public Boolean isActive() {
        return active;
    }
}
