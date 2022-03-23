package persistence;

import model.TrainingSession;
import model.TrainingLog;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads training log from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }


    // Method taken from JsonReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    // EFFECTS: reads training log from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TrainingLog read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTrainingLog(jsonObject);
    }

    // Method taken from JsonWriterTest class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses training log from JSON object and returns it
    private TrainingLog parseTrainingLog(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        TrainingLog wr = new TrainingLog(name);
        addTrainingSessions(wr, jsonObject);
        return wr;
    }

    // MODIFIES: wr
    // EFFECTS: parses training sessions from JSON object and adds them to training log
    private void addTrainingSessions(TrainingLog wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("training sessions");
        for (Object json : jsonArray) {
            JSONObject nextTrainingSession = (JSONObject) json;
            addTrainingSession(wr, nextTrainingSession);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses training session from JSON object and adds it to training log
    private void addTrainingSession(TrainingLog wr, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Integer duration = jsonObject.getInt("duration");
        Double distance = jsonObject.getDouble("distance");
        String notes = jsonObject.getString("notes");
        Integer effort = jsonObject.getInt("effort");
        TrainingSession trainingSession = new TrainingSession(name, duration, distance, notes, effort);
        wr.addTrainingSession(trainingSession);
    }
}
