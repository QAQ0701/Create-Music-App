package model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

//Represent a list of the NoteOrRest Object
public class Score implements Writable {
    private ArrayList<NoteOrRest> score;

    //EFFECT: Initializes each newly created ListOfNotesAndRest as an empty ArrayList
    public Score() {
        score = new ArrayList<>();
    }

    //REQUIRES: given position int must be <= size of array list
    //MODIFIES: this
    //EFFECT: Adds noteOrRest to list in position of given int
    public void insertNoteOrRest(NoteOrRest noteOrRest, int position) {
        score.add(position, noteOrRest);
    }

    //MODIFIES: this
    //EFFECT: Adds noteOrRest to end of list
    public void addNoteOrRest(NoteOrRest noteOrRest) {
        score.add(noteOrRest);
        EventLog.getInstance().logEvent(new Event("Note or Rest Added to Score"));
        /*if (noteOrRest.getPitch().equals("0")) {
            EventLog.getInstance().logEvent(new Event(noteOrRest.getTimeVal() + " Rest Added to Score"));
        } else {
            EventLog.getInstance().logEvent(new Event(noteOrRest.getTimeVal() + " Note Added to Score"));
        }*/
    }

    //REQUIRES: ListOfNoteAndRest must have at least one element
    //MODIFIES: this
    //EFFECT: removes noteOrRest with given index
    public void removeNoteOrRest(int position) {
        NoteOrRest noteOrRest = getNoteFromList(position);
        score.remove(position);
        EventLog.getInstance().logEvent(new Event("Note or Rest Removed from Score"));
        /*if (noteOrRest.getPitch().equals("0")) {
            EventLog.getInstance().logEvent(new Event(noteOrRest.getTimeVal() + " Rest Removed from Score"));
        } else {
            EventLog.getInstance().logEvent(new Event(noteOrRest.getTimeVal() + " Note Removed from Score"));
        }*/
    }

    //REQUIRES: ListOfNotesAndRest must have at least one Note
    //EFFECT: returns the NoteOrRest object in the given int index position
    public NoteOrRest getNoteFromList(int position) {
        return score.get(position);
    }

    //REQUIRES: ListOfNoteAndRest must have at least one Note
    //EFFECT: return position of given NoteOrRest object
    public int indexOfNote(NoteOrRest noteOrRest) {
        return score.indexOf(noteOrRest);
    }

    //EFFECT returns size of ListOfNotes
    public int getSizeOfScore() {
        return score.size();
    }

    //Requires: the given integer must be <= to the size of ListOfNoteAndRest Object (elements).
    //          All NotesOrRest must adhere to the requires of its constructor.
    //EFFECTS: returns true if the given index integer corresponds to a note in the elements list
    //         else, return false.
    public boolean isRest(Integer pos) {
        NoteOrRest isRest = getNoteFromList(pos);
        String pitch = isRest.getPitch();
        if (pitch.equals("0")) {
            return true;
        } else {
            return false;
        }
    }


    //EFFECT: saves score to Json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Score", scoreToJson());
        return json;
    }

   //EFFECT: returns jsonArray
    private JSONArray scoreToJson() {
        JSONArray jsonArray = new JSONArray();
        for (NoteOrRest nor : score) {
            jsonArray.put(nor.toJson());
        }
        return jsonArray;
    }

}
