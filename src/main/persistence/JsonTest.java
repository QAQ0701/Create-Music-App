package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Used by JsonReader and JsonWriter Test classes
public class JsonTest {
    //EFFECT: checks given pitch with given note.
    protected void checkNoteOrRest(String pitch, String time, NoteOrRest nor) {
        assertEquals(pitch, nor.getPitch());
        assertEquals(time, nor.getTimeVal());
    }

    /*//EFFECT: checks given score with first 2 lines in allScore
    protected void checkAllScore(AllScore all, Score score, Score score2) {
        assertEquals(score, all.getLine(1));
        assertEquals(score2, all.getLine(2));
    }*/
}
