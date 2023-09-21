package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

import model.*;

//To test methods in the JsonReader class
class JsonReaderTest extends JsonTest {

    private Score scr1;
    private Score scr2;
    private NoteOrRest noteOrRestTest1;
    private NoteOrRest noteOrRestTest2;
    private NoteOrRest noteOrRestTest3;

    @BeforeEach
    public void runBefore() {
        noteOrRestTest1 = new NoteOrRest("0", "1");
        noteOrRestTest2 = new NoteOrRest("0", "1/16");
        noteOrRestTest3 = new NoteOrRest("B4", "1/2");

        scr1 = new Score();
        scr1.addNoteOrRest(noteOrRestTest1);
        scr1.addNoteOrRest(noteOrRestTest2);
        scr1.addNoteOrRest(noteOrRestTest3);
        scr2 = new Score();
        scr2.addNoteOrRest(noteOrRestTest2);
        scr2.addNoteOrRest(noteOrRestTest3);
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            AllScore scoreNonExistent = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyScore() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyScore.json");
        try {
            AllScore listEmpty = reader.read();
            assertEquals(0, listEmpty.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralScore() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralScore.json");
        try {
            AllScore all = new AllScore();
            all.addScore(scr1);
            all.addScore(scr2);
            JsonWriter writer = new JsonWriter("./data/testReaderGeneralScore.json");
            writer.open();
            writer.write(all);
            writer.close();
            all = reader.read();

            assertEquals(3, scr1.getSizeOfScore());
            checkNoteOrRest("0","1", scr1.getNoteFromList(0));
            checkNoteOrRest("0","1/16", scr1.getNoteFromList(1));
            checkNoteOrRest("B4","1/2", scr1.getNoteFromList(2));
            checkNoteOrRest("0","1/16", scr2.getNoteFromList(0));
            checkNoteOrRest("B4","1/2", scr2.getNoteFromList(1));
            //checkAllScore(all, scr1, scr2);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}