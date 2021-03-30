package game.Levels.Obstacles;

import city.cs.engine.*;


public class Healer extends DynamicBody {

    private static final Shape obstacleShape = new CircleShape(0.7f);
    private static final BodyImage image = new BodyImage("data/vcn.png", 1.5f);


    public Healer(World world) {
        super(world, obstacleShape);
        addImage(image);
    }

}
