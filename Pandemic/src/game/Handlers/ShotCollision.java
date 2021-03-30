package game.Handlers;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 3.0
 */

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Game;
import game.Levels.GameLevel;
import game.Levels.Obstacles.*;

/** Collision listener for fireball (shooting) */

public class ShotCollision implements CollisionListener {


    /** Initialise Shot collision listener */
    public ShotCollision() {
    }

    /** Setup for different object collisions - some get destroyed
     * on collision, others do not */
    @Override
    public void collide(CollisionEvent e) {
        // checks for collision with air obstacle and removes it on contact
        if (e.getOtherBody() instanceof AirObstacle) {
            e.getOtherBody().destroy();
        }
        // destroys healer if shot
        else if (e.getOtherBody() instanceof Healer) {
            e.getOtherBody().destroy();

            // destroys obstacles
        } else if (e.getOtherBody() instanceof Mask) {
            e.getOtherBody().destroy();

        } else if (e.getOtherBody() instanceof Tissue) {
            e.getOtherBody().destroy();
        }

        e.getReportingBody().destroy();

    }
}
