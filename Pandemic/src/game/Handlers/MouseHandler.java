package game.Handlers;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 2.0
 */

import game.Game;
import game.Levels.GameLevel;
import game.MyView;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    private GameLevel level;
    private MyView view;
    private Game game;

    /**
     * Initialise a new Mousehandler
     * @param l, v, g GameLevel, MyView and Game in order to
     * be able to access methods of those classes
     */
    public MouseHandler(GameLevel l, MyView v, Game g) {
        level = l;
        view = v;
        game = g;
    }

    /** Mouse clicked method to recognize for pressing different
     * buttons
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        //location where mouse was pressed
        Point mousePoint = e.getPoint();
        //conversion to location on frame
        Vec2 worldPoint = view.viewToWorld(mousePoint);
        float fx = e.getPoint().x;
        float fy = e.getPoint().y;
        float vx = worldPoint.x;
        float vy = worldPoint.y;
        //Completes checks for pressing of GUI components
        restartCheck(vx, vy);
        settingsCheck(vx, vy);
        zoomCheck(fx, fy);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    /** Method to check if mousePressed was at where restart
     * icon is located
     * @param x, y float positions of mouse click
     */
    //Method to check if restart button was pressed
    public void restartCheck (float x, float y) {
        int score = level.getScore();
        if(x>=score-15 && x<=score+15) {
            if (y<4 && y>1) {
                game.restart();
            }
        }
    }

    /** Method to check if settings button was pressed
     * @param x, y float positions of mouse click
     */
    public void settingsCheck(float x, float y) {
        float sx = level.getSettings().getPosition().x;
        float sy = level.getSettings().getPosition().y;
        if (y<=(sy+0.64) && y>=(sy-0.64)){
            if(x<= (sx+0.64) && x>= (sx-0.64)) {
                level.getGame().openSettings();
            }
        }
    }

    /** Method to check if zoom buttons were pressed and
     * identify which of the two was pressed
     * @param x, y float positions of mouse click
     */
    //Checking for mouseClick on zoom buttons
    public void zoomCheck(float x, float y) {
        if (y<=470 && y>=430) {
            if (x<=720 && x>=680) {
                game.changeZoom(1);
            } else if (x<=780 && x>=740) {
                game.changeZoom(0);
            }
        }
    }

}
