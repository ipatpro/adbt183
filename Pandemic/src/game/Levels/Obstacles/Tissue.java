package game.Levels.Obstacles;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 2.0
 */

import city.cs.engine.*;

import java.awt.geom.RectangularShape;

public class Tissue extends DynamicBody {

    private static final Shape obstacleShape = new CircleShape(0.6f);

    private static final BodyImage image = new BodyImage("data/toiletp.png", 1.7f);

    //More random obstacles to create confusion,
    // however do not cause a collision
    public Tissue(World world) {
        super(world, obstacleShape);
        addImage(image);
    }

}
