package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.ImageIconLibrary;
import ui.PainterNoteRest;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

//To test methods in the NoteOrRest class
class NoteOrRestTest {
    private NoteOrRest noteOrRestTest;
    private NoteOrRest noteOrRestTest2;
    private static Integer xPos;
    private static Integer yPos;
    private static Integer staffPos;
    private Image quarterNote;

    @BeforeEach
    void runBefore() {
        ImageIconLibrary library = new ImageIconLibrary();
        noteOrRestTest = new NoteOrRest("C4", "1");
        noteOrRestTest2 = new NoteOrRest("0", "1/2");
        quarterNote = ImageIconLibrary.getQuarterNoteScaled().getImage();

    }

    @Test
    void TestGetTimeVal() {
        assertEquals("1", noteOrRestTest.getTimeVal());
    }

    @Test
    void TestGetPitch() {
        assertEquals("C4", noteOrRestTest.getPitch());
        assertEquals("0", noteOrRestTest2.getPitch());
    }

    @Test
    void TestSetNoteTime() {
        noteOrRestTest.setTimeVal("1/4");
        assertEquals("1/4", noteOrRestTest.getTimeVal());
    }

    @Test
    void testSetxPos() {
        noteOrRestTest.setXpos(1);
        assertEquals(1, noteOrRestTest.getXpos());
    }

    @Test
    void testSetyPos() {
        noteOrRestTest.setYpos(1);
        assertEquals(1, noteOrRestTest.getYpos());
    }

    @Test
    void testSetStaffPos() {
        noteOrRestTest.setStaffPos(1);
        assertEquals(1, noteOrRestTest.getStaffPos());
    }

    @Test
    void getStaffPos() {
        noteOrRestTest.setStaffPos(1);
        assertEquals(1, noteOrRestTest.getStaffPos());
    }

    @Test
    void getxPos() {
        noteOrRestTest.setXpos(1);
        assertEquals(1, noteOrRestTest.getXpos());
    }

    @Test
    void getyPos() {
        noteOrRestTest.setYpos(1);
        noteOrRestTest.getYpos();

        assertEquals(1, noteOrRestTest.getYpos());
    }

    @Test
    void setImage() {
        noteOrRestTest.setImage(quarterNote);
        assertEquals(quarterNote, noteOrRestTest.getImage());
    }


    @Test
    void getImage() {
        noteOrRestTest.setImage(quarterNote);
        assertEquals(quarterNote, noteOrRestTest.getImage());
    }
}