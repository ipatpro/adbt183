package game.Levels;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 3.0
 */

import city.cs.engine.*;

public class Fireball extends DynamicBody {

    private static final Shape ballShape = new CircleShape(0.5f);
    private static final BodyImage image = new BodyImage("data/fireball.png", 1f);

    public Fireball(World w) {
        super(w, ballShape);
        addImage(image);
        System.out.println("Fireball created");
    }
}
