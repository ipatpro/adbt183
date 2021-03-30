package game.Settings;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 3.0
 */

import game.Game;
import game.Levels.GameLevel;
import game.Settings.Files.ReadSaveFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoadInterface extends FilesInterface {

    //private String fileToSave;

    private GameLevel level;
    private JFrame settingsFrame;
    private Game game;


    public LoadInterface(GameLevel lvl, JFrame f, Game g) {
        super(lvl, f, g);
        level = lvl;
        settingsFrame = f;
        game = g;

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==getFile(1)) {
            ReadSaveFile load = new ReadSaveFile(getFileName(1));
            try {
                load.readSaveFile();
                game.loadLevel(load.getLevel(), load.getHealth());
                closeFrame();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } else if(e.getSource()==getFile(2)) {
            ReadSaveFile load = new ReadSaveFile(getFileName(2));
            try {
                load.readSaveFile();
                game.loadLevel(load.getLevel(), load.getHealth());
                closeFrame();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } else if(e.getSource()==getFile(3)) {
            ReadSaveFile load = new ReadSaveFile(getFileName(3));
            try {
                load.readSaveFile();
                game.loadLevel(load.getLevel(), load.getHealth());
                closeFrame();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } else if(e.getSource()==getClose()) {
            closeFrame();
        }
    }
}
