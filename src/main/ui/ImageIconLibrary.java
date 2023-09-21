package ui;

import javax.swing.*;
import java.awt.*;

//stores all iconImages
public class ImageIconLibrary extends JLabel {

    private static final Integer BG_WIDTH = 900;
    private static final Integer BG_HEIGHT = 800;
    private static final Integer SHORT_HEIGHT = 28;
    private static final Integer TALL_HEIGHT = 60;
    private static final Integer MID_HEIGHT = 40;

    private static ImageTool thumbnailUnscaled;
    private static ImageTool titlePageUnscaled;
    private static ImageTool bgUnscaled;
    private static ImageTool spookyBgUnscaled;
    private static ImageTool spookyTransGIF;

    private static ImageTool trebleClef;
    private static ImageTool fourFour;

    private static ImageTool wholeNote;
    private static ImageTool halfNote;
    private static ImageTool quarterNote;
    private static ImageTool eighthNote;
    private static ImageTool sixteenthNote;
    private static ImageTool wholeRest;
    private static ImageTool halfRest;
    private static ImageTool quarterRest;
    private static ImageTool eighthRest;
    private static ImageTool sixteenthRest;


    private static ImageIcon thumbnail;
    private static ImageIcon titlePageScaledRatio;
    //private static ImageIcon titlePageScaled;
    private static  ImageIcon bgScaledRatio;
    private static ImageIcon spookyBgScaledRatio;
    private static ImageIcon spookyTransGIFScaledRatio;

    private static ImageIcon trebleClefScaledRatio;
    private static ImageIcon fourFourScaledRatio;


    protected static ImageIcon wholeNoteScaled;
    protected static ImageIcon halfNoteScaled;
    protected static ImageIcon quarterNoteScaled;
    protected static ImageIcon eighthNoteScaled;
    protected static ImageIcon sixteenthNoteScaled;
    protected static ImageIcon wholeRestScaled;
    protected static ImageIcon halfRestScaled;
    protected static ImageIcon quarterRestScaled;
    protected static ImageIcon eighthRestScaled;
    protected static ImageIcon sixteenthRestScaled;

    //Modifies: this
    //EFFECT: initiates object
    public ImageIconLibrary() {
        setUp();
        generateImageIcons();
        generateNoteOrRestIcons();
    }

    //Modifies: this
    //EFFECT: formats this
    public void setUp() {
        //Border border = BorderFactory.createLineBorder(Color.green, 3);

        //JLabel this = new JLabel(); //create a label, Label label = new JLabel("Text");
        //this.setText("Text .... Where is the image?");//set text of label
        //this.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT, CENTER, RIGHT of image icon
        //this.setVerticalTextPosition(JLabel.TOP); //set Text TOP, CENTER, BOTTOM of image icon

        //text colour/font
        //this.setForeground(Color.BLUE); //customize by initiate new Color and set RGB or HEX
        //this.setFont(new Font("MV Boli", Font.PLAIN, 20));
        //this.setIconTextGap(-25); //set gap of text ot image
        this.setBackground(Color.WHITE); //set background colour
        this.setOpaque(true); //display background colour
        this.setLayout(new BorderLayout());

        this.setVerticalAlignment(JLabel.TOP); //set vert pos of icon+text within label
        this.setHorizontalAlignment(JLabel.RIGHT); //set hori pos of icon+text within label

        //this.setBounds(0,0, 900, 750); //either panel or label needs to set bound

        this.setVisible(true);
    }

    //Modifies: this
    //EFFECT: set given image to this
    public void setImageIcon(ImageIcon image) {
        this.setIcon(image);
    }


    //Modifies: this
    //EFFECT: provides path to images backdrops
    public void generateImageIcons() {
        //thumbnailUnscaled = new ImageTool(new ImageIcon("./data/images/IconThumbnail.png"));
        thumbnail = scaleIcon(thumbnailUnscaled, "IconThumbnail.png", 50, 50);

        //titlePageUnscaled = new ImageTool((new ImageIcon("./data/images/titlePage.PNG")));
        titlePageScaledRatio = scaleIcon(titlePageUnscaled, "titlePage.PNG", BG_WIDTH, BG_HEIGHT);

        //bgUnscaled = new ImageTool((new ImageIcon("./data/images/background.JPG")));
        bgScaledRatio = scaleIcon(bgUnscaled, "background.JPG", BG_WIDTH, BG_HEIGHT);

        //spookyBgUnscaled = new ImageTool((new ImageIcon("./data/images/background2.JPG")));
        spookyBgScaledRatio = scaleIcon(spookyBgUnscaled,"background2.JPG",BG_WIDTH, BG_HEIGHT);

        spookyTransGIF = new ImageTool((new ImageIcon("./data/images/SpookyTransition.GIF")));
        spookyTransGIFScaledRatio = spookyTransGIF.scaleGifRatio(BG_WIDTH, BG_HEIGHT);

        //trebleClef = new ImageTool((new ImageIcon("./data/images/trebleClef.png")));
        trebleClefScaledRatio = scaleIcon(trebleClef,"trebleClef.png",110, 110);

        //fourFour = new ImageTool((new ImageIcon("./data/images/fourFour.png")));
        fourFourScaledRatio = scaleIcon(fourFour,"fourFour.png",75, 75);



    }

