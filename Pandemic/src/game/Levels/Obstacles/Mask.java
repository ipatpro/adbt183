package game.Levels.Obstacles;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 2.0
 */

import city.cs.engine.*;

public class Mask extends DynamicBody {

    private static final Shape obstacleShape = new CircleShape(0.75f);

    private static final BodyImage image = new BodyImage("data/mask.png", 1.5f);
    // random disturbing obstacle
    public Mask(World world) {
        super(world, obstacleShape);
        addImage(image);
    }

}
