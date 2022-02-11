package model;

public class TrainingSession {
    private String name;
    private int duration;
    private double distance;
    private String notes;
    private int effort;

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

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public double getDistance() {
        return distance;
    }

    public String getNotes() {
        if (notes.isEmpty()) {
            return null;
        } else {
            return notes;
        }
    }

    public int getEffort() {
        return effort;
    }

}
