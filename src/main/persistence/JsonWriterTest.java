package persistence;

import model.TrainingSession;
import model.WorkRoom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    // Test taken from JsonWriterTest class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    @Test
    void testWriterInvalidFile() {
        try {
            WorkRoom wr = new WorkRoom("My work room");
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
    void testWriterEmptyWorkroom() {
        try {
            WorkRoom wr = new WorkRoom("My work room");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            wr = reader.read();
            assertEquals("My work room", wr.getName());
            assertEquals(0, wr.numTrainingSessions());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            WorkRoom wr = new WorkRoom("My work room");
            wr.addTrainingSession(new TrainingSession("Run", 30, 6, "", 6));
            wr.addTrainingSession(new TrainingSession("Swim", 25, 1.6, "fun swim", 3));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            wr = reader.read();
            assertEquals("My work room", wr.getName());
            List<TrainingSession> trainingSessions = wr.getTrainingSessions();
            assertEquals(2, trainingSessions.size());
            checkTrainingSession("Run", 30, 6.0, "", 6, trainingSessions.get(0));
            checkTrainingSession("Swim", 25, 1.6, "fun swim", 3, trainingSessions.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}