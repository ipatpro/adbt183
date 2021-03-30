package game.Handlers;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 1.0
 */

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Game;
import game.Levels.GameLevel;
import game.Levels.Obstacles.*;
/** Collision detector used for the character */
public class ObstDetect implements CollisionListener {

    private GameLevel level;
    private Game game;

    /** Initialise the collision listener
     *
     * @param l, g - game and level to access their methods*/
    public ObstDetect(GameLevel l, Game g) {
        //this.character = c;
        level = l;
        game = g;
    }

    /** Checks which body character collided with and responds
     * accordingly - either increasing/decreasing health level,
     * ending the game or starting movement*/
    @Override
    public void collide(CollisionEvent e) {
        //  checks for collision of character with obstacle instance
        if (e.getOtherBody() instanceof Obstacle) {
            //  starts reaction to the end of the game
            level.endGame();
            //System.out.println("gO Collision");
        }

        if (e.getOtherBody() instanceof Chaser) {
            //  starts reaction to the end of the game
            level.endGame();
            //System.out.println("gO Collision");
        }

        // checks for collision with air obstacle and removes it on contact
        if (e.getOtherBody() instanceof AirObstacle) {
            level.decreaseHealth();
            e.getOtherBody().destroy();
            //System.out.println("aO collision");
        }

        //checks for collision with healing element
        //increases health value
        if (e.getOtherBody() instanceof Healer) {
            level.increaseHealth();
            e.getOtherBody().destroy();
            //System.out.println("hlth collision");
        }

        //Checks for collision with invisible element
        //used for level progression
        if (e.getOtherBody() instanceof LvlEnd) {
            game.goToNextLevel();
        }

        //Plays bump sound when colliding with some objects
        if (e.getOtherBody() instanceof Tissue) {
            ((Tissue) e.getOtherBody()).setAngularVelocity(3);
            level.bump();
        }

        if (e.getOtherBody() instanceof Mask) {
            ((Mask) e.getOtherBody()).setAngularVelocity(3);
            level.bump();
        }
        level.checkForStart();

    }
}
