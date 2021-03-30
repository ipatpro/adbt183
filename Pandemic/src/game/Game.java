package game;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 1.0
 */

import game.Handlers.CharacterControl;
import game.Handlers.MouseHandler;
import game.Handlers.Tracker;
import game.Levels.*;
import game.Settings.Files.ReadPresets;
import game.Settings.Files.WritePresets;
import game.Settings.Highscore;
import game.Settings.Settings;


import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * A frame calling for a View and GameLevels to fill itself
 */

public class Game {

    /** The World in which the bodies move and interact */
    private GameLevel level;

    /** A JPanel in which the game runs */
    private MyView view;

    /** Character controller to activate key controls */
    private CharacterControl ccontrol;

    /** Mousehandler object to interact with GUI icons */
    private MouseHandler mouse;

    /** Zoom variable with default value */
    private int zoom = 20;

    /** Frame of the game */
    private JFrame frame;

    /** Name of file where presets are to be saved and loaded from */
    private String filename;

    /**
     * Variables taken out of game class in
     * order not to be reset on every level
     */
    /** Boolean value for whether bg music is to be muted */
    private boolean mute; //boolean to check for music mute
    /** Float with default walkspeed set - different levels change this value */
    private float walkSpeed = 10; //walkspeed setup
    /** Default health value - health is carried across levels, not reset at each level */
    private int health = 3; //Health meter
    /** Variable to display overall highest score */
    private int best; //indicator of best score

    /** Initialise a new Game */
    public Game() {

        //mutes bg music by default
        mute = true;

        filename = "data/presets.txt";

        presetSetup();

        // creating the level
        level = new Level1(this);
        // creating view containing the level
        view = new MyView(level, this, 800, 500);

        // key and mouse listeners allowing controls
        ccontrol = new CharacterControl(level.getCharacter(), level);
        mouse = new MouseHandler(level, view, this);

        view.addMouseListener(mouse);
        view.addKeyListener(ccontrol);
        view.setFocusable(true);

        //step listener to track character position and move view
        level.addStepListener(new Tracker(view, level.getCharacter()));

        // add the view to a frame (Java top level window)
        frame = new JFrame("Pandemic");
        frame.add(view);

        // enable the frame to quit the application
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // make frame unresizable
        frame.setResizable(false);
        // size the frame to fit the world view
        frame.pack();
        // makes frame visible
        frame.setVisible(true);

        // start
        level.start();

        view.setZoom(zoom);
    }

    /**
     * Getter and setter method for shared variables
     */
    /** Getter for score
     * @return int score */
    public int getScore() {
        return level.getScore();
    }

    /** Getter for health
     * @return int health */
    public int getHealth() {
        return health;
    }

    /** Setter for health used when loading
     * from a saved file
     * @param h int for health value */
    public void setHealth(int h) {health = h;}

    /** Getter for mute to set appropriate volume
     * level on background soung
     * @return boolean of mute variable */
    public boolean isMuted() { return mute;}

    /** Getter for best score used to save presets
     * and save high score
     * @return int best score */
    public int getBest() {return best;}

    /** Setter for best score
     * @param b int for best score to set */
    public void setBest(int b) {best = b; }

    /** Resetting best score to 0 */
    public void resetBest() {
        view.resetBest();
        best = 0;
    }

    /** Getter for walk speed
     * @return float walk speed */
    public float getWalkSpeed() {return walkSpeed;}

    /** Setter for walk speed when new levels are initiated
     * @param n float for target walk speed */
    public void setWalkSpeed(float n) { walkSpeed = n;}

    /**
     * Method to change zoom implements conditional statements
     * in order to prevent excessive zooming in or out which
     * would affect gameplay
     *
     * @param z integer signaling either zooming in or out
     */
    public void changeZoom(int z) {
        if (z==1 && zoom < 22) {
            zoom++;
        } else if (z==0 && zoom > 15) {
            zoom--;
        }
        view.setZoom(zoom);
    }

