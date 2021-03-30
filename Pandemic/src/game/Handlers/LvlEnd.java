package game.Handlers;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 2.0
 */

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/** Static object invisible to user used to trigger
 * end of level */
public class LvlEnd extends StaticBody {

    private static final Shape obstacleShape = new CircleShape(0.7f);

    private static final BodyImage image = new BodyImage("data/mt.png", 1.4f);

    // Invisible element used for collision event to trigger transfer to new level

    /** Initialise a level end object
     * this object is used to determine when the end
     * of a level has been reached
     *
     * @param world
     */
    public LvlEnd(World world) {
        super(world, obstacleShape);
        addImage(image);
    }

}
