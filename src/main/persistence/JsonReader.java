package persistence;

import model.TrainingSession;
import model.WorkRoom;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }


    // Method taken from JsonReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WorkRoom read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
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

    // EFFECTS: parses workroom from JSON object and returns it
    private WorkRoom parseWorkRoom(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        WorkRoom wr = new WorkRoom(name);
        addTrainingSessions(wr, jsonObject);
        return wr;
    }

    // MODIFIES: wr
    // EFFECTS: parses training sessions from JSON object and adds them to workroom
    private void addTrainingSessions(WorkRoom wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("training sessions");
        for (Object json : jsonArray) {
            JSONObject nextTrainingSession = (JSONObject) json;
            addTrainingSession(wr, nextTrainingSession);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses training session from JSON object and adds it to workroom
    private void addTrainingSession(WorkRoom wr, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Integer duration = jsonObject.getInt("duration");
        Double distance = jsonObject.getDouble("distance");
        String notes = jsonObject.getString("notes");
        Integer effort = jsonObject.getInt("effort");
        TrainingSession trainingSession = new TrainingSession(name, duration, distance, notes, effort);
        wr.addTrainingSession(trainingSession);
    }
}
