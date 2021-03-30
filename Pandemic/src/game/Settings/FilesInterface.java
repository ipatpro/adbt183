package game.Settings;

/**
 * @author      Patrik Chrenko, patrik.chrenko@city.ac.uk
 * @version     v 3.1
 * @since       v 3.0
 */

import game.Game;
import game.Levels.GameLevel;
import game.Settings.Files.ReadSaveFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/** Abstract class for the save and load menus,
 * which consist of an identical layout.
 * Implements ActionListener for buttons to function
 */

public abstract class FilesInterface implements ActionListener {
    //Three save files
    /** String var = Name of first file*/
    private String sFile1;
    /** String var = Name of second file*/
    private String sFile2;
    /** String var = Name of third file*/
    private String sFile3;
    /** JButton representing first file*/
    private JButton iconFile1;
    /** JButton representing second file*/
    private JButton iconFile2;
    /** JButton representing third file*/
    private JButton iconFile3;
    /** JButton for closing the frame*/
    private JButton close;
    /** JFrame variable to reference previous settings window*/
    private JFrame settingsFrame;

    /** Label for first file name*/
    private JLabel fileOneName;
    /** Label for second file name*/
    private JLabel fileTwoName;
    /** Label for third file name*/
    private JLabel fileThreeName;
    
    /** Label for first file level*/
    private JLabel fileOneLevel;
    /** Label for second file level*/
    private JLabel fileTwoLevel;
    /** Label for third file level*/
    private JLabel fileThreeLevel;

    /** Icon used if save file is empty*/
    private final ImageIcon emptyIcon;
    /** Icon used if save file has contents*/
    private final ImageIcon loadIcon;

    /** String variable for the name of the file*/
    private String name;

    /** GameLevel used to give access to level methods*/
    //private GameLevel level;

    /** JFrame for global access in this class*/
    private JFrame frame;

