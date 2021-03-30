package game.Settings;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 3.0
 */

import game.Settings.SaveInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameFile implements ActionListener {

    private JButton done;
    private JButton cancel;
    private JTextField input;
    private JLabel instr;

    private JFrame frame;
    private JFrame saverFrame;
    private SaveInterface save;

    public NameFile(JFrame f, SaveInterface fi) {
        saverFrame = f;
        save = fi;
        //setup for writing to file

        //Display score message
        instr = new JLabel("Enter file name: ");
        instr.setBounds(0, 5, 250, 30);
        instr.setHorizontalAlignment(JLabel.CENTER);
        instr.setVisible(true);


        //text field to input player name
        input = new JTextField();
        input.setBounds(25, 40, 200, 30);
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
            save.setName(input.getText());
            save.saveGame();
            frame.dispose();
            saverFrame.dispose();
        }
    }

}
