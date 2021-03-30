package game.Settings.Files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates how high-score data can be read from a text
 * file and printed to the terminal.
 */
public class ReadPresets {

    private String fileName;
    private int mute = 1;
    private int best = 0;

    /**
     * Initialise a new ReadPresets class
     *
     * @param file the name of the presets file
     */
    public ReadPresets(String file) {
        fileName = file;
    }

    /**
     * Read the presets data and return values
     */
    public void readPresets() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                // file is assumed to contain one name, score pair per line
                String[] tokens = line.split(",");
                mute = Integer.parseInt(tokens[0]);
                best = Integer.parseInt(tokens[1]);
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
    }

    public boolean wasMuted() {
        boolean b = true;
        if (mute == 0) {
            b = false;
        } else if (mute == 1) {
            b = true;
        }
        return b;
    }

    public int prevBest() {
        return best;
    }
}
