package model;

import org.json.JSONObject;
import persistence.Writable;
import ui.PainterNoteRest;

import java.awt.*;

//Represents individual notes or rests on score.
public class NoteOrRest implements Writable {
    private static final Integer SPACING = 45;
    private String timeVal;
    private String pitch;

    private Integer xpos;
    private Integer ypos;
    private Image image;
    private static Integer staffPos;

    //Requires: The pitch of the note must be "0" or in the combination of a single letter followed by an
    //          integer number with no space in between. The letter can range from a - g inclusive
    //          and the number can range from 4-5
    //          the noteTime value for the note much be one of 1, 1/2, 1/4, 1/8, and 1/16.
    //EFFECT: Initializes each newly created Notes. Pitch of note is set to pitch.
    //        The timeNote (time value od note) is set to time.
    public NoteOrRest(String pitch, String time) {
        this.pitch = pitch;
        timeVal = time;
        xpos = 0;
        ypos = 0;
        staffPos = 1;
    }

    //EFFECT: saves Notes to Json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Pitch", pitch);
        json.put("Time Value", timeVal);
        json.put("xPos", xpos);
        json.put("yPos", ypos);
        json.put("Image", image);
        return json;
    }

    //Getters and Setters
    public String getTimeVal() {
        return timeVal;
    }

    public String getPitch() {
        return pitch;
    }

    public static Integer getSpacing() {
        return SPACING;
    }

    public void setTimeVal(String timeVal) {
        this.timeVal = timeVal;
    }

    public void setImage(Image img) {
        this.image = img;
    }

    public Image getImage() {
        return image;
    }

    public void setXpos(Integer posX) {
        xpos = posX;
    }


    public void setYpos(Integer posY) {
        ypos = posY;
    }

    public static void setStaffPos(Integer staffPos) {
        NoteOrRest.staffPos = staffPos;
    }

    public static Integer getStaffPos() {
        return staffPos;
    }

    public Integer getXpos() {
        return xpos;
    }

    public Integer getYpos() {
        return ypos;
    }
}
