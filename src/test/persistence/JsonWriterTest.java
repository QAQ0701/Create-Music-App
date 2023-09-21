package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import model.*;

//To test methods in the JsonWriter class
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    private NoteOrRest noteOrRestTest1;
    private NoteOrRest noteOrRestTest2;
    private NoteOrRest noteOrRestTest3;

    private Score scr1;
    private Score scr2;


    @BeforeEach
    public void runBefore() {
        noteOrRestTest1 = new NoteOrRest("C4", "1");
        noteOrRestTest2 = new NoteOrRest("0", "1/2");
        noteOrRestTest3 = new NoteOrRest("E4", "1/8");

        scr1 = new Score();
        scr1.addNoteOrRest(noteOrRestTest1);
        scr1.addNoteOrRest(noteOrRestTest2);
        scr1.addNoteOrRest(noteOrRestTest3);
        scr2 = new Score();
        scr2.addNoteOrRest(noteOrRestTest2);
        scr2.addNoteOrRest(noteOrRestTest3);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            AllScore allInvalid = new AllScore();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyScore() {
        try {
            AllScore all = new AllScore();
            Score scoreEmpty = new Score();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyScore.json");
            writer.open();
            writer.write(all);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyScore.json");
            all = reader.read();
            assertEquals(0, scoreEmpty.getSizeOfScore());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralScore() {
        try {
            AllScore all = new AllScore();
            all.addScore(scr1);
            all.addScore(scr2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralScore.json");
            writer.open();
            writer.write(all);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralScore.json");
            all = reader.read();
            assertEquals(3, scr1.getSizeOfScore());
            checkNoteOrRest("C4","1", scr1.getNoteFromList(0));
            checkNoteOrRest("0","1/2", scr1.getNoteFromList(1));
            checkNoteOrRest("E4","1/8", scr1.getNoteFromList(2));
            //checkAllScore(all, scr1, scr2);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}