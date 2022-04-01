package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a training log having a collection of training sessions
public class TrainingLog implements Writable {
    private String name;
    List<TrainingSession> trainingSessions;

    // EFFECTS: constructs training log with a name and empty list of training sessions
    public TrainingLog(String name) {
        this.name = name;
        trainingSessions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds training session to this training log
    public void addTrainingSession(TrainingSession trainingSession) {
        trainingSessions.add(trainingSession);
        EventLog.getInstance().logEvent(new Event("Added new session to training log"));
    }

    // MODIFIES: this
    // EFFECTS: removes training session from this training log
    public void removeTrainingSession() {
        trainingSessions.remove(getTrainingSessions().size() - 1);
        EventLog.getInstance().logEvent(new Event("Removed most recent session from training log"));
    }

    // EFFECTS: returns an unmodifiable list of training sessions in this training log
    public List<TrainingSession> getTrainingSessions() {
        return Collections.unmodifiableList(trainingSessions);
    }

    // EFFECTS: returns number of training sessions in this training log
    public int numTrainingSessions() {
        return trainingSessions.size();
    }

    // EFFECTS: displays all training sessions in training log to panel
    public String printTrainingSessions() {
        String workouts = "";
        for (TrainingSession t : trainingSessions) {
            workouts += ("\n\nWorkout Type: " + t.getName() + "\nWorkout Duration: "
                    + t.getDuration() + "\nWorkout Distance: " + t.getDistance()
                    + "\nWorkout Notes: " + t.getNotes() + "\nWorkout Effort: " + t.getEffort());
        }
        return workouts;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("training sessions", trainingSessionsToJson());
        return json;
    }

    // EFFECTS: returns training sessions in this training log as a JSON array
    private JSONArray trainingSessionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (TrainingSession t : trainingSessions) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}

