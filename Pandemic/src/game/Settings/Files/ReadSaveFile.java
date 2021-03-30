package game.Settings.Files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Demonstrates how high-score data can be read from a text
 * file and printed to the terminal.
 */
public class ReadSaveFile {

    private String fileName;
    private String name = null;
    private int level = 0;
    private int health = 1;

    /**
     * Initialise a new ReadPresets class
     *
     * @param file the name of the presets file
     */
    public ReadSaveFile(String file) {
        fileName = file;
    }

    /**
     * Read the presets data and return values
     */
    public void readSaveFile() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                // file is assumed to contain one name, score pair per line
                String[] part = line.split(",",3);
                name = part[0];
                level = Integer.parseInt(part[1]);
                health = Integer.parseInt(part[2]);
                line = reader.readLine();
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }

    public int getLevel() {
        return level;
    }
    public String getName() {return name;}
    public int getHealth() {return health;}



    public String readName() {
        return name;
    }
}
