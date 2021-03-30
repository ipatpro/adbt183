package game.Settings.Files;

import java.io.FileWriter;
import java.io.IOException;

public class WriteHighscore {
    private String fileName;

    public WriteHighscore(String fileName) {
        this.fileName = fileName;
    }

    public void writeHighScore(String name, int score) throws IOException {
        boolean append = true;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, append);
            writer.write(name + "," + score + "\n");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
