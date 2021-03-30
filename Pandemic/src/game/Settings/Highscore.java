package game.Settings;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 3.0
 */

import game.Game;
import game.Settings.Files.WriteHighscore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Highscore implements ActionListener {

    private String ldbfile;
    private JButton done;
    private JButton cancel;
    private JTextField input;
    private JLabel instr;
    private JLabel uscore;
    private Game game;

    private WriteHighscore saveRecord;

    private JFrame frame;
    private JFrame gameFrame;

    public Highscore(Game g, JFrame f) {

        game = g;
        gameFrame = f;
        //setup for writing to file
        ldbfile = "data/leaderboard.txt";
        saveRecord = new WriteHighscore(ldbfile);

        //Display score message
        uscore = new JLabel("New high score: " + game.getScore());
        uscore.setBounds(0, 5, 250, 30);
        uscore.setHorizontalAlignment(JLabel.CENTER);
        uscore.setVisible(true);

        //Instruction label
        instr = new JLabel("Enter name:");
        instr.setBounds(0, 40, 95, 30);
        instr.setHorizontalAlignment(JLabel.RIGHT);
        instr.setVisible(true);

        //text field to input player name
        input = new JTextField();
        input.setBounds(100, 40, 120, 30);
        input.setHorizontalAlignment(JTextField.LEFT);
        input.setVisible(true);

        //cancel button option
        cancel = new JButton("Cancel");
        cancel.setBounds(10, 80, 70, 30);
        cancel.setVisible(true);
        cancel.addActionListener(this);

        //confirmation of entry
        done = new JButton("Done");
        done.setBounds(170, 80, 70, 30);
        done.setVisible(true);
        done.addActionListener(this);

        //creating window for settings
        frame = new JFrame("New Record");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Setting size
        frame.setSize(250, 150);
        frame.setLayout(null);
        // frame should not be resizable
        frame.setResizable(false);
        // set frame to be visible
        frame.setVisible(true);
        // Adding buttons to frame
        frame.add(uscore);
        frame.add(input);
        frame.add(instr);
        frame.add(cancel);
        frame.add(done);

        frame.getRootPane().setDefaultButton(done);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==cancel) {
           frame.dispose();
        } else if (e.getSource()==done) {
            //Write to leaderboard file
            try {
                saveRecord.writeHighScore(input.getText(), game.getScore());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            frame.dispose();
            game.savePresets();
            //gameFrame.requestFocus();
        }
    }

}
