package game.Handlers;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 1.0
 */

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import game.Levels.Character;
import game.MyView;
import org.jbox2d.common.Vec2;

/** Step listener to move frame based on character position */
//keeping the character centered on screen
//serves as the moving mechanism for the game
public class Tracker implements StepListener {
    private MyView view;
    private Character character;
    /** Initialises the tracker
     *
     * @param v, c - character object and view to access their methods*/
    public Tracker(MyView v, Character c) {
        view = v;
        character = c;
    }
    public void preStep(StepEvent e) {}
    public void postStep(StepEvent e) {
        view.setCentre(new Vec2(character.getPosition().x+10, 0f));
    }
}