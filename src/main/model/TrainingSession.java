package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a training session and it's different elements
public class TrainingSession implements Writable {
    private String name;
    private int duration;
    private double distance;
    private String notes;
    private int effort;
    private TrainingSession session;

    /*
    REQUIRES: duration >= 0, distance >= 0, 1 >= effort <= 10
    EFFECTS: name of training session is set to workoutType;
             duration of training session is set to workoutLength which is given in minutes;
             distance of training session is set to distanceTravelled which is given in kilometers;
             training session notes is set to workoutDescription;
             training effort is set to workoutEffort;
     */
    public TrainingSession(String workoutType, Integer workoutLength,
                           double distanceTravelled, String workoutDescription,
                           Integer workoutEffort) {
        this.name = workoutType;
        this.duration = workoutLength;
        this.distance = distanceTravelled;
        this.notes = workoutDescription;
        this.effort = workoutEffort;
    }

    // EFFECTS: returns name of training session
    public String getName() {
        return name;
    }

    // EFFECTS: returns duration of training session
    public int getDuration() {
        return duration;
    }

    // EFFECTS: returns distance of training session
    public double getDistance() {
        return distance;
    }

    // EFFECTS: returns notes from training session,
    //          returns empty string if no notes are provided
    public String getNotes() {
        if (notes.isEmpty()) {
            return " ";
        } else {
            return notes;
        }
    }

    // EFFECTS: returns effort of training session on a scale of [1-10]
    public int getEffort() {
        return effort;
    }

    // EFFECTS: returns string of training session info
    public String printSession() {
        return "\nWorkout Type: " + this.name + "\nWorkout Duration: "
                + this.duration + "\nWorkout Distance: " + this.distance
                + "\nWorkout Notes: " + this.notes + "\nWorkout Effort: " + this.effort;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("duration", duration);
        json.put("distance", distance);
        json.put("notes", notes);
        json.put("effort", effort);
        return json;
    }

}
