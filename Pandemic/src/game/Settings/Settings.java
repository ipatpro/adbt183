package game.Settings;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 3.0
 */

import game.*;
import game.Levels.GameLevel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings implements ActionListener {

    //Mute button and related elements
    private JButton mute;
    private ImageIcon muted;
    private ImageIcon unmuted;

    private JButton leaderboard;
    private JButton save;
    private JButton load;
    private JButton close;
    private Game game;
    private GameLevel level;

    private JFrame frame;

    public Settings(Game g, GameLevel l) {

        game = g;
        level = l;

        //icons for mute button
        muted = new ImageIcon("data/mute.png");
        unmuted = new ImageIcon("data/!mute.png");

        //mute button setup
        mute = new JButton(unmuted);
        updateMute();
        mute.setBounds(75, 30, 50, 50);
        mute.setVisible(true);
        mute.addActionListener(this);
        mute.setBorderPainted(false);

        //save button setup
        save = new JButton("Save");
        save.setBounds(30, 100, 140, 30);
        save.setVisible(true);
        save.addActionListener(this);

        //load button setup
        load = new JButton("Load");
        load.setBounds(30, 135, 140, 30);
        load.setVisible(true);
        load.addActionListener(this);

        //leaderboard button setup
        leaderboard = new JButton("Leaderboard");
        leaderboard.setBounds(30, 170, 140, 30);
        leaderboard.setVisible(true);
        leaderboard.addActionListener(this);

        //close button option
        close = new JButton("Close");
        close.setBounds(30, 230, 140, 30);
        close.setVisible(true);
        close.addActionListener(this);

        //creating window for settings
        frame = new JFrame("Settings");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Setting size
        frame.setSize(200, 300);
        frame.setLayout(null);
        // frame should not be resizable
        frame.setResizable(false);
        // set frame to be visible
        frame.setVisible(true);
        // Adding buttons to frame
        frame.add(mute);
        frame.add(save);
        frame.add(load);
        frame.add(leaderboard);
        frame.add(close);

    }

    public void actionPerformed(ActionEvent e) {
        //Action for mute button
        if (e.getSource()==mute) {
            game.toggleMute();
            updateMute(); //switch button icon
            System.out.println("Switch pressed");
        }
        else if (e.getSource()==save) {
            FilesInterface saver = new SaveInterface(level, frame, game);
            System.out.println("Save game");

        }
        else if (e.getSource()==leaderboard) {
            openLeaderboard();
            frame.setVisible(false);
        }
        else if (e.getSource()==load) {
            FilesInterface loader = new LoadInterface(level, frame, game);
        }
        else if (e.getSource()==close) {
           frame.dispose();
        }
    }

    //changes icon of mute switch depending on state
    public void updateMute() {
        if (game.isMuted()) {
            mute.setIcon(muted);
        } else {
            mute.setIcon(unmuted);
        }
    }

    public void openLeaderboard() {
        Leaderboard l = new Leaderboard(game, frame);
    }

}
