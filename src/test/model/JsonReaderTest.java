package model;

import model.TrainingSession;
import model.TrainingLog;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    // Test taken from JsonReaderTest class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TrainingLog wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    // Test taken from JsonWriterTest class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    @Test
    void testReaderEmptyTrainingLog() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTrainingLog.json");
        try {
            TrainingLog wr = reader.read();
            assertEquals("My training log", wr.getName());
            assertEquals(0, wr.numTrainingSessions());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTrainingLog() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTrainingLog.json");
        try {
            TrainingLog wr = reader.read();
            assertEquals("My training log", wr.getName());
            List<TrainingSession> trainingSessions = wr.getTrainingSessions();
            assertEquals(2, trainingSessions.size());
            checkTrainingSession("Run", 30, 6.0, " ", 6, trainingSessions.get(0));
            checkTrainingSession("Swim", 25, 1.6, "fun swim", 3, trainingSessions.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}