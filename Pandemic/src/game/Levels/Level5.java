package game.Levels;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 1.0
 */

import game.*;
import game.Handlers.OtherCollisions;
import game.Handlers.ShotCollision;
import game.Levels.Obstacles.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Level5 extends GameLevel implements ActionListener {

    //init objects
    private AirObstacle aobstacle;
    private Chaser chaser;
    private Healer heal;
    private Tissue tissue;
    private Mask mask;
    private Random rand = new Random();
    private Timer timer;

    private Game game;

    public Level5(Game game) {
        super(game);

        this.game = game;


        //character start position for level 5
        darkChar();
        getCharacter().setPosition(new Vec2(1695, -2.3f));
        getCharacter().setGravityScale(2);
        //increase walkSpeed
        getGame().setWalkSpeed(21);

        setCurrentLevel(5);

        setAmmo(10);

        //Set dark theme
        setGroundColour(1);

        //settings button
        getSettings().setPosition(new Vec2(getScore()-13f, 8f));

        //level 5 bg track
        setMusic("data/bg5.wav");

        //Creating timer for heal objects
        int t = rand.nextInt(20000);
        timer = new Timer(5000 + t, this);


        // Random switching between creation of air and ground obstacles
        //Also may create a healing object from time to time
        for (float i = 1730; i < 2495; i += 11) {
            int r = rand.nextInt(23);
            if (r==11) {
                createHeal(i);
            }
            else if (r%2 == 0) {
                if (r%4 == 0) {
                    createChaser(i);
                } else {
                    createMasks(i, r);
                }
            } else if(r%7 == 0) {
                createTissues(i);
            }
            else {
                createAObstacle(i);
            }
            i = i + r;
        }
        //set sensor for end of level 5
        getSensor().setPosition(new Vec2(2500, -2f));
    }

        //Method for creating ground obstacles
        public void createChaser(float x) {
            chaser = new Chaser(this);
            chaser.setPosition(new Vec2(x, -3.1951563f));
        }

    //Method for creating random tissues
    public void createTissues(float x) {
        tissue = new Tissue(this);
        tissue.setPosition(new Vec2(x, -3.1951563f));
    }

    //Method for creating random stuff
    public void createMasks(float x, int r) {
        mask = new Mask(this);
        mask.addCollisionListener(new OtherCollisions(this, getGame()));
        mask.setPosition(new Vec2(x, -3.1951563f));
        mask.setAngularVelocity(0.2f * r);
    }

        //Method for creating healing objects
        public void createHeal(float i) {
            heal = new Healer(this);
            heal.setPosition(new Vec2(i, 10f));
            heal.setAngularVelocity(5);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        createHeal(getScore()+25);
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
        if (getScore() > 1720) {
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