    public FilesInterface(GameLevel lvl, JFrame f, Game g) {
        emptyIcon = new ImageIcon("data/emptyFile.png");
        loadIcon = new ImageIcon("data/saveFile.png");

        settingsFrame = f;
        // file locations
        sFile1 = "data/saveFileOne.txt";
        sFile2 = "data/saveFileTwo.txt";
        sFile3 = "data/saveFileThree.txt";

        // initialising JButtons
        iconFile1 = new JButton(emptyIcon);
        iconFile2 = new JButton(emptyIcon);
        iconFile3 = new JButton(emptyIcon);

        // testing whether save files are created
        File saveOne = new File(sFile1);
        try {
            saveOne.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File saveTwo = new File(sFile2);
        try {
            saveTwo.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File saveThree = new File(sFile3);
        try {
            saveThree.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //creating window for settings
        frame = new JFrame("Select file:");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Setting size
        frame.setSize(250, 300);
        frame.setLayout(null);
        // frame should not be resizable
        frame.setResizable(false);
        // set frame to be visible
        frame.setVisible(true);

        //Methods to add load content and layout
        //for frame
        loadContent();
        createLayout();

    }
    /** Method to handle button press
     * Only sets the action for button 'close'
     * as the other buttons have different actions
     * in various extensions of this class
     * @param e representing the pressed button*/
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==close) {
            frame.dispose();
            settingsFrame.setVisible(true);
            settingsFrame.requestFocus();
        }
    }

    /** Method calling individual methods which position GUI
     * elements*/
    public void createLayout() {
        createIconButton(iconFile1, 15);
        createIconButton(iconFile2, 90);
        createIconButton(iconFile3, 165);

        createNameLabel(fileOneName, 30);
        createNameLabel(fileTwoName, 105);
        createNameLabel(fileThreeName, 180);

        createLevelLabel(fileOneLevel, 45);
        createLevelLabel(fileTwoLevel, 120);
        createLevelLabel(fileThreeLevel, 195);

        //close button option
        close = new JButton("Close");
        close.setBounds(90, 230, 70, 30);
        close.setVisible(true);
        close.setHorizontalAlignment(JLabel.CENTER);
        close.addActionListener(this);
        frame.add(close);
    }

    /** Method to create the icons which also serve as buttons
     * for the files
     * @param button, y - button is the JButton to be assigned
     * to and y is the y value at which to position the button*/
    public void createIconButton(JButton button, int y) {
        //button = new JButton(emptyIcon);
        button.setBounds(30, y, 250, 60);
        button.setVisible(true);
        button.setBorderPainted(false);
        button.setHorizontalAlignment(JButton.LEFT);
        button.addActionListener(this);
        frame.add(button);
    }

    /** Method to create the labels of file names
     * @param label, y - JLabel to assign the positioning to,
     * y is the y value at which to position the label*/
    public void createNameLabel(JLabel label, int y) {
        label.setBounds(90, y, 70, 20);
        label.setVisible(true);
        label.setHorizontalAlignment(JLabel.LEFT);
        frame.add(label);
    }

    /** Method to create the labels of levels saved
     * @param label, y - JLabel to assign the positioning to,
     * y is the y value at which to position the label*/
    public void createLevelLabel(JLabel label, int y) {
        label.setBounds(90, y, 70, 20);
        label.setVisible(true);
        label.setHorizontalAlignment(JLabel.LEFT);
        frame.add(label);
    }

    /** Method to load the contents of the three save files.
     * These contents are then assigned to variables in this class
     * and referenced by the GUI-creating methods*/
    public void loadContent() {
        ReadSaveFile load1 = new ReadSaveFile(sFile1);
        try {
            load1.readSaveFile();
            fileOneName = new JLabel(checkForEmpty(load1.getName(), iconFile1));
            fileOneLevel = new JLabel(checkForEmpty(load1.getLevel()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ReadSaveFile load2 = new ReadSaveFile(sFile2);
        try {
            load2.readSaveFile();
            fileTwoName = new JLabel(checkForEmpty(load2.getName(), iconFile2));
            fileTwoLevel = new JLabel(checkForEmpty(load2.getLevel()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ReadSaveFile load3 = new ReadSaveFile(sFile3);
        try {
            load3.readSaveFile();
            fileThreeName = new JLabel(checkForEmpty(load3.getName(), iconFile3));
            fileThreeLevel = new JLabel(checkForEmpty(load3.getLevel()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Method used to modify what is displayed if a file is empty
     * @param s, icon - s is the String that would be used by the label
     * displaying the file name, icon is the JButton that can have its icon
     * changed*/
    public String checkForEmpty(String s, JButton icon) {
        if (s == null) {
            s = "Empty File";
            icon.setIcon(emptyIcon);
        } else {
            icon.setIcon(loadIcon);
        }
        return s;
    }

    /** Overloading checkForEmpty method used to check if
     * file is empty and potentially change what is displayed by the label
     * @param i integer for level value in the save file*/
    public String checkForEmpty(int i) {
        String s;
        if (i == 0) {
            s  = "-";
        } else {
            s = "Level: " + i;
        }
        return s;
    }

    /** Setter for name variable
     * @param n String to set file name to*/
    public void setName(String n) { name = n; }

    /** Getter for name variable
     * @retrun n String of file name*/
    public String getName() {return name;}
    /** Getter for this frame
     * @retrun frame */
    public JFrame getFrame() {return frame;}

    /** Getter for the three buttons used by extensions
     * to identify which button was pressed
     * @param f integer that selects which of the three buttons
     *          to return - prevents from needing getter for
     *          each of the buttons
     * @retrun JButton object*/
    public JButton getFile(int f) {
        if (f == 1) {
            return iconFile1;
        } else if(f==2) {
            return iconFile2;
        } else {
            return iconFile3;
        }
    }

    /** Getter for the three file names
     * @param f integer that selects which of the three names
     *          to return - prevents from needing getter for
     *          each of the file names
     * @retrun String name of specified file*/
    public String getFileName(int f) {
        if (f == 1) {
            return sFile1;
        } else if(f==2) {
            return sFile2;
        } else {
            return sFile3;
        }
    }
    /** Getter for close JButton to close extensions of this class
     * @retrun JButton object */
    public JButton getClose() {return close;}

    /** Method to dispose frame and focus on the settings frame */
    public void closeFrame() {
        frame.dispose();
        settingsFrame.requestFocus();
    }
}

