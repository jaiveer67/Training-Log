package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ListOfTrainingSessionTest {
    private ListOfTrainingSession testList;
    private TrainingSession testSession1;
    private TrainingSession testSession2;
    private TrainingSession testSession3;
    private TrainingSession testSession4;

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
        testSession4 = new TrainingSession("Run",
                45,
                10,
                "",
                9);
        testList = new ListOfTrainingSession();
    }

    @Test
    void addSessionTest() {
        assertTrue(testList.list.size() == 0);
        testList.addSession(testSession1);
        assertTrue(testList.list.size() == 1);
        assertTrue(testList.list.contains(testSession1));
        testList.addSession(testSession2);
        testList.addSession(testSession3);
        assertTrue(testList.list.get(1) == testSession2);
    }

    @Test
    void removeSessionTest() {
        assertTrue(testList.list.size() == 0);
        testList.addSession(testSession1);
        testList.addSession(testSession2);
        testList.addSession(testSession3);
        testList.removeSession(testSession2);
        assertTrue(testList.list.get(1) == testSession3);
    }


    }

