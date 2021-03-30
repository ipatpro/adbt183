package game.Settings;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 3.0
 */

import game.Settings.Files.WriteHighscore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ResetConfirmation implements ActionListener {

    private String ldbfile;
    private JButton confirm;
    private JButton cancel;
    private JLabel message;
    private JLabel message1;
    //private JFrame ldb;
    private Leaderboard ldb;

    private boolean confirmed;

    private WriteHighscore saveRecord;

    private JFrame frame;

    public ResetConfirmation(Leaderboard l, String fi) {

        confirmed = false;

        ldb = l;

        //setup for writing to file
        ldbfile = fi;

        //Display score message
        message = new JLabel("Are you sure you want to");
        message.setBounds(0, 10, 250, 30);
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setVerticalAlignment(JLabel.CENTER);
        message.setVisible(true);

        message1 = new JLabel("RESET the leaderboard?");
        message1.setBounds(0, 30, 250, 30);
        message1.setHorizontalAlignment(JLabel.CENTER);
        message1.setVerticalAlignment(JLabel.CENTER);
        message1.setVisible(true);

        //cancel button option
        cancel = new JButton("Cancel");
        cancel.setBounds(20, 80, 70, 30);
        cancel.setVisible(true);
        cancel.addActionListener(this);

        //confirmation of entry
        confirm = new JButton("Confirm");
        confirm.setBounds(140, 80, 90, 30);
        confirm.setVisible(true);
        confirm.addActionListener(this);

        //creating window for settings
        frame = new JFrame("Are you sure?");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Setting size
        frame.setSize(250, 150);
        frame.setLayout(null);
        // frame should not be resizable
        frame.setResizable(false);
        // set frame to be visible
        frame.setVisible(true);
        // Adding buttons to frame
        frame.add(message);
        frame.add(message1);
        frame.add(cancel);
        frame.add(confirm);

        frame.getRootPane().setDefaultButton(cancel);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==cancel) {
           frame.dispose();
        } else if (e.getSource()==confirm) {
            //Remove leaderboard file
            File file = new File(ldbfile);
            if(file.delete())
            {
                ldb.getGame().resetBest();
                ldb.closeLeaderboard();
                System.out.println("File deleted successfully");
            }
            else
            {
                System.out.println("Failed to delete the file");
            }
            frame.dispose();
        }
    }


}
