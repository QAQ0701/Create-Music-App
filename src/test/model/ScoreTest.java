package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//To test methods in the Score class
class ScoreTest {
    //private ListOfNotes noteListEmpty;
    private Score scoreTest;
    private Score ScoreIsEmpty;
    private NoteOrRest noteOrRestTest1;
    private NoteOrRest noteOrRestTest2;
    private NoteOrRest noteOrRestTest3;
    private NoteOrRest noteOrRestTest4;



    @BeforeEach
    public void runBefore() {
        scoreTest = new Score();
        ScoreIsEmpty = new Score();

        noteOrRestTest1 = new NoteOrRest("C4", "1");
        noteOrRestTest2 = new NoteOrRest("D4", "1/2");
        noteOrRestTest3 = new NoteOrRest("0", "1/4");
        noteOrRestTest4 = new NoteOrRest("C4", "1");

    }

    @Test
    public void TestInsertNote() {
        scoreTest.insertNoteOrRest(noteOrRestTest1, 0);
        assertEquals(noteOrRestTest1, scoreTest.getNoteFromList(0));
        scoreTest.insertNoteOrRest(noteOrRestTest2, 1);
        scoreTest.insertNoteOrRest(noteOrRestTest3, 2);
        scoreTest.insertNoteOrRest(noteOrRestTest4, 3);
        assertEquals(noteOrRestTest2, scoreTest.getNoteFromList(1));
        assertEquals(noteOrRestTest3, scoreTest.getNoteFromList(2));
        assertEquals(noteOrRestTest4, scoreTest.getNoteFromList(3));

        scoreTest.insertNoteOrRest(noteOrRestTest2, 2);//insert to pos 2

        assertEquals(noteOrRestTest1, scoreTest.getNoteFromList(0));
        assertEquals(noteOrRestTest2, scoreTest.getNoteFromList(1));
        assertEquals(noteOrRestTest2, scoreTest.getNoteFromList(2));
        assertEquals(noteOrRestTest3, scoreTest.getNoteFromList(3));
        assertEquals(noteOrRestTest4, scoreTest.getNoteFromList(4));

    }

    @Test
    public void TestAddNoteOrRest() {
        scoreTest.addNoteOrRest(noteOrRestTest1);
        assertEquals(noteOrRestTest1, scoreTest.getNoteFromList(0));
        scoreTest.addNoteOrRest(noteOrRestTest2);
        scoreTest.addNoteOrRest(noteOrRestTest3);
        scoreTest.addNoteOrRest(noteOrRestTest4);
        assertEquals(noteOrRestTest2, scoreTest.getNoteFromList(1));
        assertEquals(noteOrRestTest3, scoreTest.getNoteFromList(2));
        assertEquals(noteOrRestTest4, scoreTest.getNoteFromList(3));



    }


    @Test
    public void TestRemoveNoteOrRest() {
        scoreTest.insertNoteOrRest(noteOrRestTest1, 0);
        assertEquals(noteOrRestTest1, scoreTest.getNoteFromList(0));
        scoreTest.removeNoteOrRest(0);
        assertEquals( 0, scoreTest.getSizeOfScore());
        scoreTest.insertNoteOrRest(noteOrRestTest3, 0);
        scoreTest.insertNoteOrRest(noteOrRestTest2, 1);
        scoreTest.insertNoteOrRest(noteOrRestTest4, 2);
        scoreTest.insertNoteOrRest(noteOrRestTest1, 3);

        scoreTest.removeNoteOrRest(3);//remove note from end of list
        assertEquals(3, scoreTest.getSizeOfScore());
        assertEquals(noteOrRestTest4, scoreTest.getNoteFromList(2));
        assertEquals(noteOrRestTest2, scoreTest.getNoteFromList(1));
        assertEquals(noteOrRestTest3, scoreTest.getNoteFromList(0));

        scoreTest.removeNoteOrRest(1); //remove note from middle of list
        assertEquals(noteOrRestTest3, scoreTest.getNoteFromList(0));
        assertEquals(noteOrRestTest4, scoreTest.getNoteFromList(1));
        assertEquals(2, scoreTest.getSizeOfScore());

    }


    @Test
    public void TestGetNoteOrRestFromList() {
        scoreTest.insertNoteOrRest(noteOrRestTest3, 0);
        scoreTest.insertNoteOrRest(noteOrRestTest2, 1);
        scoreTest.insertNoteOrRest(noteOrRestTest4, 2);
        scoreTest.insertNoteOrRest(noteOrRestTest1, 3);

        assertEquals(noteOrRestTest1, scoreTest.getNoteFromList(3));
        assertEquals(noteOrRestTest3, scoreTest.getNoteFromList(0));
        assertEquals(noteOrRestTest4, scoreTest.getNoteFromList(2));
    }


    @Test
    public void TestIndexOfNoteOrRest() {
        scoreTest.insertNoteOrRest(noteOrRestTest3, 0);
        scoreTest.insertNoteOrRest(noteOrRestTest2, 1);
        scoreTest.insertNoteOrRest(noteOrRestTest4, 2);
        assertEquals(0, scoreTest.indexOfNote(noteOrRestTest3));
        assertEquals(1, scoreTest.indexOfNote(noteOrRestTest2));
        assertEquals(2, scoreTest.indexOfNote(noteOrRestTest4));
    }

    @Test
    public void TestGetSizeListOfNoteAndRest() {
        assertEquals(0, ScoreIsEmpty.getSizeOfScore());
        scoreTest.insertNoteOrRest(noteOrRestTest3, 0);
        assertEquals(1, scoreTest.getSizeOfScore());
        scoreTest.insertNoteOrRest(noteOrRestTest2, 1);
        assertEquals(2, scoreTest.getSizeOfScore());
        scoreTest.insertNoteOrRest(noteOrRestTest4, 2);
        assertEquals(3, scoreTest.getSizeOfScore());
    }

    @Test
    public void TestisRest() {
        scoreTest.addNoteOrRest(noteOrRestTest1);
        scoreTest.addNoteOrRest(noteOrRestTest3);

        assertFalse(scoreTest.isRest(0)); //noteOrRestTest1
        assertTrue(scoreTest.isRest(1)); //noteOrRestTest3
    }
}