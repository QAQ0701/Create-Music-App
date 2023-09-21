package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AllScoreTest {
    AllScore all;
    Score scr1;
    Score scr2;
    Score scr3;
    Score scr4;
    NoteOrRest e4;
    NoteOrRest rest;

    @BeforeEach
    void runBefore() {
        all = new AllScore();
        scr1 = new Score();
        scr2 = new Score();
        scr3 = new Score();
        scr4 = new Score();
        e4 = new NoteOrRest("E4", "1");
        rest = new NoteOrRest("0", "1/2");
    }

    @Test
    void testAllScore() {
        all.addScore(scr1);
        all.addScore(scr2);
        all.addScore(scr3);
        all.addScore(scr4);
        assertEquals(4, all.getSize());

    }

    @Test
    void getSize() {
        all.addScore(scr1);
        all.addScore(scr2);
        all.addScore(scr3);
        all.addScore(scr4);
        assertEquals(4, all.getSize());
    }

    @Test
    void getLines() {
        all.addScore(scr1);
        all.addScore(scr2);
        all.addScore(scr3);
        all.addScore(scr4);
        assertEquals(scr1, all.getLine(1));
        assertEquals(scr2, all.getLine(2));
        assertEquals(scr3, all.getLine(3));
        assertEquals(scr4, all.getLine(4));
    }

    @Test
    void getAll() {
        all.addScore(scr1);
        all.addScore(scr2);
        all.addScore(scr3);
        all.addScore(scr4);
        ArrayList<Score> list= new ArrayList<>();
        list.add(scr1);
        list.add(scr2);
        list.add(scr3);
        list.add(scr4);
        assertEquals(list, this.all.getAll());

    }

}