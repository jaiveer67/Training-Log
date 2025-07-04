package ui;

import model.TrainingSession;
import model.TrainingLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Represents the TrainingLog Application
public class TrainingLogApp {

    private static final String JSON_STORE = "./data/trainingLog.json";
    private Scanner input;
    private Scanner scanner;
    private TrainingSession session;
    private TrainingLog trainingLog;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: Runs the TrainingLog application
    public TrainingLogApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        scanner = new Scanner(System.in);
        trainingLog = new TrainingLog("Jaiveer's training log");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTrainingLog();
    }

    // MODIFIES: this
    // EFFECTS: initiates training log
    private void runTrainingLog() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add training session");
        System.out.println("\tr -> remove training session");
        System.out.println("\tp -> print training sessions");
        System.out.println("\ts -> save work room to file");
        System.out.println("\tl -> load work room from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addTrainingSession();
        } else if (command.equals("r")) {
            removeTrainingSession();
        } else if (command.equals("p")) {
            printTrainingSessions();
        } else if (command.equals("s")) {
            saveTrainingLog();
        } else if (command.equals("l")) {
            loadTrainingLog();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user for workout info and adds session to training log
    private void addTrainingSession() {
        System.out.println("\nWhat type of workout would you like to enter");
        String workoutName = input.next();
        System.out.println("How many minutes did you " + workoutName + " for?");
        String workoutDuration = input.next();
        System.out.println("How many kilometers did you " + workoutName);
        String workoutDistance = input.next();
        System.out.println("Any comments on your " + workoutName);
        String workoutNotes = scanner.nextLine();
        System.out.println("What was your perceived effort during your " + workoutName + " from 1-10?");
        String workoutEffort = input.next();
        trainingLog.addTrainingSession(stringToSession(workoutName, workoutDuration,
                workoutDistance, workoutNotes, workoutEffort));
        displayWorkout(workoutName, workoutDuration, workoutDistance, workoutNotes, workoutEffort);
    }

    // MODIFIES: this
    // EFFECTS: asks user if they want to remove their previous training session
    private void removeTrainingSession() {
        System.out.println("\n Most recent session has been removed");
        trainingLog.removeTrainingSession();
    }

    // EFFECTS: prints all training sessions in training log to the console
    private void printTrainingSessions() {
        List<TrainingSession> trainingSessions = trainingLog.getTrainingSessions();

        for (TrainingSession t : trainingSessions) {
            System.out.println(t.printSession());
        }
    }

    // EFFECTS: displays workout data
    private void displayWorkout(String workoutType, String workoutLength,
                                String distanceTravelled, String workoutDescription, String workoutEffort) {
        System.out.println("\nWorkout Type: " + workoutType + "\nWorkout Duration: "
                + workoutLength + "\nWorkout Distance: " + distanceTravelled
                + "\nWorkout Notes: " + workoutDescription + "\nWorkout Effort: " + workoutEffort);
    }

    // EFFECTS: converts training session in string form to correct type
    private TrainingSession stringToSession(String workoutType, String workoutLength,
                                            String distanceTravelled, String workoutDescription, String workoutEffort) {
        session = new TrainingSession(workoutType, Integer.parseInt(workoutLength),
                Double.parseDouble(distanceTravelled), workoutDescription, Integer.parseInt(workoutEffort));
        return session;
    }

    // MODIFIES: this
    // EFFECTS: saves the training log to file
    private void saveTrainingLog() {
        try {
            jsonWriter.open();
            jsonWriter.write(trainingLog);
            jsonWriter.close();
            System.out.println("Saved " + trainingLog.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads training log from file
    private void loadTrainingLog() {
        try {
            trainingLog = jsonReader.read();
            System.out.println("Loaded " + trainingLog.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}

