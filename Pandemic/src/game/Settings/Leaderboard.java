package game.Settings;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 3.0
 */

import game.Game;
import game.Settings.Files.ReadLeaders;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/** A frame with buttons and a list that gets populated
 * from reading the leaderboard file
 */

public class Leaderboard implements ActionListener {

    /** String containing file name of leaderboard*/
    private String ldbfile;
    /** JButton for close - must be accessible to later methods*/
    private JButton close;
    /** JButton for reset - must be accessible to later methods*/
    private JButton reset;
    /** Game variable allows access to its methods */
    private Game game;
    /** Variable to access the previous frame and change its visibility */
    private JFrame settingsFrame;
    /** Variable for list which will be populated with leaderboard data */
    private JList leaderList;
    /** ReadLeader class which will read highscores in file */
    private ReadLeaders highscores;
    /** This frame */
    private JFrame frame;

    /** Initialise Leaderboard
     * @param g, f Game and JFrame to be able to access methods
     * and hiding/displaying other frames*/
    public Leaderboard(Game g, JFrame f) {

        game = g;
        settingsFrame = f;
        ldbfile = "data/leaderboard.txt";

        File leaderboard = new File(ldbfile);
        try {
            leaderboard.createNewFile();
        } catch(IOException e) {
            e.printStackTrace();
        }

        highscores = new ReadLeaders(ldbfile);

        leaderList = new JList();
        leaderList.setBounds(50, 10, 300, 180);

            DefaultListModel<String> model = new DefaultListModel<>();
            try {
                model.addAll(highscores.readScores());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        leaderList.setModel(model);
            leaderList.setVisible(true);

        //close button option
        close = new JButton("Close");
        close.setBounds(100, 230, 200, 30);
        close.setVisible(true);
        close.addActionListener(this);

        reset = new JButton("Reset");
        reset.setBounds(280, 190, 75, 30);
        reset.setVisible(true);
        reset.addActionListener(this);

        //creating window for settings
        frame = new JFrame("Leaderboard");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Setting size
        frame.setSize(400, 300);
        frame.setLayout(null);
        // frame should not be resizable
        frame.setResizable(false);
        // set frame to be visible
        frame.setVisible(true);
        // Adding buttons to frame
        frame.add(close);
        frame.add(leaderList);
        frame.add(reset);

        frame.getRootPane().setDefaultButton(close);

    }

    /** Defines what to do when close or reset buttons are pressed
     * @param e ActionEvent to identify which button was pressed
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==close) {
           closeLeaderboard();
        } else if (e.getSource()==reset) {
            ResetConfirmation conf = new ResetConfirmation(this, ldbfile);
        }
    }

    /** Closing the leaderboard frame, displaying the settings frame again */
    public void closeLeaderboard() {
        frame.dispose();
        settingsFrame.setVisible(true);
    }

    /** Getter for Game to access game methods
     * used for the resetting of the leaderboard
     * @return game */
    public Game getGame() {
        return game;
    }
}
