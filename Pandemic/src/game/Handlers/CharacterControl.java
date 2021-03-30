package game.Handlers;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 1.0
 */

import game.Levels.Character;
import game.Levels.GameLevel;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/** KeyListener for character */

public class CharacterControl implements KeyListener {

    /** Character object */
    private Character character;

    /** Level class for its methods access */
    private GameLevel level;

    /** Initialise new CharacterControl */
    public CharacterControl(Character d, GameLevel l) {

        character = d;
        level = l;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        }

    /** Setting what to run at different key presses */
    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        //  only reacts if the game has not ended
        if (!character.hasEnded()) {
            if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) {
                character.jump(10f);
                character.startWalking(level.getWalkSpeed()); //uses variable from GameLevel, as it gets updated there based on the level
                level.checkForStart();
            }

            //  quick descent from jump
            if (key == KeyEvent.VK_DOWN) {
                character.jump(0);
                character.setPosition(new Vec2(character.getPosition().x, -2.4f));
            }

            if (key == KeyEvent.VK_ENTER) {
                level.shootFire();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /** Method to update the keyListener to the new character
     * and new level - used by the Game class when changing levels
     *
     * @param character, level*/
    public void updateController(Character character, GameLevel level){
        this.character = character;
        this.level = level;
    }
}
