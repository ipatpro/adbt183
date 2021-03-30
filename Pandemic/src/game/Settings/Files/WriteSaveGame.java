package game.Settings.Files;

import java.io.FileWriter;
import java.io.IOException;

public class WriteSaveGame {
    private String fileName;

    public WriteSaveGame(String f) {
        fileName = f;
    }

    public void writeSaveState(String name, int level, int health) throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(fileName);
            fw.write(name + "," + level + "," + health);
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }
}