    //Modifies: this
    //EFFECT: provides path to images notes and rest images
    public void generateNoteOrRestIcons() {
        //wholeNote = new ImageTool((new ImageIcon("./data/images/wholeNote.png")));
       // wholeNoteScaled = wholeNote.scaleImageRatio(NOTE_HEIGHT, NOTE_HEIGHT);

       //Notes
        wholeNoteScaled = scaleNoteOrRest(wholeNote, "wholeNote.png", SHORT_HEIGHT);
        halfNoteScaled = scaleNoteOrRest(halfNote, "halfNote.png", TALL_HEIGHT);
        quarterNoteScaled = scaleNoteOrRest(quarterNote, "quarterNote.png", TALL_HEIGHT);
        eighthNoteScaled = scaleNoteOrRest(eighthNote, "eightNote.png", TALL_HEIGHT);
        sixteenthNoteScaled = scaleNoteOrRest(sixteenthNote, "sixteenthNote.png", TALL_HEIGHT);
        //Rests
        wholeRestScaled = scaleNoteOrRest(wholeRest, "wholeRest.png", SHORT_HEIGHT);
        halfRestScaled = scaleNoteOrRest(halfRest, "halfRest.png", SHORT_HEIGHT);
        quarterRestScaled = scaleNoteOrRest(quarterRest, "quarterRest.png", TALL_HEIGHT);
        eighthRestScaled = scaleNoteOrRest(eighthRest, "eightRest.png", MID_HEIGHT);
        sixteenthRestScaled = scaleNoteOrRest(sixteenthRest, "sixteenthRest.png", MID_HEIGHT);



    }


    //EFFECT: scales given note/rest image
    public ImageIcon scaleNoteOrRest(ImageTool imageTool, String source, Integer dim) {
        String direc = "./data/images/" + source;
        imageTool = new ImageTool((new ImageIcon(direc)));
        return imageTool.scaleImageRatio(dim, dim);
    }

    //Modifies: this
    //EFFECT: scales given backdrop image
    public ImageIcon scaleIcon(ImageTool imgTool, String source, Integer x, Integer y) {
        String direc = "./data/images/" + source;
        imgTool = new ImageTool((new ImageIcon(direc)));
        return imgTool.scaleImageRatio(x, y);
    }

    //GETTERS:
    public static ImageIcon getSpookyGifRatio() {
        return spookyTransGIFScaledRatio;
    }

    public static ImageIcon getBgScaledRatio() {
        return bgScaledRatio;
    }

    public static ImageIcon getSpookyBgScaledRatio() {
        return spookyBgScaledRatio;
    }

    public static ImageTool getSpookyTransGIF() {
        return spookyTransGIF;
    }

    public static ImageIcon getTitlePageScaledRatio() {
        return titlePageScaledRatio;
    }

    public static ImageIcon getThumbnail() {
        return thumbnail;
    }

    public static ImageIcon getTrebleClefScaledRatio() {
        return trebleClefScaledRatio;
    }

    public static ImageIcon getFourFourScaledRatio() {
        return fourFourScaledRatio;
    }

    public static ImageIcon getWholeNoteScaled() {
        return wholeNoteScaled;
    }

    public static ImageIcon getHalfNoteScaled() {
        return halfNoteScaled;
    }

    public static ImageIcon getQuarterNoteScaled() {
        return quarterNoteScaled;
    }

    public static ImageIcon getEighthNoteScaled() {
        return eighthNoteScaled;
    }

    public static ImageIcon getSixteenthNoteScaled() {
        return sixteenthNoteScaled;
    }

    public static ImageIcon getWholeRestScaled() {
        return wholeRestScaled;
    }

    public static ImageIcon getHalfRestScaled() {
        return halfRestScaled;
    }

    public static ImageIcon getQuarterRestScaled() {
        return quarterRestScaled;
    }

    public static ImageIcon getEighthRestScaled() {
        return eighthRestScaled;
    }

    public static ImageIcon getSixteenthRestScaled() {
        return sixteenthRestScaled;
    }


}
