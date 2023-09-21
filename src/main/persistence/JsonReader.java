package persistence;

import model.AllScore;
import model.Score;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


import model.NoteOrRest;
import org.json.*;

// Represents a reader that reads Score from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Score from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AllScore read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseScore(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Score from JSON object and returns it
    private AllScore parseScore(JSONObject jsonObject) {
        AllScore all = new AllScore();
        Score scr = new Score();
        //addThingies(scr, jsonObject);
        addToAll(all, scr, jsonObject);
        return all;
    }


    // MODIFIES: score
    // EFFECTS: parses each score from JSON object and adds them to allScore
    private void addToAll(AllScore all, Score scr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("ScoreList");
        for (Object json : jsonArray) {
            JSONObject nextScore = (JSONObject) json;
            addScores(all, scr, nextScore);
            //addThingies();
        }
    }

    // MODIFIES: score
    // EFFECTS: parses each NoteOrRest from JSON object and adds them to Score
    private void addThingies(Score scr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Score");
        for (Object json : jsonArray) {
            JSONObject nextNoteOrRest = (JSONObject) json;
            addNoteOrRest(scr, nextNoteOrRest);
        }
    }

    // MODIFIES: score
    // EFFECTS: parses NoteOrRest from JSON object and adds it to Score
    private void addScores(AllScore all, Score scr, JSONObject jsonObject) {
        all.addScore(scr);
    }


    // MODIFIES: score
    // EFFECTS: parses NoteOrRest from JSON object and adds it to Score
    private void addNoteOrRest(Score scr, JSONObject jsonObject) {
        String pitch = jsonObject.getString("Pitch");
        String time = jsonObject.getString("Time Value");
        String xpos = jsonObject.getString(" xPos");
        String ypos = jsonObject.getString(" yPos");
        String image = jsonObject.getString("Image");
        NoteOrRest nor = new NoteOrRest(pitch, time);
        scr.addNoteOrRest(nor);
    }
}
