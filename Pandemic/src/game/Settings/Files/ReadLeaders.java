package game.Settings.Files;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

/**
 * Demonstrates how high-score data can be read from a text
 * file and printed to the terminal.
 */
public class ReadLeaders {

    private String fileName;

    /**
     * Initialise a new ReadLeaders
     *
     * @param file the name of the leaderboard file
     */
    public ReadLeaders(String file) {
        fileName = file;

    }

    /**
     * Read the leaderboard data and add it to a list
     */
    public List<String> readScores() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        List<String> leaderboard = new ArrayList<>();
        try {
            //System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                // file is assumed to contain one name, score pair per line
                String[] tokens = line.split(",");
                String name = tokens[0];
                int score = Integer.parseInt(tokens[1]);
                leaderboard.add("\t\t\t\t\t\t" + name + "\t\t\t\t\t\t Score: " + score);
                line = reader.readLine();
            }
            //System.out.println("...done.");
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
        return leaderboard;
    }
}
