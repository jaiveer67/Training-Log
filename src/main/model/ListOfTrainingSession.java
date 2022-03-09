package model;

import java.util.ArrayList;

// Represents a list of training sessions
public class ListOfTrainingSession {
    ArrayList<TrainingSession> list;

    public ListOfTrainingSession() {
        list = new ArrayList<>();
    }

    /*
    MODIFIES: list
    EFFECTS: Adds training session to list
    */
    public void addSession(TrainingSession workout) {
        list.add(workout);
    }

    /*
    MODIFIES: list
    EFFECTS: Removes training session from list
    */
    public void removeSession(TrainingSession workout) {
        list.remove(workout);
    }

}

