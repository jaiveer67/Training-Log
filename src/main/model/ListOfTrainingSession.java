package model;

import java.util.ArrayList;
import java.util.*;
import java.util.List;

public class ListOfTrainingSession {
    List<TrainingSession> list;


    public ListOfTrainingSession() {
        list = new ArrayList<TrainingSession>();
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

