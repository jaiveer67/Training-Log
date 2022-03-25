package model;

import model.TrainingSession;
import model.TrainingLog;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    // Test taken from JsonWriterTest class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    @Test
    void testWriterInvalidFile() {
        try {
            TrainingLog wr = new TrainingLog("My work room");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    // Test taken from JsonWriterTest class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    @Test
    void testWriterEmptyTrainingLog() {
        try {
            TrainingLog tl = new TrainingLog("My work room");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTrainingLog.json");
            writer.open();
            writer.write(tl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTrainingLog.json");
            tl = reader.read();
            assertEquals("My work room", tl.getName());
            assertEquals(0, tl.numTrainingSessions());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            TrainingLog tl = new TrainingLog("My work room");
            tl.addTrainingSession(new TrainingSession("Run", 30, 6, "", 6));
            tl.addTrainingSession(new TrainingSession("Swim", 25, 1.6, "fun swim", 3));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTrainingLog.json");
            writer.open();
            writer.write(tl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTrainingLog.json");
            tl = reader.read();
            assertEquals("My work room", tl.getName());
            List<TrainingSession> trainingSessions = tl.getTrainingSessions();
            assertEquals(2, trainingSessions.size());
            checkTrainingSession("Run", 30, 6.0, " ", 6, trainingSessions.get(0));
            checkTrainingSession("Swim", 25, 1.6, "fun swim", 3, trainingSessions.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}