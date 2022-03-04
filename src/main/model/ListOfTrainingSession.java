package model;

import java.util.ArrayList;
import java.util.List;

public class ListOfTrainingSession {
    ArrayList<TrainingSession> list;
    String completedSessions = " ";

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

    public String printSessions() {
        for (int i = 0; i < list.size(); i++) {
            completedSessions += "\nWorkout Type: " + list.get(i).getName() + "\nWorkout Duration: "
                    + list.get(i).getDuration() + "\nWorkout Distance: " + list.get(i).getDistance()
                    + "\nWorkout Notes: " + list.get(i).getNotes() + "\nWorkout Effort: " + list.get(i).getEffort();
        }
        return completedSessions;
    }
}

