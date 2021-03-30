package game.Settings;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 3.0
 */

import game.*;
import game.Levels.GameLevel;
import game.Settings.Files.WriteSaveGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class SaveInterface extends FilesInterface {

    private String fileToSave;

    private GameLevel level;
    private JFrame settingsFrame;
    private Game game;


    public SaveInterface(GameLevel lvl, JFrame f, Game g) {
        super(lvl, f, g);
        level = lvl;
        settingsFrame = f;
        game = g;

    }

    public void saveGame() {
        File saveFile = new File(fileToSave);
        try {
            saveFile.createNewFile();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        //Writing the presets of the current game to presets file
        WriteSaveGame presets = new WriteSaveGame(fileToSave);
        try { presets.writeSaveState(getName(), level.getCurrentLevel(), game.getHealth()); }
        catch(IOException ex) { ex.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==getFile(1)) {
            NameFile saver = new NameFile(getFrame(), this);
            System.out.println("File 1 saved");
            fileToSave = getFileName(1);
        } else if(e.getSource()==getFile(2)) {
            NameFile saver = new NameFile(getFrame(), this);
            fileToSave = getFileName(2);
            System.out.println("File 2 saved");
        } else if(e.getSource()==getFile(3)) {
            NameFile saver = new NameFile(getFrame(), this);
            fileToSave = getFileName(3);
            System.out.println("File 3 saved");

        } else if(e.getSource()==getClose()) {
            closeFrame();
        }
    }
}
