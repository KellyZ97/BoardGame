package simonRace;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.annotations.AfterTest;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ScoresHandleTest {

    @BeforeAll
    static void clearScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("score.txt"))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void saveRecordTest() {

        // Save a new record
        ScoresHandle.saveRecord("player1", 10);
        ScoresHandle.saveRecord("player1", 20);
        ScoresHandle.saveRecord("player2", 20);

        // Read the file and make sure it contains the correct record
        Map<String, Integer> scores = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("score.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int score = Integer.parseInt(parts[1]);
                scores.put(name, score);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(2, scores.size());
        assertEquals(10, scores.get("player1").intValue());
    }

    @Test
    void showTop10Test() {
        ScoresHandle scoresHandle = new ScoresHandle();

        // Populate the scores file with test data
        scoresHandle.saveRecord("player1", 10);
        scoresHandle.saveRecord("player2", 20);
        scoresHandle.saveRecord("player3", 30);
        scoresHandle.saveRecord("player4", 40);
        scoresHandle.saveRecord("player5", 50);
        scoresHandle.saveRecord("player6", 60);
        scoresHandle.saveRecord("player7", 70);
        scoresHandle.saveRecord("player8", 80);
        scoresHandle.saveRecord("player9", 90);
        scoresHandle.saveRecord("player10", 100);
        scoresHandle.saveRecord("player11", 110);


        // Call the showTop10 method and store the result in a list
        List<String> top10 = scoresHandle.showTop10();

        // Create a list of expected results
        List<String> expected = Arrays.asList("player1: 10", "player2: 20", "player3: 30", "player4: 40", "player5: 50",
                "player6: 60","player7: 70","player8: 80","player9: 90", "player10: 100");

        // Assert that the top10 list contains the expected results
        assertEquals(expected, top10);
    }
}

