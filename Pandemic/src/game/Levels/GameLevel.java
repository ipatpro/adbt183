package game.Levels;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 1.0
 */

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.*;
import game.Handlers.LvlEnd;
import game.Handlers.ObstDetect;
import game.Handlers.SettingsButton;
import org.jbox2d.common.Vec2;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.awt.*;

/**
 * Abstract class with some bodies acting as a template
 * for all the levels
 */

public abstract class GameLevel extends World {


    //Initialising objects
    /** Character object, common to all GameLevel extensions */
    private Character character;
    /** Settings button used as GUI carried across levels */
    private final SettingsButton settings = new SettingsButton(this);
    /** Instructions and information banner variable */
    private Banner title;
    /** SoundClip variable for background music*/
    private SoundClip bgMusic;
    /** SoundClip variable for game over sound */
    private SoundClip overSound;
    /** SoundClip variable for collision sound */
    private SoundClip bumpSound;
    /** LvlEnd variable that gets moved in every level */
    private final LvlEnd sensor;

    /** StaticBody variable used as the base of the game */
    private StaticBody ground;

    //Initializing variables being used in game
    /**
     * Score is carried across various levels,
     * Required for changing levels and score tracking
     */
    private int score;

    /** Boolean to check if game has finished */
    private boolean over = false;

    /** Game variable to give global access to whole class */
    private Game game;

    /** Integer tracking current level */
    private int currentLevel;
    /** Integer tracking available ammo */
    private int ammo;

