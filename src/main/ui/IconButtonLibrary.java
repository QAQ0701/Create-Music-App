package ui;

import javax.swing.*;

//scales and stores icon Buttons
public class IconButtonLibrary extends JButton {
    private static IconButton wholeNote;
    private static IconButton halfNote;
    private static IconButton quarterNote;
    private static IconButton eightNote;
    private static IconButton sixteenthNote;

    private static IconButton wholeRest;
    private static IconButton halfRest;
    private static IconButton quarterRest;
    private static IconButton eightRest;
    private static IconButton sixteenthRest;


//Frame frame = new Frame();

    //Modifies: this
    //EFFECT: instantiates object
    public IconButtonLibrary() {
        initializeIconButton();
        setImage();
    }

    //Modifies: this
    //EFFECT: give all field values
    public void setImage() {
        wholeNote.setIcon(ImageIconLibrary.getWholeNoteScaled());
        halfNote.setIcon(ImageIconLibrary.getHalfNoteScaled());
        quarterNote.setIcon(ImageIconLibrary.getQuarterNoteScaled());
        eightNote.setIcon(ImageIconLibrary.getEighthNoteScaled());
        sixteenthNote.setIcon(ImageIconLibrary.getSixteenthNoteScaled());

        wholeRest.setIcon(ImageIconLibrary.getWholeRestScaled());
        halfRest.setIcon(ImageIconLibrary.getHalfRestScaled());
        quarterRest.setIcon(ImageIconLibrary.getQuarterRestScaled());
        eightRest.setIcon(ImageIconLibrary.getEighthRestScaled());
        sixteenthRest.setIcon(ImageIconLibrary.getSixteenthRestScaled());
    }


    //Modifies: this
    //EFFECT: initiallizes all fields
    public void initializeIconButton() {
        wholeNote = new IconButton();
        halfNote = new IconButton();
        quarterNote = new IconButton();
        eightNote = new IconButton();
        sixteenthNote = new IconButton();

        wholeRest = new IconButton();
        halfRest = new IconButton();
        quarterRest = new IconButton();
        eightRest = new IconButton();
        sixteenthRest = new IconButton();
        //wholeNote.addActionListener(this);
    }

    //MODIFIES: this
    //EFFECT: deactivate all IconButtons in this class
    public static void deactivateAll() {
        wholeNote.setEnabled(false);
        halfNote.setEnabled(false);
        quarterNote.setEnabled(false);
        eightNote.setEnabled(false);
        sixteenthNote.setEnabled(false);

        wholeRest.setEnabled(false);
        halfRest.setEnabled(false);
        quarterRest.setEnabled(false);
        eightRest.setEnabled(false);
        sixteenthRest.setEnabled(false);
    }

    //MODIFIES: this
    //EFFECT: Activate all IconButtons in this class
    public static void activateAll() {
        wholeNote.setEnabled(true);
        halfNote.setEnabled(true);
        quarterNote.setEnabled(true);
        eightNote.setEnabled(true);
        sixteenthNote.setEnabled(true);

        wholeRest.setEnabled(true);
        halfRest.setEnabled(true);
        quarterRest.setEnabled(true);
        eightRest.setEnabled(true);
        sixteenthRest.setEnabled(true);
    }

    //getter
    public static IconButton getWholeNote() {
        return wholeNote;
    }

    public static IconButton getHalfNote() {
        return halfNote;
    }

    public static IconButton getQuarterNote() {
        return quarterNote;
    }

    public static IconButton getEightNote() {
        return eightNote;
    }

    public static IconButton getSixteenNote() {
        return sixteenthNote;
    }

    public static IconButton getWholeRest() {
        return wholeRest;
    }

    public static IconButton getHalfRest() {
        return halfRest;
    }

    public static IconButton getQuarterRest() {
        return quarterRest;
    }

    public static IconButton getEightRest() {
        return eightRest;
    }

    public static IconButton getSixteenRest() {
        return sixteenthRest;
    }
}
