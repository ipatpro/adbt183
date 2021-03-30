package game.Levels.Obstacles;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 1.0
 */

import city.cs.engine.*;

public class Obstacle extends StaticBody {

    private static final Shape obstacleShape = new CircleShape(0.7f);

    private static final BodyImage image = new BodyImage("data/virus.png", 1.4f);

    // main obstacle - collision ends game
    public Obstacle(World world) {
        super(world, obstacleShape);
        addImage(image);
    }

}
