package game.Levels.Obstacles;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 2.0
 */

import city.cs.engine.*;
import game.Levels.GameLevel;
import org.jbox2d.common.Vec2;

public class Chaser extends DynamicBody implements StepListener {
    private static final Shape obstacle = new CircleShape(0.7f);
    private static final BodyImage obstacleImage =
            new BodyImage("data/virus.png", 1.4f);

    private enum State {
        ROLL_LEFT, ROLL_RIGHT, STAND_STILL
    }
    public static final float RANGE = 20;
    private GameLevel world;
    private State state;
    private float speed;

    public Chaser(GameLevel w) {
        super(w, obstacle);
        speed = 10f;
        world= w;
        addImage(obstacleImage);
        state = State.STAND_STILL;
        getWorld().addStepListener(this);
    }

    public boolean inRangeLeft() {
        Body a = world.getCharacter();
        float gap = getPosition().x - a.getPosition().x;
        return gap < RANGE && gap > 0;
    }

    public boolean inRangeRight() {
        Body a = world.getCharacter();
        float gap = getPosition().x - a.getPosition().x;
        return gap > -RANGE && gap < 0;
    }

    // update state
    public void preStep(StepEvent e) {
        if (inRangeRight()) {
            if (state != State.ROLL_RIGHT) {
                state = State.ROLL_RIGHT;
            }
        } else if (inRangeLeft()) {
            if (state != State.ROLL_LEFT) {
                state = State.ROLL_LEFT;
            }
        } else {
            if (state != State.STAND_STILL) {
                state = State.STAND_STILL;
                setAngularVelocity(0);
                setLinearVelocity(new Vec2(0, 0));
            }
        }
        refreshRoll();
    }

    // use this to prevent the balls from slowing
    // down and stopping due to friction when they
    // should be rolling
    private void refreshRoll() {
        switch (state) {
            case ROLL_LEFT:
                setAngularVelocity(speed);
                break;
            case ROLL_RIGHT:
                setAngularVelocity(-(speed*2));
                break;
            default: // nothing to do
        }
    }

    public void postStep(StepEvent e) {
    }
}

