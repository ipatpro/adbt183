package game.Handlers;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 2.0
 */

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Game;
import game.Levels.GameLevel;
import game.Levels.Obstacles.AirObstacle;
import game.Levels.Obstacles.Healer;

/** Collision listener for obstacles in the game to allow
 * them to interact between each other */
public class OtherCollisions implements CollisionListener {

    private GameLevel level;
    private Game game;


    /** Initialises the collision listener for obstacles */
    public OtherCollisions(GameLevel l, Game g) {
        //this.character = c;
        this.level = l;
        this.game = g;
    }

    /** Sets the actions for different object collisions */
    @Override
    public void collide(CollisionEvent e) {
        // checks for collision with air obstacle and removes it on contact
        if (e.getOtherBody() instanceof AirObstacle) {
            e.getReportingBody().destroy();
            e.getOtherBody().destroy();
        }

        //checks for collision with healing element
        //doubles health increase
        if (e.getOtherBody() instanceof Healer) {
            level.increaseHealth();
            level.increaseHealth();
            e.getOtherBody().destroy();
        }

    }
}
