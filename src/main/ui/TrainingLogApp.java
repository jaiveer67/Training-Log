package ui;

import model.ListOfTrainingSession;
import model.TrainingSession;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class TrainingLogApp {

    private Scanner input;
    private Scanner scanner;
    private ListOfTrainingSession trainingSessions;
    private TrainingSession session;


    // EFFECTS: Runs the TrainingLog application
    public TrainingLogApp() {
        runTrainingLog();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void runTrainingLog() {
        boolean keepGoing = true;
        String command1 = null;
        String command2 = null;
        String command3 = null;
        String command4 = null;
        String command5 = null;
        String command6 = null;
        String command7 = null;
        String command8 = null;
        init();
        while (keepGoing) {
            command1 = input.next();
            processCommand1(command1);
            command2 = input.next();
            processCommand2(command1);
            command3 = input.next();
            processCommand3(command1);
            command4 = scanner.nextLine();
            processCommand4(command1);
            command8 = input.next();
            displayWorkout(command1, command2, command3, command4, command8);
            session = new TrainingSession(command1, Integer.parseInt(command2),
                    Double.parseDouble(command3), command4, Integer.parseInt(command8));
            trainingSessions.addSession(session);
            System.out.println("Would you like to see your training log? Y/N");
            command5 = input.next();
            if (command5.equals("Y")) {
                System.out.println(trainingSessions);
                System.out.println("Would you like to remove the previous training session? Y/N");
                command7 = input.next();
                if (command7.equals("Y")) {
                    trainingSessions.removeSession(session);
                    System.out.println("Session removed!");
                    System.out.println("Would you like to add another training session? Y/N");
                    command6 = input.next();
                    if (command6.equals("N")) {
                        keepGoing = false;
                    } else {
                        runTrainingLog();
                    }
                } else {
                    {
                        System.out.println("Would you like to add another training session? Y/N");
                        command6 = input.next();
                        if (command6.equals("N")) {
                            keepGoing = false;
                        } else {
                            runTrainingLog();
                        }
                    }
                }
            } else {
                System.out.println("Would you like to remove the previous training session? Y/N");
                command7 = input.next();
                if (command7.equals("Y")) {
                    trainingSessions.removeSession(session);
                    System.out.println("Session removed!");
                    System.out.println("Would you like to add another training session? Y/N");
                    command6 = input.next();
                    if (command6.equals("N")) {
                        keepGoing = false;
                    } else {
                        runTrainingLog();
                    }
                } else {
                    System.out.println("Would you like to add another training session? Y/N");
                    command6 = input.next();
                    if (command6.equals("N")) {
                        keepGoing = false;
                    } else {
                        runTrainingLog();
                    }
                }
            }

        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand1(String command) {
        System.out.println("How many minutes did you " + command + " for?");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand2(String command) {
        System.out.println("How many kilometers did you " + command);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand3(String command) {
        System.out.println("Any comments on your " + command);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand4(String command) {
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


}
