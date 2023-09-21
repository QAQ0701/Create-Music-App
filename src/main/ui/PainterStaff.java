package ui;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

//paints staff
public class PainterStaff extends JPanel {
    private static final Integer STAFF_DISTANCE = 20;
    private static final Integer ORIGIN = 20;
    private static final Integer PANEL_WIDTH = 680;
    private static final Integer PANEL_HEIGHT = 110;
    private static final Integer LINE_WIDTH = 3;
    private static final Integer BX_START = 100;
    private static final Integer BX_MID = PANEL_WIDTH / 2 + 60;

    protected static Panel background;

    private static PainterNoteRest elementPanel;



    private static Color color;
    private static Integer nRSpacing;

    //Modifies: this
    //EFFECT: instantiates object
    public PainterStaff() {
        this.setVisible(true);
        background = new Panel();
       // background.setBackground(ColorUIResource.GREEN);
       // background.setPreferredSize(new Dimension(StaffPanel.STAFF_PANEL_WIDTH, StaffPanel.STAFF_PANEL_HEIGHT));
        //nRSpacing = NoteOrRest.getSpacing();
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        //elementPanel = new PainterNoteRest();
        //this.add(background);
        background.setBackground(Color.WHITE);
        //this.add(elementPanel);
        background.add(new PainterNoteRest());
    }

    //Modifies: this
    //EFFECT: paints the lines
    @Override
    public void paint(Graphics g) {

        //Lines
        Graphics2D g2D = (Graphics2D) g; //cast
        g2D.setStroke(new BasicStroke(LINE_WIDTH));
        g2D.setPaint(color);
        g2D.drawLine(0, ORIGIN, PANEL_WIDTH, ORIGIN);
        g2D.drawLine(0, 1 * STAFF_DISTANCE + ORIGIN, PANEL_WIDTH, 1 * STAFF_DISTANCE + ORIGIN);
        g2D.drawLine(0, 2 * STAFF_DISTANCE + ORIGIN, PANEL_WIDTH, 2 * STAFF_DISTANCE + ORIGIN);
        g2D.drawLine(0, 3 * STAFF_DISTANCE + ORIGIN, PANEL_WIDTH, 3 * STAFF_DISTANCE + ORIGIN);
        g2D.drawLine(0, 4 * STAFF_DISTANCE + ORIGIN, PANEL_WIDTH, 4 * STAFF_DISTANCE + ORIGIN);

        g2D.drawLine(PANEL_WIDTH / 2 + 35, ORIGIN, PANEL_WIDTH / 2 + 35, 4 * STAFF_DISTANCE + ORIGIN);
        g2D.setStroke(new BasicStroke(LINE_WIDTH + 1));
        g2D.drawLine(PANEL_WIDTH - 2, ORIGIN, PANEL_WIDTH - 2, 4 * STAFF_DISTANCE + ORIGIN);

        //Images
        g2D.drawImage(ImageIconLibrary.getTrebleClefScaledRatio().getImage(), 0, 1, null);
        g2D.drawImage(ImageIconLibrary.getFourFourScaledRatio().getImage(), 50, 23, null);
    }

        /*//Note Image
        //g2D.drawImage(ImageIconLibrary.getWholeNoteScaled().getImage(), BORDER_X_START, 10, null);
        g2D.drawImage(ImageIconLibrary.getHalfNoteScaled().getImage(),
                 BX_START, L1_TALL, null);
        g2D.drawImage(ImageIconLibrary.getHalfNoteScaled().getImage(),
                BX_START + nRSpacing, L1_TALL, null);
        g2D.drawImage(ImageIconLibrary.getQuarterNoteScaled().getImage(),
                BX_START + 2 * nRSpacing, L1_TALL, null);
        g2D.drawImage(ImageIconLibrary.getEighthNoteScaled().getImage(),
                BX_START + 3 * nRSpacing, L1_TALL, null);
        g2D.drawImage(ImageIconLibrary.getSixteenthNoteScaled().getImage(),
                BX_START + 4 * nRSpacing, L1_TALL, null);
        g2D.drawImage(ImageIconLibrary.getWholeNoteScaled().getImage(),
                BX_START + 5 * nRSpacing, L1_SHORT, null);


        //Rest Image
        g2D.drawImage(ImageIconLibrary.getWholeRestScaled().getImage(),
                BX_MID + 0 * nRSpacing, R_MID, null);

        g2D.drawImage(ImageIconLibrary.getHalfRestScaled().getImage(),
                BX_MID + 1 * nRSpacing, R_SHORT, null);
        g2D.drawImage(ImageIconLibrary.getQuarterRestScaled().getImage(),
                BX_MID + 2 * nRSpacing, R_TALL, null);
        g2D.drawImage(ImageIconLibrary.getEightRestScaled().getImage(),
                BX_MID + 3 * nRSpacing, R_MID, null);
        g2D.drawImage(ImageIconLibrary.getSixteenthRestScaled().getImage(),
                BX_MID + 4 * nRSpacing, R_MID, null);*/
        //g2D.setPaint(Color.RED);
        //g2D.setStroke(new BasicStroke(1));
        //g2D.drawRect(0, ORIGIN-1, PANEL_WIDTH, LINE_WIDTH-1);
        //g2D.fillRect(0,0,100,200);
        //g2D.drawOval(0,0,100,100);
        //g2D.fillOval(0,0,100,100);
        //g2D.drawArc(0,0,100,100,0,180);
        //g2D.fillArc(0,0,100,100,0,180);
        //g2D.setPaint(Color.white);
        //g2D.fillArc(0,0,100,100,180,180);
        /*int[] xPoints = {150, 250, 350};
        int[] yPoints = {300, 150, 300};
        g2D.drawPolygon(xPoints, yPoints, 3);
        g2D.setFont(new Font("Ink Free", Font.BOLD, 50));
        g2D.drawString("UR A Winner", 50, 50); //origin at bottom left corner
        g2D.drawImage(Label.getThumbnail().getImage(), 0, 0, null);


    }*/
    /*public void drawNote(NoteOrRest note, Integer pos) {

        if (note.getTimeVal() == "1") {
            if (note.getPitch().equals("E4")) {
                g2D.drawImage(ImageIconLibrary.getWholeNoteScaled().getImage(),
                        BX_START + 5 * nRSpacing, L5_SHORT, null);
            }
        }

    }*/

    //Getter and Setter
    public static void setColor(Color color) {
        PainterStaff.color = color;
    }

    public static Integer getStaffDistance() {
        return STAFF_DISTANCE;
    }

    public static Integer getOrigin() {
        return ORIGIN;
    }

    public static Integer getPanelWidth() {
        return PANEL_WIDTH;
    }

    public static Integer getPanelHeight() {
        return PANEL_HEIGHT;
    }

    public static Integer getLineWidth() {
        return LINE_WIDTH;
    }

    public static Color getColor() {
        return color;
    }

    public static Integer getBxStart() {
        return BX_START;
    }

    public static Integer getBxMid() {
        return BX_MID;
    }
}
