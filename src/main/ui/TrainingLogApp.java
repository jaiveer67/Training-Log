package ui;

import model.ListOfTrainingSession;
import model.TrainingSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TrainingLogApp {

    private Scanner input;
    private Scanner scanner;
    private ListOfTrainingSession trainingSessions;
    private TrainingSession session;
    private String workoutName;
    private String minutes;
    private String distance;
    private String comments;
    private String seeTrainingLog;
    private String addSession;
    private String removeSession;
    private String effort;

    // EFFECTS: Runs the TrainingLog application
    public TrainingLogApp() {
        runTrainingLog();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTrainingLog() {
        boolean keepGoing = true;
        init();
        while (keepGoing) {
            execute();
            addSession = input.next();
            if (addSession.equals("N")) {
                keepGoing = false;
            } else {
                runTrainingLog();
            }
        }
    }

    // EFFECTS: Executes program
    private void execute() {
        workoutName = input.next();
        processWorkoutTime(workoutName);
        minutes = input.next();
        processWorkoutDistance(workoutName);
        distance = input.next();
        processWorkoutNotes(workoutName);
        comments = scanner.nextLine();
        processWorkoutEffort(workoutName);
        effort = input.next();
        displayWorkout(workoutName, minutes, distance, comments, effort);
        stringToSession(workoutName, minutes, distance, comments, effort);
        System.out.println("Would you like to see your training log? Y/N");
        seeTrainingLog = input.next();
        viewTrainingLog(seeTrainingLog);
        removeSession = input.next();
        removePreviousSession(removeSession);
    }

    // EFFECTS: If user wants to view training log
    private void viewTrainingLog(String command) {
        if (command.equals("Y")) {
            System.out.println(trainingSessions.printSessions());
        }
        System.out.println("Would you like to remove the previous training session? Y/N");
    }

    // EFFECTS: If user wants to remove previous training session
    private void removePreviousSession(String command) {
        if (command.equals("Y")) {
            trainingSessions.removeSession(session);
            System.out.println("Session removed!");
        }
        System.out.println("Would you like to add another training session? Y/N");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processWorkoutTime(String command) {
        System.out.println("How many minutes did you " + command + " for?");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processWorkoutDistance(String command) {
        System.out.println("How many kilometers did you " + command);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processWorkoutNotes(String command) {
        System.out.println("Any comments on your " + command);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processWorkoutEffort(String command) {
        System.out.println("What was your perceived effort during your " + command + " from 1-10?");
    }

    // MODIFIES: this
    // EFFECTS: initializes trainingSessions and begins user input
    private void init() {
        trainingSessions = new ListOfTrainingSession();
        System.out.println("\nWhat type of workout would you like to enter");
        input = new Scanner(System.in);
        scanner = new Scanner(System.in);
    }

    //EFFECTS: displays workout data
    private void displayWorkout(String workoutType, String workoutLength,
                                String distanceTravelled, String workoutDescription, String workoutEffort) {
        System.out.println("\nWorkout Type: " + workoutType + "\nWorkout Duration: "
                + workoutLength + "\nWorkout Distance: " + distanceTravelled
                + "\nWorkout Notes: " + workoutDescription + "\nWorkout Effort: " + workoutEffort);
    }

    private void stringToSession(String workoutType, String workoutLength,
                                 String distanceTravelled, String workoutDescription, String workoutEffort) {
        session = new TrainingSession(workoutType, Integer.parseInt(workoutLength),
                Double.parseDouble(distanceTravelled), workoutDescription, Integer.parseInt(workoutEffort));
        trainingSessions.addSession(session);
    }


}