    /** Initialise new GameLevel */
    public GameLevel(Game g) {

        //variable to be able to call variables from game class
        game = g;

        /** setup for elements common to all levels */

        // base / ground for game
        Shape shape = new BoxShape(3000, 0.1f);
        ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0, -4f));
        setGroundColour(0);

        // character is only transitioned for every level
        character = new Character(this);

        // collision listener for character
        character.addCollisionListener(new ObstDetect(this, game));

        title = new Banner(this);

        sensor = new LvlEnd(this);

        //Background audio setup - points to non-existing file, as we want nothing to play yet
        try {
            bgMusic = new SoundClip("data/bg.wav");   // Locate file in data
            bgMusic.loop();  // Looping for continuous playback
            bgMusic.setVolume(0.2);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        // Settings button
        settings.setAlwaysOutline(false);

        ammo = currentLevel;
    }

    /**
     * Getters and Setters for shared objects
     */
    /** Getter for character object
     * @return character object
     */
    public Character getCharacter() {
        return character;
    }

    /** Getter for sensor in order to be able to reposition it
     * for every level
     * @return sensor LvlEnd object
     */
    public LvlEnd getSensor() {
        return sensor;
    }

    /** Getter for banner object
     * @return title Banner to be able to modify and carry
     * to different levels
     */
    public Banner getTitle() {
        return title;
    }

    /** Getter for Game variable to give access to methods
     * in Game class
     * @return game
     */
    public Game getGame() { return game; }

    /** Getter for SettingsButton object for repositioning
     * @return settings
     */
    public SettingsButton getSettings() {
        return settings;
    }

    /**
     * Getters and Setters for shared variables
     */

    /** Getter for walkspeed referencing method from Game class
     * to give access to KeyListener which doesn't have Game class
     * access
     * @return float of walkspeed
     */
    public float getWalkSpeed() {
        return game.getWalkSpeed();
    }

    /** Getter for the currentLevel variable for the files
     * interface class to be able to reference when saving a
     * game state
     * @return currentLevel integer value
     */
    public int getCurrentLevel() { return currentLevel; }

    /** Setter for currentLevel, called when a new level is opened
     * to update the current level of the game
     * @param i integer for current level
     */
    public void setCurrentLevel(int i) { currentLevel = i; }

    /** Getter for ammo variable used to check if ammo is left
     * and Fireball object can be created in the levels
     * @return int of ammo left
     */
    public int getAmmo() { return ammo; }
    /** Setter for ammo for every level to set the amount of
     * ammo available for the given level
     * @param i int to set ammo levels to
     */
    public void setAmmo(int i) {ammo = i;}
    /** Method run to decrease the ammo left when a Fireball
     * is shot
     */
    public void decreaseAmmo() { ammo--;}

    /** Getter for score using the characters x position,
     * as scoring is essentially measured by the distance covered
     * @return int of current score
     */
    public int getScore() {
        score = (int) character.getPosition().x;
        score += 5;
        return score;
    }

    /** Getter for boolean checking whether the game
     * has ended
     * @return boolean value of variable over
     */
    public boolean isOver() {
        return over;
    }


    /**
     * Method for changing the ground colour
     *
     * @param i integer used to select between two
     *          possible ground colours
     */
    public void setGroundColour(int i) {
        if (i == 0) {
            ground.setFillColor(Color.gray);
            ground.setLineColor(Color.gray);
        } else if (i == 1) {
            ground.setFillColor(Color.lightGray);
            ground.setLineColor(Color.lightGray);
        }
    }

    /**
     * Method to decrease health level after certain
     * collisions and check if any character lives are left
     */
    // decreasing health level
    public void decreaseHealth() {
        game.updateHealth(-1);
        lifeLoss();
        //checking if health is below value
        //cals end of game if run out of lives
        if (game.getHealth() <= 0) {
            endGame();
        }
    }

    /**
     * Method to increase health level after certain collisions
     */
    //increasing health level
    public void increaseHealth() {
        game.updateHealth(1);
        lifeGain();
    }


    /**
     * Music Controls Section
     */

    /**
     * Method to allow levels to set their background music
     * Prevents from having to replicate bgMusic object in
     * every single level
     *
     * @param  s String used to enter the sound file name into method
     */
    //setter for music, so that
    // each level can update to its own bg track
    public void setMusic(String s) {
        try {
            bgMusic = new SoundClip(s);   // Locate file in data
            bgMusic.setVolume(0.0001); //Defaults to playing quietly
            bgMusic.loop();  // Looping for continuous playback
            if (game.isMuted()) {
                bgMusic.setVolume(0.0001);
            } else {
                bgMusic.setVolume(0.2);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /** Method to stop music used for end of levels or game over */
    //method to stop bg music
    public void stopMusic() {
        bgMusic.stop();
    }

    /**
     * Method to mute background music if mute state
     * changes from settings
     */
    //BG music controls
    public void muteMusic() {
        this.bgMusic.setVolume(0.0001);
        System.out.println("mute");
    }

    /**
     * Method to unmute background music
     */
    public void unmuteMusic() {
        this.bgMusic.setVolume(.2);
        System.out.println("unmute");
    }

    /**
     * Abstract class checking whether the character
     * has started to move
     * This is then used to activate a timer appropriately
     */
    public abstract void checkForStart();

    /**
     * Methods to play different sounds based on which
     * objects have a collision
     */
    public void bump() {
        //Plays a bumping sound
        try {
            bumpSound = new SoundClip("data/bump.wav");
            bumpSound.play();
        } catch (UnsupportedAudioFileException |IOException|LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Methods to play sound after health has decreased
     */
    public void lifeLoss() {
        //Plays tone for loss of health
        try {
            bumpSound = new SoundClip("data/lifeLoss.wav");
            bumpSound.play();
        } catch (UnsupportedAudioFileException |IOException|LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Playing tone if life has been gained
     */
    public void lifeGain() {
        //Plays tone for added health
        try {
            bumpSound = new SoundClip("data/lifeGain.wav");
            bumpSound.play();
        } catch (UnsupportedAudioFileException |IOException|LineUnavailableException e) {
            System.out.println(e);
        }
    }


  /** Methods used for modifying the level appearance */

    /**
     * Methods to switch to dark theme character for levels 3 and 5
     */
    //switches character to dark mode
    //adds collision listener, otherwise character would not react to obstacles
    public void darkChar() {
        character.darkMode();
        setGroundColour(1);
        character = new Character(this);
        character.addCollisionListener(new ObstDetect(this, game));
    }

    /**
     * Method to switch look of character for levels
     * using the light colour theme
     */
    //switches character to light mode
    public void lightChar() {
        character.lightMode();
        setGroundColour(0);
        character = new Character(this);
        character.addCollisionListener(new ObstDetect(this, game));

    }

    /** Method to deploy ammo */
    public void shootFire() {}

    /**
     * Method to remove objects from the frame
     */
    public void clearFrame() {
        title.restart();
        character.destroy();
        ground.destroy();
        settings.destroy();
    }

    /**
     * Method for the state when game ends as the result of a collision
     * removes collision listeners from characters to stop from any other collisions
     * occuring
     * Also plays the game over sound and stops background music
     * Character movement is stopped
     * Game checks for whether a new highscore has been reached
     * Displays banner giving the option to restart the game
     */
    //method for Game Over (fail)
    public void endGame() {

        over = true;

        character.removeAllCollisionListeners();

        game.setHealth(0);
        //  game over title (destroys initial, creates new image with different costume)

        //Plays a game over sound
        try {
            overSound = new SoundClip("data/over.wav");
            overSound.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        // calls for character to stop
        character.endGame();

        //Stops music after collision
        bgMusic.stop();

        title.over();
        title = new Banner(this);
        // centres message on screen based on character position
        title.setPosition(new Vec2((character.getPosition().x + getWalkSpeed() + 5), 5f));

        game.checkForHS();
    }

    /**
     * Method for the case when player reaches the end of the game
     * Win banner is displayed
     * New highscore interface is presented
     * Character movement is stopped
     */
    //Scenario for reaching the end of the game
    public void win() {
        //destroys old title, replaces with new image
        title.win();
        title = new Banner(this);
        // centres message on screen based on character position
        title.setPosition(new Vec2(character.getPosition().x + 10, 5f));
        game.checkForHS();

        //stop character
        character.endGame();
        bgMusic.stop();
    }

}
