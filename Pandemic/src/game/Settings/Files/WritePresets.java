package game.Settings.Files;

import java.io.FileWriter;
import java.io.IOException;

public class WritePresets {
    private String presets;

    public WritePresets(String fileName) {
        presets = fileName;
    }

    public void writePresets(int muted, int best) throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(presets);
            fw.write(muted + "," + best);
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }
}
