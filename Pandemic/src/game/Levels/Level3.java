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

public class Level3 extends GameLevel {

    //init objects
    private AirObstacle aobstacle;
    private Obstacle gobstacle;
    private Random rand = new Random();

    private Game game;

    public Level3(Game game) {
        super(game);
        this.game = game;

        //character start position for level 3
        darkChar(); //switch character to dark theme
        getCharacter().setPosition(new Vec2(395, -2.3f));
        getCharacter().setGravityScale(2);

        //increasing walking speed to increase difficulty
        getGame().setWalkSpeed(15);

        setAmmo(6);

        setCurrentLevel(3);

        //Set dark theme
        //darkMode();
        setGroundColour(1);

        //settings button
        getSettings().setPosition(new Vec2(getScore()-13f, 8f));

        //Level 3 bg music
        setMusic("data/bg3.wav");

        // Random creation of air and ground obstacles
        for (float i = 430; i < 1082; i += 10) {
            int r = rand.nextInt(15);
            if (r%3 == 0) {
                createGObstacle(i);
            } else {
                createAObstacle(i);
            }
            i = i + r;
        }

        //sensor for end of level 3
        getSensor().setPosition(new Vec2(1095f, -2f));

    }

    @Override
    public void checkForStart() {

    }

    public void shootFire() {
        System.out.println("Should work fine");
        if (getAmmo() > 0) {
            System.out.println("Shots fired");
            Fireball fireball = new Fireball(this);
            fireball.setPosition(new Vec2(getCharacter().getPosition().x + 2, getCharacter().getPosition().y + 0.3f));
            fireball.setGravityScale(0f);
            fireball.setLinearVelocity(new Vec2(getWalkSpeed() + 8f, 0f));
            fireball.addCollisionListener(new ShotCollision());
            decreaseAmmo();
        }
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

    }
