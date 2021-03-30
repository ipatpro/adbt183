package game.Levels;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 2.0
 */

import game.*;
import game.Handlers.ShotCollision;
import game.Levels.Obstacles.AirObstacle;
import game.Levels.Obstacles.Chaser;
import game.Levels.Obstacles.Healer;
import game.Levels.Obstacles.Tissue;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class Level4 extends GameLevel implements ActionListener {

    //init objects
    private AirObstacle aobstacle;
    private Chaser mobstacle;
    private Healer heal;
    private Tissue tissue;
    private Random rand = new Random();

    private Timer timer;

    private Game game;

    public Level4(Game game) {
        super(game);

        this.game = game;


        //character start position for level 4
        lightChar(); //returns character to light mode
        getCharacter().setPosition(new Vec2(1095, -2.3f));
        getCharacter().setGravityScale(2);
        //increase walkSpeed
        getGame().setWalkSpeed(17);

        setCurrentLevel(4);

        setAmmo(8);

        //settings button
        getSettings().setPosition(new Vec2(getScore()-13f, 8f));

        //level 4 bg track
        setMusic("data/bg2.wav");

        //Swap ground colour
        setGroundColour(0);

        //Creating timer for heal objects
        int t = rand.nextInt(10000);
        timer = new Timer(10000 + t, this);

        // Random switching between creation of air and ground obstacles
        //Also may create a healing object from time to time
        for (float i = 1130; i < 1685; i += 10) {
            int r = rand.nextInt(10);
            if (r%2 == 0) {
                createAObstacle(i);
            } else if (r%3 == 0) {
                createMObstacle(i);
            } else {
                createTissues(i);
            }
            i = i + r;
        }

        //set sensor for end of level 4
        getSensor().setPosition(new Vec2(1695, -2f));

    }

        //Method for creating ground obstacles
        public void createMObstacle(float x) {
            mobstacle = new Chaser(this);
            mobstacle.setPosition(new Vec2(x, -3.1951563f));
        }

        //Method for creating random tissues
        public void createTissues(float x) {
            tissue = new Tissue(this);
            tissue.setPosition(new Vec2(x, -3.1951563f));
        }

        //Method for creating healing objects
        public void createHeal(float i) {
            heal = new Healer(this);
            heal.setPosition(new Vec2(i, 10f));
            heal.setAngularVelocity(5);
            heal.setAlwaysOutline(false);
        }

        //Method for creating air obstacles
        public void createAObstacle(float x) {
            //Randomizing between two y values for air obstacles
            int r = rand.nextInt(2);
            float y = 2f;
            if (r == 1) {
                y = 1.3f;
            } else {
                y = -2.8f;
            }
            System.out.println(r);
            aobstacle = new AirObstacle(this);
            aobstacle.setGravityScale(0);
            aobstacle.setPosition(new Vec2(x, y));
        }

        public void checkTimerStart() {

            if(getScore() > 1710) {
                timer.start();
                System.out.println("Timer started");

            } else {
                System.out.println("Delaying");
            }
        }

        //Event when timer completes
        @Override
        public void actionPerformed(ActionEvent e) {
            createHeal(getScore()+20);
            System.out.println("Heal sent");
        }

        public void runTimer() {
            if (!getCharacter().hasEnded()) {
                    timer.start();
            } else {
                timer.stop();
            }
        }

    @Override
    public void checkForStart() {
        if (getScore() > 1120) {
            runTimer();
        }

    }

    public void shootFire() {
        if (getAmmo() > 0) {
            //System.out.println("Shots fired");
            Fireball fireball = new Fireball(this);
            fireball.setPosition(new Vec2(getCharacter().getPosition().x + 2, getCharacter().getPosition().y + 0.3f));
            fireball.setGravityScale(0f);
            fireball.setLinearVelocity(new Vec2(getWalkSpeed() + 8f, 0f));
            fireball.addCollisionListener(new ShotCollision());
            decreaseAmmo();
        }
    }
}
