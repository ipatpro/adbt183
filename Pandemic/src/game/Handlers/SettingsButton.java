package game.Handlers;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 2.0
 */

import city.cs.engine.*;

public class SettingsButton extends StaticBody {

    private static final Shape soundShape = new CircleShape(.64f);

    private static BodyImage image = new BodyImage("data/settings.png", 1.5f);

    //icon for mute GUI
    public SettingsButton(World w) {
        super(w, soundShape);
        addImage(image);
    }

    //Toggle between muted and unmuted icons based on boolean in GameLevel
    public void toggle(boolean b) {
        if(b) {
            image = new BodyImage("data/mute.png", 1.5f);
            destroy();
        } else {
            image = new BodyImage("data/!mute.png", 1.5f);
            destroy();
        }
    }

}

