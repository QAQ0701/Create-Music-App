package ui;

import model.AllScore;
import model.NoteOrRest;
import model.Score;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//paints the note and rest
public class PainterNoteRest extends JPanel {
    private static Integer staffDistance = PainterStaff.getStaffDistance();

    protected static Image wholeNote = ImageIconLibrary.wholeNoteScaled.getImage();
    protected static Image halfNote = ImageIconLibrary.halfNoteScaled.getImage();
    protected static Image quarterNote = ImageIconLibrary.quarterNoteScaled.getImage();
    protected static Image eighthNote = ImageIconLibrary.eighthNoteScaled.getImage();
    protected Image sixteenthNote = ImageIconLibrary.sixteenthNoteScaled.getImage();
    protected Image wholeRest = ImageIconLibrary.wholeRestScaled.getImage();
    protected Image halfRest = ImageIconLibrary.halfRestScaled.getImage();
    protected Image quarterRest = ImageIconLibrary.quarterRestScaled.getImage();
    protected Image eighthRest = ImageIconLibrary.eighthRestScaled.getImage();
    protected Image sixteenthRest = ImageIconLibrary.sixteenthRestScaled.getImage();




    //SPACE
    private static final Integer S0_WHOLE = 1;
    private static final Integer S1_WHOLE = 22;
    private static final Integer S2_WHOLE = S1_WHOLE + staffDistance - 1;
    private static final Integer S3_WHOLE = S2_WHOLE + staffDistance;
    private static final Integer S4_WHOLE = S3_WHOLE + staffDistance;
    //private static final Integer S5_SHORT = S4_SHORT + STAFF_DISTANCE;*/


    private static final Integer S1_TALL = -22;
    private static final Integer S2_TALL = S1_TALL + staffDistance;
    private static final Integer S3_TALL = S2_TALL + staffDistance;
    private static final Integer S4_TALL = S3_TALL + staffDistance;
    //private static final Integer S5_TALL = S4_TALL + STAFF_DISTANCE;

    //LINE
    private static final Integer L1_WHOLE = 11;
    private static final Integer L2_WHOLE = L1_WHOLE + staffDistance;
    private static final Integer L3_WHOLE = L2_WHOLE + staffDistance;
    private static final Integer L4_WHOLE = L3_WHOLE + staffDistance;
    private static final Integer L5_WHOLE = L4_WHOLE + staffDistance;
    //Whole notes

    private static final Integer L1_TALL = -32;
    private static final Integer L2_TALL = L1_TALL + staffDistance;
    private static final Integer L3_TALL = L2_TALL + staffDistance;
    private static final Integer L4_TALL = L3_TALL + staffDistance;
    private static final Integer L5_TALL = L4_TALL + staffDistance;
    //

    //RESTS
    private static final Integer R_Quarter = 25;
    private static final Integer R_ONE_EIGHT_SIX = 40;
    private static final Integer R_TWO = 50;

    private static Color color;
    private static Integer lineWidth;
    private static Integer bXStart;
    private static Integer bXMid;
    private static Integer nRSpacing;

    private String timeVal;
    private String pitch;


    private static Panel iconPanel;
    protected Score score;

    private static Integer mouseX;
    private static Integer mouseY;

    private Integer lineNum;

    private static NoteOrRest currentElement;

    private static Boolean isMenu;

