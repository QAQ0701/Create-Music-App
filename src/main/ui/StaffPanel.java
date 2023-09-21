package ui;

import model.AllScore;
import model.Score;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//the panel that contains staff painting
public class StaffPanel extends JPanel implements ActionListener {

    protected static final Integer STAFF_PANEL_WIDTH = 700;
    protected static final Integer STAFF_PANEL_HEIGHT = 100;

    protected static AllScore allScore;

    private  Panel line1;
    private  Panel line2;
    private  Panel line3;
    private  Panel line4;

    private Panel back1;
    private Panel back2;
    private Panel back3;
    private Panel back4;

    private PainterNoteRest paintNote1;
    private PainterNoteRest paintNote2;
    private PainterNoteRest paintNote3;
    private PainterNoteRest paintNote4;

    protected static IconButton activeButton;

    //private static Integer mouseX;
    //private static Integer mouseY;

    //Modifies: this
    //EFFECT: adds 4 panels
    public StaffPanel() {
        this.setVisible(true);
        setLines();
        allScore = new AllScore();
        addBack();
        initPaintNoteRest();
        addPaintToBack();

        activeButton = new IconButton();

    }

    public void addPaintToBack() {
        back1.add(new PainterStaff());
        back1.add(paintNote1);
        back2.add(new PainterStaff());
        back2.add(paintNote2);
        back3.add(new PainterStaff());
        back3.add(paintNote3);
        back4.add(new PainterStaff());
        back4.add(paintNote4);

        allScore.addScore(paintNote1.getScore());
        allScore.addScore(paintNote2.getScore());
        allScore.addScore(paintNote3.getScore());
        allScore.addScore(paintNote4.getScore());
    }

    /*public void addMouseListener(Panel panel) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                System.out.println(mouseX + "," + mouseY);
                if (activeButton == IconButtonLibrary.getWholeNote()) {

                    //stub
                }
            }
        });
    }*/

    //Modifies: this
    //EFFECT: initialize PainterNoteRest fields
    public void initPaintNoteRest() {
        paintNote1 = new PainterNoteRest();
        paintNote2 = new PainterNoteRest();
        paintNote3 = new PainterNoteRest();
        paintNote4 = new PainterNoteRest();
    }

    //Modifies: this
    //EFFECT: sets property of lines
    public void setLines() {
        line1 = new Panel();
        line1.setBackground(ColorUIResource.YELLOW);
        line1.setPreferredSize(new Dimension(STAFF_PANEL_WIDTH, STAFF_PANEL_HEIGHT));
        //line1.addMouseListener(this);
        line2 = new Panel();
        line2.setPreferredSize(new Dimension(STAFF_PANEL_WIDTH, STAFF_PANEL_HEIGHT));
        //line2.addMouseListener(this);
        line3 = new Panel();
        line3.setBackground(ColorUIResource.YELLOW);
        line3.setPreferredSize(new Dimension(STAFF_PANEL_WIDTH, STAFF_PANEL_HEIGHT));
        //line3.addMouseListener(this);
        line4 = new Panel();
        line4.setPreferredSize(new Dimension(STAFF_PANEL_WIDTH, STAFF_PANEL_HEIGHT));
        //line4.addMouseListener(this);

        back1 = new Panel();
        back1.setLayout(new OverlayLayout(back1));
        back1.setPreferredSize(new Dimension(STAFF_PANEL_WIDTH, 150));
        back1.setBackground(Color.WHITE);
        back2 = new Panel();
        back2.setLayout(new OverlayLayout(back2));
        back2.setBackground(Color.BLUE);
        back3 = new Panel();
        back3.setLayout(new OverlayLayout(back3));
        back3.setBackground(Color.WHITE);
        back4 = new Panel();
        back4.setLayout(new OverlayLayout(back4));
        back4.setBackground(Color.WHITE);
    }

    void addBack() {
        line1.add(back1);
        line2.add(back2);
        line3.add(back3);
        line4.add(back4);
    }


    //getter
    public Panel getLine1() {
        return line1;
    }

    public Panel getLine2() {
        return line2;
    }

    public Panel getLine3() {
        return line3;
    }

    public Panel getLine4() {
        return line4;
    }

    public Integer getStaffPanelWidth() {
        return STAFF_PANEL_WIDTH;
    }

    public static Integer getStaffPanelHeight() {
        return STAFF_PANEL_HEIGHT;
    }

    // MODIFIES: this
    // EFFECTS:  sets the given tool as the activeTool
    public void setActiveTool(IconButton button) {
        if (activeButton != null) {
            activeButton.deactivate();
        }
        button.activate();
        activeButton = button;
    }

    /*public static Integer getMouseX() {
        return mouseX;
    }

    public static Integer getMouseY() {
        return mouseY;
    }*/

    //SETTER
    public void setAllScore(AllScore all) {
        allScore = all;
    }

    //MODIFIES this
    //EFFECT: sets data from loaded file
    public void load() {
        paintNote1.setScore(Frame.allScore.getLine(1));
        paintNote1.repaintThis();
        paintNote2.setScore(Frame.allScore.getLine(2));
        paintNote2.repaintThis();
        paintNote3.setScore(Frame.allScore.getLine(3));
        paintNote3.repaintThis();
        paintNote4.setScore(Frame.allScore.getLine(4));
        paintNote4.repaintThis();
        addPaintToBack();
    }


    //EFFECT: listens for actions by icon buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == IconButtonLibrary.getWholeNote()) {
            setActiveTool(IconButtonLibrary.getWholeNote());
        } else if (e.getSource() == IconButtonLibrary.getHalfNote()) {
            setActiveTool(IconButtonLibrary.getHalfNote());
        } else if (e.getSource() == IconButtonLibrary.getQuarterNote()) {
            setActiveTool(IconButtonLibrary.getQuarterNote());
        } else if (e.getSource() == IconButtonLibrary.getEightNote()) {
            setActiveTool(IconButtonLibrary.getEightNote());
        } else if (e.getSource() == IconButtonLibrary.getSixteenNote()) {
            setActiveTool(IconButtonLibrary.getSixteenNote());
        } else if (e.getSource() == IconButtonLibrary.getWholeRest()) {
            setActiveTool(IconButtonLibrary.getWholeRest());
        } else if (e.getSource() == IconButtonLibrary.getHalfRest()) {
            setActiveTool(IconButtonLibrary.getHalfRest());
        } else if (e.getSource() == IconButtonLibrary.getQuarterRest()) {
            setActiveTool(IconButtonLibrary.getQuarterRest());
        } else if (e.getSource() == IconButtonLibrary.getEightRest()) {
            setActiveTool(IconButtonLibrary.getEightRest());
        } else if (e.getSource() == IconButtonLibrary.getSixteenRest()) {
            setActiveTool(IconButtonLibrary.getSixteenRest());
        }
    }

}
