package game.Levels.Obstacles;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 2.0
 */

import city.cs.engine.*;

public class AirObstacle extends DynamicBody {

    private static final Shape obstacleShape = new CircleShape(0.7f);

    private static final BodyImage image = new BodyImage("data/bat.gif", 2.5f);


    public AirObstacle(World world) {
        super(world, obstacleShape);
        addImage(image);
    }

}
