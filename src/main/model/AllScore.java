package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;


import java.util.ArrayList;

//a list of Scores
public class AllScore implements Writable {
    private ArrayList<Score> allScore;

    //MODIFIES: this
    //EFFECT: instantiates allScore
    public AllScore() {
        allScore = new ArrayList<>();
    }


    //EFFECT: returns Json
    @Override
    public  JSONObject toJson() {
        JSONObject jsonAll = new JSONObject();
        jsonAll.put("ScoreList", scoresToJson());
        return jsonAll;
    }

    //EFFECT: returns jsonArray
    private JSONArray scoresToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Score score : allScore) {
            jsonArray.put(score.toJson());
        }
        return jsonArray;
    }

    //EFFECT: prints out EventLog
    public static void printEventLog() {
        System.out.println("EventLog for this Session:");
        for (Event nextEvent: EventLog.getInstance()) {
            System.out.println(nextEvent.getDate() + "\n" + nextEvent.getDescription());
        }
    }


    //MODIFIES: this
    //EFFECT: adds given score to allScore
    public void addScore(Score scr) {
        allScore.add(scr);
    }



    //getters
    public Integer getSize() {
        return allScore.size();
    }

    public ArrayList<Score> getAll() {
        return allScore;
    }

    public Score getLine(Integer i) {
        return allScore.get(i - 1);
    }


}
