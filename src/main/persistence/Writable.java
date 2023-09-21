package persistence;

import org.json.JSONObject;

//Implemented by Score and NoteOrRest
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
