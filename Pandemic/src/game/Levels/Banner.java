package game.Levels;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 1.0
 */

import city.cs.engine.*;

import java.awt.geom.RectangularShape;

public class Banner extends StaticBody {

    private static final Shape menuShape = new PolygonShape(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f);

    private static BodyImage image = new BodyImage("data/start.jpg", 8f);

    public Banner(World w) {
        super(w, menuShape);
        addImage(image);
    }

    public void restart() {
        // returns to main image
        removeAllImages();
        image = new BodyImage("data/start.jpg", 8f);
        destroy();
    }

    public void over() {
        //  changes image to display game over message
        removeAllImages();
        image = new BodyImage("data/over.jpg", 8f);
        destroy();

    }

    public void win() {
        removeAllImages();
        image = new BodyImage("data/win.jpg", 8f);
        destroy();
    }
}
