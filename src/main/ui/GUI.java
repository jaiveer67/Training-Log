
package ui;

import model.Event;
import model.EventLog;
import model.TrainingLog;
import model.TrainingSession;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener {
    private JPanel mainMenu;
    private JPanel sessionPage;
    private JPanel trainingLog;
    private TrainingSession workout;
    private TrainingLog log;
    private JButton addSessionButton;
    private JButton removeSessionButton;
    private JButton printSessionsButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton exitButton;
    private JButton addToLogButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JLabel type;
    private JLabel duration;
    private JLabel distance;
    private JLabel notes;
    private JLabel effort;
    private JLabel workouts;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Creates a new JFrame to interact with program
    public GUI() {
        super("Training Log");
        jsonWriter = new JsonWriter("./data/trainingLog.json");
        jsonReader = new JsonReader("./data/trainingLog.json");
        log = new TrainingLog("Jaiveer's Training log");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println(printLog(EventLog.getInstance()));
            }
        });
        setPreferredSize(new Dimension(800, 800));
        displayMenu();
        trainingLogPanel();
        addSessionPanel();
        createButtons();
        mainMenu.setVisible(true);
    }

    // EFFECTS: Create and display main menu
    public void displayMenu() {
        mainMenu = new JPanel();
        add(mainMenu);
        workouts = new JLabel();
        ImageIcon mainMenuImage = new ImageIcon("./data/RacePic.jpeg");
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(mainMenuImage);
        mainMenu.add(imageLabel);
    }

    public void createButtons() {
        initializeButtons();
        addButton(addSessionButton, mainMenu);
        addButton(removeSessionButton, mainMenu);
        addButton(printSessionsButton, mainMenu);
        addButton(saveButton, mainMenu);
        addButton(loadButton, mainMenu);
        addButton(exitButton, mainMenu);
        setActionToButtons();
    }

    //EFFECTS: Initialize main menu buttons
    public void initializeButtons() {
        addSessionButton = new JButton("Add Session");
        formatButton(addSessionButton);
        removeSessionButton = new JButton("Remove Session");
        formatButton(removeSessionButton);
        printSessionsButton = new JButton("View Training Log");
        formatButton(printSessionsButton);
        saveButton = new JButton("Save Training Log");
        formatButton(saveButton);
        loadButton = new JButton("Load Training Log");
        formatButton(loadButton);
        exitButton = new JButton("Exit application");
        formatButton(exitButton);
    }

    // MODIFIES: this
    // EFFECTS: adds button to provided panel
    public void addButton(JButton button, JPanel panel) {
        button.setSize(30, 30);
        panel.add(button);
        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Sets buttons up with their actions
    public void setActionToButtons() {
        addSessionButton.addActionListener(this);
        addSessionButton.setActionCommand("Add Session");
        removeSessionButton.addActionListener(this);
        removeSessionButton.setActionCommand("Remove Session");
        printSessionsButton.addActionListener(this);
        printSessionsButton.setActionCommand("View Training Log");
        saveButton.addActionListener(this);
        saveButton.setActionCommand("Save Training Log");
        loadButton.addActionListener(this);
        loadButton.setActionCommand("Load Training Log");
        exitButton.addActionListener(this);
        exitButton.setActionCommand("Exit application");
    }

    // EFFECTS: changes appearance of button
    public void formatButton(JButton button) {
        button.setFont(new Font("Lato", Font.BOLD, 30));
    }

    @Override
    // EFFECTS: performs indicated action when button is pressed
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Session")) {
            addTrainingSession();
        } else if (e.getActionCommand().equals("Add workout to log")) {
            addTrainingSessionToLog();
        } else if (e.getActionCommand().equals("Remove Session")) {
            removeTrainingSession();
        } else if (e.getActionCommand().equals("View Training Log")) {
            displayLogPanel();
        } else if (e.getActionCommand().equals("Save Training Log")) {
            saveLog();
        } else if (e.getActionCommand().equals("Load Training Log")) {
            loadLog();
        } else if (e.getActionCommand().equals("Return to main menu")) {
            backToMainMenu();
        } else if (e.getActionCommand().equals("Exit application")) {
            System.out.println(printLog(EventLog.getInstance()));
            System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates panel for adding a new training session
    public void addSessionPanel() {
        sessionPage = new JPanel(new GridLayout(0, 2));
        addMainMenuButton(sessionPage);
        createSessionsPage();
        addSectionsToSession();
    }

    // EFFECTS: Displays panel for adding a new training session
    public void addTrainingSession() {
        add(sessionPage);
        sessionPage.setVisible(true);
        mainMenu.setVisible(false);
        trainingLog.setVisible(false);
    }

    // EFFECTS: Create fields for user to input training session
    public void createSessionsPage() {
        addToLogButton = new JButton("Add workout to log");
        formatButton(addToLogButton);
        addToLogButton.setActionCommand("Add workout to log");
        addToLogButton.addActionListener(this);
        addButton(addToLogButton, sessionPage);

        type = new JLabel("Type of Workout:");
        type.setFont(new Font("Lato", Font.BOLD, 40));
        textField1 = new JTextField(5);
        duration = new JLabel("Length (min):");
        duration.setFont(new Font("Lato", Font.BOLD, 40));
        textField2 = new JTextField(5);
        distance = new JLabel("Distance(km):");
        distance.setFont(new Font("Lato", Font.BOLD, 40));
        textField3 = new JTextField(5);
        notes = new JLabel("Notes:");
        notes.setFont(new Font("Lato", Font.BOLD, 40));
        textField4 = new JTextField(5);
        effort = new JLabel("Effort Rating 1-10:");
        effort.setFont((new Font("Lato", Font.BOLD, 40)));
        textField5 = new JTextField(5);
    }

    // EFFECTS: Adds created fields for user to session page
    public void addSectionsToSession() {
        sessionPage.add(type);
        sessionPage.add(textField1);
        sessionPage.add(duration);
        sessionPage.add(textField2);
        sessionPage.add(distance);
        sessionPage.add(textField3);
        sessionPage.add(notes);
        sessionPage.add(textField4);
        sessionPage.add(effort);
        sessionPage.add(textField5);
    }

    // EFFECTS: adds training session to log
    public void addTrainingSessionToLog() {

        try {
            workout = stringToSession(textField1.getText(), textField2.getText(),
                    textField3.getText(), textField4.getText(), textField5.getText());

            log.addTrainingSession(workout);
            workouts.setText("<html><pre>Recorded Workouts: \n" + log.printTrainingSessions() + "\n </pre></html> \n");

        } catch (NumberFormatException e) {
            System.out.println("Invalid input, please try again");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error");
        }
    }

    // EFFECTS: removes most recent training session
    public void removeTrainingSession() {
        try {
            log.removeTrainingSession();
            workouts.setText("<html><pre>Recorded Workouts: \n" + log.printTrainingSessions() + "\n </pre></html> \n");
        } catch (NullPointerException e) {
            System.out.println("Log is empty");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("error");
        }
    }

    // EFFECTS: converts training session in string form to correct type
    public TrainingSession stringToSession(String workoutType, String workoutLength,
                                           String distanceTravelled, String workoutDescription, String workoutEffort) {
        TrainingSession session = new TrainingSession(workoutType, Integer.parseInt(workoutLength),
                Double.parseDouble(distanceTravelled), workoutDescription, Integer.parseInt(workoutEffort));
        return session;
    }

    // EFFECTS: creates panel for viewing recorded training sessions
    public void trainingLogPanel() {
        trainingLog = new JPanel(new GridLayout(1, 2));
        addMainMenuButton(trainingLog);
        workouts.setFont(new Font("Lato", Font.BOLD, 20));
        trainingLog.add(workouts);
    }

    // EFFECTS: Displays training log panel
    public void displayLogPanel() {
        add(trainingLog);
        trainingLog.setVisible(true);
        mainMenu.setVisible(false);
        sessionPage.setVisible(false);
    }

    // EFFECTS: creates mainMenuButton on provided panel
    public void addMainMenuButton(JPanel panel) {
        JButton mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.setActionCommand("Return to main menu");
        mainMenuButton.addActionListener(this);
        formatButton(mainMenuButton);
        panel.add(mainMenuButton);
    }

    // EFFECTS: Returns user to the main menu
    public void backToMainMenu() {
        mainMenu.setVisible(true);
        trainingLog.setVisible(false);
        sessionPage.setVisible(false);
    }

    public String printLog(EventLog el) {
        String output = "";
        for (Event next : el) {
            output += next.toString() + "\n\n";
        }
        return output;
    }

    // EFFECTS: Saves inputted training sessions to file
    public void saveLog() {
        try {
            jsonWriter.open();
            jsonWriter.write(log);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to file: " + "./data/trainingLog.json");
        }
    }

    // EFFECTS: Loads stored training sessions from file
    public void loadLog() {
        try {
            log = jsonReader.read();
            workouts.setText("<html><pre>Recorded Workouts: \n" + log.printTrainingSessions() + "\n </pre></html> \n");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + "./data/trainingLog.json");
        }
    }
}
