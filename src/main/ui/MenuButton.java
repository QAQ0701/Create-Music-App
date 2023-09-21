package ui;

import javax.swing.*;
import java.awt.*;

//button settings for title page buttons
public class MenuButton extends JButton {

    //Modifies: this
    //EFFECT: initiates object
    MenuButton() {
        //this.setBounds();
        this.setFocusable(false); //remove border around text
        this.setOpaque(true);
        this.setEnabled(true);
        this.setVisible(true);

        // this.addActionListener(frame);

    }

    //Modifies: this
    //EFFECT: disables and make this invisable
    public void disableButton() {
        this.setEnabled(false);
        this.setVisible(false);
    }
}
