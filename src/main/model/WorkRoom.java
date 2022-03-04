package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a workroom having a collection of training sessions
public class WorkRoom implements Writable {
    private String name;
    private List<TrainingSession> trainingSessions;

    // EFFECTS: constructs workroom with a name and empty list of training sessions
    public WorkRoom(String name) {
        this.name = name;
        trainingSessions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds training session to this workroom
    public void addTrainingSession(TrainingSession trainingSession) {
        trainingSessions.add(trainingSession);
    }

    // MODIFIES: this
    // EFFECTS: removes training session from this workroom
    public void removeTrainingSession() {
        trainingSessions.remove(getTrainingSessions().size() - 1);
    }

    // EFFECTS: returns an unmodifiable list of training sessions in this workroom
    public List<TrainingSession> getTrainingSessions() {
        return Collections.unmodifiableList(trainingSessions);
    }

    // EFFECTS: returns number of training sessions in this workroom
    public int numTrainingSessions() {
        return trainingSessions.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("training sessions", trainingSessionsToJson());
        return json;
    }

    // EFFECTS: returns training sessions in this workroom as a JSON array
    private JSONArray trainingSessionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (TrainingSession t : trainingSessions) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}

