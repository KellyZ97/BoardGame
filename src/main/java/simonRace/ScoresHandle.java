package simonRace;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that handles saving and showing the top 10 scores of a game.
 * <p>
 * The scores are saved in a file called "score.txt", in the format: playerName,AllSteps
 *
 * @author [Qiongyi Zhang]
 */
public class ScoresHandle {

    /**
     * Saves the record of winner with their name and their total steps as score.
     * <p>
     * If the player already exists in the scores file, their score will be updated if it is higher than the previous score.
     *
     * @param winnerName the name of the winner
     * @param allStep the player's total steps
     */
    public static void saveRecord(String winnerName, int allStep) {
        Map<String, Integer> scores = new HashMap<>();

        // check if the file existed already
        File file = new File("score.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("creat file");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // read file and save the name and scores in a hash map
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

        // update the winners scores if current total step less than previous one
        if (!scores.containsKey(winnerName) || scores.get(winnerName) > allStep) {
            scores.put(winnerName, allStep);
        }

        // write in file
        try (FileWriter writer = new FileWriter("score.txt")) {
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                String name = entry.getKey();
                int score = entry.getValue();
                writer.write(name + "," + score + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the top 10 scores from the scores file.
     *
     * @return top10 players record as a list of strings in the format "playerName: score"
     */
    public static List<String> showTop10() {
        // Read the file and store the player names and scores in a HashMap
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

        // Store the player names and scores in a list
        List<String> players = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            String name = entry.getKey();
            int score = entry.getValue();
            players.add(name + ": " + score);
        }

        // Sort the players by score in descending order
        players.sort((s1, s2) -> {
            int score1 = Integer.parseInt(s1.split(":")[1].trim());
            int score2 = Integer.parseInt(s2.split(":")[1].trim());
            return Integer.compare(score2, score1);
        });

        // Get the bottom 10 players
        List<String> topPlayers = new ArrayList<>();
        for (int i = players.size() - 1; i > Math.max(players.size() - 11, -1); i--) {
            topPlayers.add(players.get(i));
        }
        return topPlayers;
    }
}
