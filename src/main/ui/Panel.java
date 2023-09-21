package ui;

import javax.swing.*;
import java.awt.*;

//setting for panel
public class Panel extends JPanel {

    //Modifies: this
    //EFFECT: initiates object
    Panel() {
        this.setBackground(Color.pink);
        this.setOpaque(false);
        //this.setBounds(x, y, width, height);
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setVisible(true);

    }


}
