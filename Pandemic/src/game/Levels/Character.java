package game.Levels;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 1.0
 */

import city.cs.engine.*;

/** Character class */
public class Character extends Walker {

    /** Boolean value to check if character should be running */
    private boolean end = false;

    /** Shape of character */
    private static final Shape studentShape =
            new PolygonShape(
            0.4f,1.7f,
            1.87f,1.7f,
            1.3f,0.2f,
            0.4f,-1.4f,
            -0.6f,-1.4f,
            -1.45f,0.8f);

    /** Character image */
    private static BodyImage image =
            new BodyImage("data/dino.gif", 5f);

    /** Initialise Character
     *
     * @param world - used to access its method*/
    public Character(World world) {
         super(world, studentShape);
        addImage(image);
    }

    /** Getter for boolean end
     * @return boolean */
    public boolean hasEnded() {
        return end;
    }

    /** Changes character image for dark theme */
    //changes to dark mode costume
    public void darkMode() {
        image = new BodyImage("data/ddino.png", 5f);
        destroy();
    }

    /** Changes character image for light theme */
    //changes to light mode costume
    public void lightMode() {
        image = new BodyImage("data/dino.gif", 5f);
        destroy();
    }

    /** Stops the character movement and update end boolean */
    public void endGame() {
        stopWalking();
        end = true;
    }
}
