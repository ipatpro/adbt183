package game.Levels;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 2.0
 */

import game.Handlers.ShotCollision;
import game.Levels.Obstacles.AirObstacle;
import game.Game;
import game.Levels.Obstacles.Obstacle;
import org.jbox2d.common.Vec2;

import java.util.Random;

public class Level1 extends GameLevel {

    //init objects
    private Obstacle gobstacle;
    private AirObstacle aobstacle;
    private Random rand = new Random();

    private Game game;


    public Level1(Game game) {
        super(game);

        this.game = game;


        //places character into start position
        lightChar();
        getCharacter().setPosition(new Vec2(-5, -2f));
        getCharacter().setGravityScale(2);

        setCurrentLevel(1);

        setAmmo(2);
        //reset walk speed
        getGame().setWalkSpeed(10f);

        //Set light theme
        setGroundColour(0);

        //settings button
        getSettings().setPosition(new Vec2(-13f, 8f));

        // first level bg music
        setMusic("data/bg1.wav");

        //Introduction title
        getTitle().setPosition(new Vec2(5f, 5f));
        getTitle().setAlwaysOutline(false);

        //sensor to determine when character reaches end of level
        getSensor().setPosition(new Vec2(195f, -2));

        Random rand = new Random();
        //  Creates random positioning of obstacles in first level
        // Minimum gap of 20 between obstacles
        for (float i = 20; i < 185; i += 15) {
            int r = rand.nextInt(15);
            if (r%3 == 0) {
                createGObstacle(i);
            } else {
                createAObstacle(i);
            }
            i = i + r;
            }
        }

    @Override
    public void checkForStart() {

    }

    //Method for creating ground obstacles
    public void createGObstacle(float x) {
        gobstacle = new Obstacle(this);
        gobstacle.setPosition(new Vec2(x, -3.1951563f));
    }

    //Method for creating air obstacles
    public void createAObstacle(float x) {
        //Randomizing between two y values for air obstacles
        int r = rand.nextInt(2);
        float y = 2f;
        if (r == 1) {
            y = 1.2f;
        } else {
            y = -2.8f;
        }
        System.out.println(r);
        aobstacle = new AirObstacle(this);
        aobstacle.setGravityScale(0);
        aobstacle.setPosition(new Vec2(x, y));
    }

    public void shootFire() {
        if (getAmmo() > 0) {
            //System.out.println("Shots fired");
            Fireball fireball = new Fireball(this);
            fireball.setPosition(new Vec2(getCharacter().getPosition().x + 2, getCharacter().getPosition().y+0.3f));
            fireball.setGravityScale(0f);
            fireball.setLinearVelocity(new Vec2(getWalkSpeed() + 11f, 0f));
            fireball.addCollisionListener(new ShotCollision());
            decreaseAmmo();
        }
    }

}
