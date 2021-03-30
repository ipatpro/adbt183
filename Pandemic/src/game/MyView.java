package game;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 1.0
 */

import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

/**
 * A view displaying the background and most of the GUI components
 */

public class MyView extends UserView {

    /** Variables for various images used in GUI */
    /** bacgkround image variable */
    private Image background;
    /** health GUI variable */
    private final Image life;
    /** zoom in GUI icon */
    private final Image zoomIn;
    /** zoom out GUI icon */
    private final Image zoomOut;

    /** Game variable to be able to access methods in whole class */
    private final Game game;

    /** Variables referenced for GUI */
    private int highscore;

    /** Initialise a new View
     * @param w, g, widht, height - parameters determine view size and
     * reference other classes to be able to access their methods
     */
    public MyView(World w, Game g, int width, int height) {
        super(w, width, height);
        background = new ImageIcon("data/lbg.jpg").getImage();
        life = new ImageIcon("data/life.png").getImage();
        zoomIn = new ImageIcon("data/zoomIn.png").getImage();
        zoomOut = new ImageIcon("data/zoomOut.png").getImage();

        game = g;
    }

    /** Setting background image */
    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this);
    }

    /** Draws GUI components on the window */
    @Override
    protected void paintForeground(Graphics2D g) {
        //draw health indicator
        g.setColor(Color.black);
        g.setFont(new Font(g.getFont().getFontName(), Font.ITALIC, g.getFont().getSize()));

        //draw score indicator
        int score = game.getScore();
        highscore = game.getBest();

        g.drawString("Score: " + score, 700, 50);
        if (score > highscore) {
            g.drawString("  Best: " + score, 700, 30);
            highscore = score;
        } else {
            g.drawString("  Best: " + highscore, 700, 30);
        }

        displayLives(g);

       g.drawImage(zoomIn,680, 430, 40, 40, this);
       g.drawImage(zoomOut, 740, 430, 40, 40, this);

        //display level during level increase
        if (score < 20) {
            g.setFont(new Font(g.getFont().getFontName(), Font.ITALIC, g.getFont().getSize()));
            g.drawString("Arrows/Space to Jump | Enter to Shoot | Arrow Down to Ground", 220, 400);
            g.drawString("Use your ammo wisely, it is limited in every level!", 260, 430);
        }
        if (score > 195 && score < 210) {
            g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 40));
            g.drawString("Level 2", 350, 200);
        } else if (score > 395 && score < 410) {
            g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 40));
            g.setColor(Color.white);
            g.drawString("Level 3", 350, 200);
        } else if(score > 1095 && score < 1110) {
            g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 40));
            g.setColor(Color.black);
            g.drawString("Level 4", 350, 200);
        } else if (score > 1695 && score < 1710) {
            g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 40));
            g.setColor(Color.white);
            g.drawString("Level 5", 350, 200);
        }
    }

    /** Getter for highscore variable
     * @return highscore integer of the new highscore
     */
    public int getHighscore() {return highscore;}

    /** Sets light background */
    public void lightTheme() {
        background = new ImageIcon("data/lbg.jpg").getImage();
    }

    /** Sets dark background */
    public void darkTheme() {
        background = new ImageIcon("data/dbg.jpg").getImage();
        System.out.println("Dark switch");
    }

    /** Method to automatically generate number of hearts in
     * GUI of lives based on the health value in Game class
     */
    //Generate heart icons for number of lives
    public void displayLives(Graphics2D g) {
        int health = game.getHealth();
        if (health>0) {
            for (int i = 1; i <= health; i++) {
                g.drawImage(life, (27 * i), 23, 20, 20, this);
            }
        }
    }

    /** resets the value of Best in game if leaderboard is reset */
    public void resetBest() {
        highscore = 0;
        game.setBest(0);
    }

}
