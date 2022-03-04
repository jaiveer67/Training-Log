package persistence;

import model.TrainingSession;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTrainingSession(String name, Integer duration, Double distance,
                                        String notes, Integer effort, TrainingSession trainingSession) {
        assertEquals(name, trainingSession.getName());
        assertEquals(duration, trainingSession.getDuration());
        assertEquals(distance, trainingSession.getDistance());
        assertEquals(notes, trainingSession.getNotes());
        assertEquals(effort, trainingSession.getEffort());
    }
}