    //Modifies: this
    //EFFECT: instantiates object and fields
    public PainterNoteRest() {
        this.setVisible(true);
        this.setOpaque(false);
        iconPanel = new Panel();
        score = new Score();
        this.setPreferredSize(new Dimension(PainterStaff.getPanelWidth(), PainterStaff.getPanelHeight()));
        init();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                lineNum = currentElement.getStaffPos();
                if (isMenu == true) {
                    checkScoreSize();
                } else {
                    score.addNoteOrRest(createNote());
                }
                repaint();

            }
        });

    }

    //MODIFIES: this
    //EFFECT: initializes fields
    public void init() {
        lineWidth = PainterStaff.getLineWidth();
        color = PainterStaff.getColor();
        bXStart = PainterStaff.getBxStart();
        bXMid = PainterStaff.getBxMid();
        nRSpacing = NoteOrRest.getSpacing();
        pitch = "D4";
        timeVal = "1";
        isMenu = false;
    }

    //MODIFIES: this
    //EFFECT: makes sure the delete function removes the last element in the score
    public void checkScoreSize() {
        if (score.getSizeOfScore() == 1) {
            score.removeNoteOrRest(0);
        } else if (score.getSizeOfScore() < 1) {
            //do nothing
        } else {
            score.removeNoteOrRest(score.getSizeOfScore() - 1);
        }
    }

    //EFFECT: returns the image to the corresponding activeButton
    public Image currentImage() {
        Image img = wholeNote;
        img = compareButtonToImage(IconButtonLibrary.getWholeNote(), wholeNote, img);
        img = compareButtonToImage(IconButtonLibrary.getQuarterNote(), quarterNote, img);
        img = compareButtonToImage(IconButtonLibrary.getHalfNote(), halfNote, img);
        img = compareButtonToImage(IconButtonLibrary.getEightNote(), eighthNote, img);
        img = compareButtonToImage(IconButtonLibrary.getSixteenNote(), sixteenthNote, img);
        img = compareButtonToImage(IconButtonLibrary.getWholeRest(), wholeRest, img);
        img = compareButtonToImage(IconButtonLibrary.getHalfRest(), halfRest, img);
        img = compareButtonToImage(IconButtonLibrary.getQuarterRest(), quarterRest, img);
        img = compareButtonToImage(IconButtonLibrary.getEightRest(), eighthRest, img);
        img = compareButtonToImage(IconButtonLibrary.getSixteenRest(), sixteenthRest, img);
        return img;
    }

    //EFFECT: compares given icon button to active button and return the corresponding image
    public Image compareButtonToImage(IconButton b, Image wanted, Image previous) {
        if (StaffPanel.activeButton.equals(b)) {
            return wanted;
        }
        return previous;
    }

    //EFFECT: paints graphic
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g; //cast
        g2D.setStroke(new BasicStroke(lineWidth));
        g2D.setPaint(color);
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //g2D.drawImage(wholeNote, 100,50, null);

        for (int i = 0; i < score.getSizeOfScore(); i++) {
            NoteOrRest note = score.getNoteFromList(i);
            g2D.drawImage(note.getImage(), note.getXpos(), note.getYpos(), null);
        }

    }

    //g2D.drawImage(wholeNote, note.getXpos(), note.getYpos(), null);
    //currentElement = score.getNoteFromList(i);
            /*if (pitch.equals("0")) {
                if (timeVal.equals("1")) {
                    g2D.drawImage(ImageIconLibrary.getWholeRestScaled().getImage(), lineX(), lineY(), null);
                } else if (timeVal.equals("1/2")) {
                    g2D.drawImage(ImageIconLibrary.getHalfRestScaled().getImage(), lineX(), lineY(), null);
                }
            } else if (!pitch.equals("0")) {
                if (timeVal.equals("1")) {
                    g2D.drawImage(ImageIconLibrary.getWholeNoteScaled().getImage(), lineX(), lineY(), null);
                } else if (timeVal.equals("1/2")) {
                    g2D.drawImage(ImageIconLibrary.getHalfNoteScaled().getImage(), lineX(), lineY(), null);
                }

            }*/

    /*g2D.drawImage(ImageIconLibrary.getHalfRestScaled().getImage(),
                        bXMid + 1 * nRSpacing, R_TWO, null);
                g2D.drawImage(ImageIconLibrary.getQuarterRestScaled().getImage(),
                        bXMid + 2 * nRSpacing, R_Quarter, null);
                g2D.drawImage(ImageIconLibrary.getEighthRestScaled().getImage(),
                        bXMid + 3 * nRSpacing, R_ONE_EIGHT_SIX, null);
                g2D.drawImage(ImageIconLibrary.getSixteenthRestScaled().getImage(),
                        bXMid + 4 * nRSpacing, R_ONE_EIGHT_SIX, null);*/

     /*//Note Image
        //g2D.drawImage(ImageIconLibrary.getWholeNoteScaled().getImage(), BORDER_X_START, 10, null);
        g2D.drawImage(ImageIconLibrary.getHalfNoteScaled().getImage(),
                bXStart, L1_TALL, null);
        g2D.drawImage(ImageIconLibrary.getHalfNoteScaled().getImage(),
                bXStart + nRSpacing, L1_TALL, null);
        g2D.drawImage(ImageIconLibrary.getQuarterNoteScaled().getImage(),
                bXStart + 2 * nRSpacing, L1_TALL, null);
        g2D.drawImage(ImageIconLibrary.getEighthNoteScaled().getImage(),
                bXStart + 3 * nRSpacing, L1_TALL, null);
        g2D.drawImage(ImageIconLibrary.getSixteenthNoteScaled().getImage(),
                bXStart + 4 * nRSpacing, L1_TALL, null);
        g2D.drawImage(ImageIconLibrary.getWholeNoteScaled().getImage(),
                bXStart + 5 * nRSpacing, L1_WHOLE, null);
        //Rest Image
        g2D.drawImage(ImageIconLibrary.getWholeRestScaled().getImage(),
                bXMid + 0 * nRSpacing, R_ONE_EIGHT_SIX, null);

        g2D.drawImage(ImageIconLibrary.getHalfRestScaled().getImage(),
                bXMid + 1 * nRSpacing, R_TWO, null);
        g2D.drawImage(ImageIconLibrary.getQuarterRestScaled().getImage(),
                bXMid + 2 * nRSpacing, R_Quarter, null);
        g2D.drawImage(ImageIconLibrary.getEighthRestScaled().getImage(),
                bXMid + 3 * nRSpacing, R_ONE_EIGHT_SIX, null);
        g2D.drawImage(ImageIconLibrary.getSixteenthRestScaled().getImage(),
                bXMid + 4 * nRSpacing, R_ONE_EIGHT_SIX, null);*/

    //getter
    public static Integer getMouseX() {
        return mouseX;
    }

    //getter
    public static Integer getMouseY() {
        return mouseY;
    }

    //Modifies: this
    //EFFECT: Creates note with pitch, staff pos according to mouse click position
    public NoteOrRest createNote() {
        Integer staffPos = 1;
        if (mouseY >= 26 && mouseY <= 29) {
            setPitchStaffPos("F5", 1);
        } else if (mouseY > 29 && mouseY < 46) {
            setPitchStaffPos("E5", 2);
        } else if (mouseY >= 46 && mouseY <= 49) {
            setPitchStaffPos("D5", 3);
        } else if (mouseY > 49 && mouseY < 66) {
            setPitchStaffPos("C5", 4);
        } else if (mouseY >= 66 && mouseY <= 69) {
            setPitchStaffPos("B5", 5);
        } else if (mouseY > 69 && mouseY < 86) {
            setPitchStaffPos("A5", 6);
        } else if (mouseY >= 86 && mouseY <= 89) {
            setPitchStaffPos("G4", 7);
        } else if (mouseY > 89 && mouseY < 106) {
            setPitchStaffPos("F4", 8);
        } else if (mouseY >= 106 && mouseY <= 109) {
            setPitchStaffPos("E4", 9);
        }
        setCurrent(getActiveButtonTimeVal());
        currentElement.setStaffPos(staffPos);
        return currentElement;
    }

    //MODIFIES: this
    //EFFECT setUp for createNote method.
    public void setCurrent(String timeVal) {
        timeVal = getActiveButtonTimeVal();
        NoteOrRest element = new NoteOrRest(pitch, timeVal);
        //this.timeVal = timeVal;
        currentElement = element;
        currentElement.setXpos(mouseX);
        currentElement.setYpos(mouseY);
        currentElement.setImage(currentImage());

    }

    //MODIFIES: this
    //EFFECT: given string and integer, sets pitch and staff position
    public void setPitchStaffPos(String pitch, Integer staffP) {
        //Integer staffPos;
        this.pitch = pitch;
        //staffPos = staffP;
    }

    //EFFECT: give the x-cord for note/rest for line/space
    public Integer lineX() {
        Integer index = score.indexOfNote(currentElement);
        return currentElement.getXpos();
    }


    //EFFECT Return true if pitch = 0
    public Boolean isRest(String pitch) {
        return pitch.equals("0");
    }

    //Modifies: this
    //EFFECT: sets y components of image cord
    public Integer setLineY(Integer i, Integer first, Integer second) {
        timeVal = currentElement.getTimeVal();
        Integer lineY = 0;
        Integer lineNum = currentElement.getStaffPos();
        if (timeVal.equals("1")) {
            lineY = first;
        } else if (isTallNote(timeVal)) {
            lineY = second;
        }
        return currentElement.getYpos();
    }

    //EFFECT  give the y-cord for rest for line/space
    public Integer restY(Integer y) {

        if (timeVal.equals("1/2")) {
            y = R_TWO;
        } else if ((timeVal.equals("1")) || (timeVal.equals("1/8")) || (timeVal.equals("1/16"))) {
            y = R_ONE_EIGHT_SIX;
        } else if ((timeVal.equals("1/4"))) {
            y = R_Quarter;
        }
        return y;
    }

    //EFFECT: give the y-cord for note/rest for line/space
    public Integer lineY() {
        Integer lineY = 0;
        if (pitch.equals("0")) {
            restY(lineY);
        } else if (!isRest("0")) {
            if (lineNum == 4) {
                lineY = setLineY(4, S2_WHOLE, S2_TALL);
            } else if (lineNum == 6) {
                lineY = setLineY(6, S3_WHOLE, S3_TALL);
            } else if (lineNum == 8) {
                lineY = setLineY(8, S4_WHOLE, S4_TALL);
            } else if (lineNum == 1) {
                lineY = setLineY(1, L1_WHOLE, L1_TALL);
            } else if (lineNum == 3) {
                lineY = setLineY(3, L2_WHOLE, L2_TALL);
            } else if (lineNum == 5) {
                lineY = setLineY(5, L3_WHOLE, L3_TALL);
            } else if (lineNum == 7) {
                lineY = setLineY(7, L4_WHOLE, L4_TALL);
            } else if (lineNum == 9) {
                lineY = setLineY(9, L5_WHOLE, L5_TALL);
            }
        }
        return mouseY;
    }


    //EFFECT: returns the time value of the active button
    public String getActiveButtonTimeVal() {
        String timeVal = "1";
        if (IconButtonLibrary.getWholeNote().isActive() || IconButtonLibrary.getWholeRest().isActive()) {
            timeVal = "1";
        } else if (IconButtonLibrary.getHalfNote().isActive() || IconButtonLibrary.getHalfRest().isActive()) {
            timeVal = "1/2";
        } else if (IconButtonLibrary.getQuarterNote().isActive() || IconButtonLibrary.getQuarterRest().isActive()) {
            timeVal = "1/4";
        } else if (IconButtonLibrary.getEightNote().isActive() || IconButtonLibrary.getEightNote().isActive()) {
            timeVal = "1/8";
        } else if (IconButtonLibrary.getSixteenNote().isActive() || IconButtonLibrary.getSixteenRest().isActive()) {
            timeVal = "1/16";
        }
        return timeVal;
    }

    //Modifies: this
    //EFFECT: if given is "0" return false
    public boolean isTallNote(String timeVal) {
        boolean firstHalf = ((timeVal.equals("1/2")) || (timeVal.equals("1/4")));
        return (firstHalf || (timeVal.equals("1/8")) || (timeVal.equals("1/16")));
    }

    //EFFECT: repaints this object
    public void repaintThis() {
        repaint();
    }

    //getters
    public static Panel getIconPanel() {
        return iconPanel;
    }

    public Score getScore() {
        return score;
    }

    //setters
    public void setScore(Score scr) {
        score = scr;
    }

    public static void setIsMenu(Boolean b) {
        isMenu = b;
    }



}