    /**
     * Method for incrementing health variable
     * @param i integer used as increment/decrement value
     */
    public void updateHealth(int i) {
        health += i;
    }

    /**
     * Method for switching mute state including call to
     * method in GameLevel class, responsible for updating bg music
     */
    public void toggleMute() {
        if (mute) {
            level.unmuteMusic();
        } else if (!mute) {
            level.muteMusic();
        }
        mute = !mute;
        savePresets();
    }

    /**
     * Method for loading a level after file is selected in
     * loader
     *
     * @param l, h integers used to determine which level the file
     *           was saved at and the number of health points remaining
     */
    public void loadLevel(int l, int h) {
        level.stopMusic();
        level.stop();
        health = h;
        if (l == 1) {
            level = new Level1(this);
            view.lightTheme();
        } else if (l==2) {
            level = new Level2(this);
            view.lightTheme();
        } else if (l == 3) {
            level = new Level3(this);
            view.darkTheme();
        } else if(l==4) {
            level = new Level4(this);
            view.lightTheme();
        } else if(l==5) {
            level = new Level5(this);
            view.darkTheme();
        }
        nextLvlSetup();
    }



    /**
     * Method to call next level based on current level
     * Some levels will also have a swapped theme
     */
    public void goToNextLevel() {
        level.stopMusic();
        level.stop();
        if (level instanceof Level1) {
            level = new Level2(this);
            nextLvlSetup();
        } else if (level instanceof Level2) {
            level = new Level3(this);
            nextLvlSetup();
            view.darkTheme();
        } else if (level instanceof Level3) {
            level = new Level4(this);
            nextLvlSetup();
            view.lightTheme();
        } else if (level instanceof Level4) {
            level = new Level5(this);
            nextLvlSetup();
            view.darkTheme();
        } else if (level instanceof Level5) {
            level.win();
        }
    }

    /** Open new highscore window */
    public void newHighScore() {
        Highscore h = new Highscore(this, frame);
    }

    /** Open new settings window */
    public void openSettings() {
        Settings s = new Settings(this, level);
    }

    /**
     * Updating focus of view, controllers, handlers
     * and listeners to work on new level
     */
    public void nextLvlSetup() {
        //level now must change to refer to the new level
        view.setWorld(level);
        view.setFocusable(true);
        view.setZoom(zoom);
        ccontrol.updateController(level.getCharacter(), level);
        mouse = new MouseHandler(level, view, this);
        view.addMouseListener(mouse);
        level.addStepListener(new Tracker(view, level.getCharacter()));
        level.start();
    }

    /**
     * Restarting to level 1 after game over
     */
    public void restart() {
        if (level.isOver()) {
            level.stop();
            view.lightTheme();
            level.clearFrame();
            level = new Level1(this);
            health = 3;
            nextLvlSetup();
            savePresets();
            frame.dispose();
            level.stopMusic();
            new Game();
        }
    }

    /**
     * Method for checking whether new highscore has been reached
     * and if so, calling for a method running the process to save
     * a new best score
     */
    public void checkForHS() {
        savePresets();
        if (level.getScore() == view.getHighscore()) {
            newHighScore();
        }
    }

    /**
     * Method to save game presets for variables:
     * mute & best
     * Writes this using the writePresets method
     */
    public void savePresets() {
        File presetsFile = new File(filename);
        try {
            presetsFile.createNewFile();
        } catch(IOException e) {
            e.printStackTrace();
        }
        //Writing the presets of the current game to presets file
        WritePresets presets = new WritePresets(filename);
        best = view.getHighscore();
        int intMute = (mute) ? 1 : 0;
        try { presets.writePresets(intMute, best); }
        catch(IOException e) { e.printStackTrace();
        }
    }

    /**
     * Uses the ReadPresets class and method to read
     * from presets file and assign values to local variables
     */
    public void presetSetup() {
        ReadPresets presets = new ReadPresets(filename);
        try {
            presets.readPresets();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mute = presets.wasMuted();
        best = presets.prevBest();
    }


    /** Runs the game */
    public static void main(String[] args) {
        new Game();
    }
}
