package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainingSessionTest {
    private TrainingSession testSession1;
    private TrainingSession testSession2;

    @BeforeEach
    void runBefore() {
        testSession1 = new TrainingSession("Run",
                30,
                6.2,
                "Easy jog with a friend",
                6);
        testSession2 = new TrainingSession("Swim",
                60,
                3,
                "",
                8);
    }

    @Test
    void testConstructor() {
        assertEquals("Run", testSession1.getName());
        assertEquals(30, testSession1.getDuration());
        assertEquals(6.2, testSession1.getDistance());
        assertEquals("Easy jog with a friend", testSession1.getNotes());
        assertEquals(" ", testSession2.getNotes());
        assertEquals(6, testSession1.getEffort());
    }

    @Test
    void testPrintSession() {
        assertEquals("\nWorkout Type: " + "Run" + "\nWorkout Duration: "
                        + 30 + "\nWorkout Distance: " + 6.2
                        + "\nWorkout Notes: " + "Easy jog with a friend" + "\nWorkout Effort: " + 6,
                testSession1.printSession());
    }

}