package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainingLogTest {
    private TrainingLog testLog;
    private TrainingSession testSession1;
    private TrainingSession testSession2;
    private TrainingSession testSession3;

    @BeforeEach
    void runBefore() {
        testSession1 = new TrainingSession("Run",
                30,
                6.2,
                "Easy jog with a friend",
                5);
        testSession2 = new TrainingSession("Swim",
                60,
                3,
                "",
                2);
        testSession3 = new TrainingSession("Bike",
                20,
                7,
                "Fun bike ride",
                4);
        testLog = new TrainingLog("Jaiveer's Training log");
    }

    @Test
    void addSessionTest() {
        assertTrue(testLog.trainingSessions.size() == 0);
        testLog.addTrainingSession(testSession1);
        assertTrue(testLog.trainingSessions.size() == 1);
        assertTrue(testLog.trainingSessions.contains(testSession1));
        testLog.addTrainingSession(testSession2);
        testLog.addTrainingSession(testSession3);
        assertTrue(testLog.trainingSessions.get(1) == testSession2);
    }


    @Test
    void removeSessionTest() {
        assertTrue(testLog.trainingSessions.size() == 0);
        testLog.addTrainingSession(testSession1);
        testLog.addTrainingSession(testSession2);
        testLog.addTrainingSession(testSession3);
        assertTrue(testLog.trainingSessions.contains(testSession3));
        testLog.removeTrainingSession();
        assertFalse(testLog.trainingSessions.contains(testSession3));
    }


}

