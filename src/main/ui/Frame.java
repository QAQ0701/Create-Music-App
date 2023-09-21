package ui;

import model.AllScore;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

//Initiates frame and prompts title page.
public class Frame extends JFrame implements ActionListener, WindowListener {
    private static final String SET = "set";
    private static final String DISABLE = "disable";
    private static final String NORMAL = "normal";
    private static final String SURPRISE = "surprise";
    private static final Integer FRAME_WIDTH = 900;
    private static final Integer FRAME_HEIGHT = 688;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/score.json";

    private static Timer timer;

    private static MenuButton normal;
    private static MenuButton surprise;
    private static MenuButton newStart;
    private static MenuButton load;
    private static MenuButton save;
    private static MenuButton edit;
    //private static MenuButton quit;


    private static ImageIconLibrary backdrop = new ImageIconLibrary();
    private static IconButtonLibrary add = new IconButtonLibrary();
    private static StaffPanel staffPanel;


    private static Panel panelWest;
    private static Panel panelEast;
    private static Panel panelSouth;

    private static Panel iconPanel;

    protected static AllScore allScore;

    //Modifies: this
    //EFFECT: Initiates and set up layout
    Frame() {
        setUp();
        staffPanel = new StaffPanel();
        backdrop.setImageIcon(ImageIconLibrary.getTitlePageScaledRatio());
        this.add(backdrop);
        this.pack();
        setPanel();
        initializeButton();
        setNewLoadButton(SET);
        //timer = new Timer(4500, taskPerformer);
        //timer.setRepeats(false);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);


        //setQuitButton(SET);

    }

    //Modifies: this
    //EFFECT: setUp this Frame
    public void setUp() {

        this.setTitle("Score"); //sets title of frame
        //this.setIconImage(title.getImage()); //change icon of frame
        //this.getContentPane().setBackground(Color.BLACK); //change colour of background
        //this.getContentPane().setBackground(new Color(200,100,60)); //RGB Value 0-255

        //setup
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
        this.setResizable(false); //prevents frame from being resized
        //BORDER LAYOUT
        //this.setLayout(new BorderLayout()); //can also do FlowLayout
        //this.setLayout(new BorderLayout(10,5))
        this.setLayout(new BorderLayout());
        //this.setLocationRelativeTo(null);
        //this.setSize(FRAME_WIDTH, FRAME_HEIGHT); //sets x, y dimension of frame
        this.setVisible(true); //make frame visible
        //this.pack(); //label expand and shrinks with window
        // this.add(label);

        addWindowListener(this);

    }

    //Modifies: this
    //EFFECT: initializes all buttons
    public void initializeButton() {
        //button.setIcon(icon);

        //BUTTONS:
        newStart = new MenuButton();
        load = new MenuButton();
        normal = new MenuButton();
        surprise = new MenuButton();
        edit = new MenuButton();
        save = new MenuButton();
        //quit = new MenuButton();


    }

    //Modifies: this
    //EFFECT: formats new and load button
    private void setNewLoadButton(String command) {
        if (command.equals("set")) {
            titleButton(newStart);
            titleButton(load);
            newStart.setText(" Start New Score ");
            newStart.setAlignmentX(Component.CENTER_ALIGNMENT);
            //newStart.setBounds(370, 440, 230, 60);
            load.setText(" Load Previous ");
            load.setAlignmentX(Component.CENTER_ALIGNMENT);
            //load.setBounds(450, 530, 230, 60);
            addButtonsToTitlePage(newStart, load);
        } else if (command.equals("disable")) {
            newStart.disableButton();
            load.disableButton();
        }
    }

    //Modifies: this
    //EFFECT: formats normal and surprise button
    private void setNormalSurpriseButton(String command) {
        if (command.equals(SET)) {
            titleButton(normal);
            //titleButton(surprise);
            normal.setPreferredSize(new Dimension(250, 50));
            surprise.setPreferredSize(new Dimension(250, 50));
            surprise.disableButton();

            normal.setText(" Normal Mode ");
            normal.setAlignmentX(Component.CENTER_ALIGNMENT);
            //normal.setBounds(370, 440, 200, 60);
            surprise.setText(" Easter Egg ");
            surprise.setAlignmentX(Component.CENTER_ALIGNMENT);
            // surprise.setBounds(450, 530, 230, 60);
        } else if (command.equals(DISABLE)) {
            normal.disableButton();
            surprise.disableButton();
        }
    }

    /*//Modifies: this
    //EFFECT:
    private void setQuitButton(String command) {
        if (command.equals(SET)) {
            titleButton(quit);
            quit.setPreferredSize(new Dimension(250, 50));
            quit.setText(" QUIT ");
            quit.setAlignmentX(Component.LEFT_ALIGNMENT);
            quit.setAlignmentY(Component.BOTTOM_ALIGNMENT);
            panelSouth.add(quit);
        } else if (command.equals(DISABLE)) {
            quit.disableButton();
        }

    }*/

    //Modifies: this
    //EFFECT: formats buttons on titlepage
    private void titleButton(MenuButton button) {

        button.addActionListener(this);
        button.setPreferredSize(new Dimension(250, 90));

        //button.setHorizontalTextPosition(JButton.CENTER);
        //button.setVerticalTextPosition(JButton.BOTTOM);
        button.setFont(new Font("Comic Sans", Font.BOLD, 25));
        //button.setIconTextGap(-8);
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(255, 236, 224));
        button.setBorder(BorderFactory.createEtchedBorder());
    }

    //Modifies: this
    //EFFECT: adds button to titlePage
    public void addButtonsToTitlePage(MenuButton b1, MenuButton b2) {
        //titlePage.add(b1);
        // titlePage.add(b2);
        panelSouth.add(b1);
        panelSouth.add(Box.createRigidArea(new Dimension(230, 20)));
        panelSouth.add(b2);
    }


    //Modifies: this
    //EFFECT: formats all the panels of titlepage
    public void setPanel() {
        //PANELS:
        /*panelNorth = new Panel(Color.YELLOW);
        panelWest = new Panel(Color.RED);*/

        panelSouth = new Panel();
        panelSouth.setLayout(new BoxLayout(panelSouth, BoxLayout.Y_AXIS));
        panelSouth.setOpaque(false);

        panelEast = new Panel();
        panelEast.setBackground(Color.WHITE);
        panelEast.setLayout(new BoxLayout(panelEast, BoxLayout.Y_AXIS));


        panelWest = new Panel();
        panelWest.setBackground(Color.GRAY);
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));

        iconPanel = new Panel();
        iconPanel.setLayout(new FlowLayout());

        // panelCenter = new Panel();
        // panelCenter.setLayout(new );

        /*panelNorth.setPreferredSize(new Dimension(100, 100));
        */
        panelSouth.setPreferredSize(new Dimension(100, 220));
        backdrop.add(panelSouth, BorderLayout.SOUTH);

        panelEast.setPreferredSize(new Dimension(150, 100));
        backdrop.add(panelEast, BorderLayout.EAST);


        panelWest.setPreferredSize(new Dimension(750, 100));
        backdrop.add(panelWest, BorderLayout.WEST);


        //panelCenter.setPreferredSize(new Dimension(100, 100));

        //this.add(panelNorth, BorderLayout.NORTH);
        //this.add(panelWest, BorderLayout.WEST);
        //this.add(panelSouth, BorderLayout.SOUTH);

        //this.add(panelCenter, BorderLayout.CENTER);
    }

    /*//Modifies: this
    //EFFECT:
    ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            backdrop.setImageIcon(ImageIconLibrary.getSpookyBgScaledRatio());
            setStaff(SURPRISE);
            System.out.println("timer event triggered");

        }
    };*/

    //Modifies: this
    //EFFECT: listens for buttons being clicked and provides feedback
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newStart || e.getSource() == load) {
            if (e.getSource() == newStart) {
                System.out.println("new");//stub
            } else {
                System.out.println("load");//stub
                loadScore();
            }
            setNewLoadButton(DISABLE);
            setNormalSurpriseButton(SET);
            addButtonsToTitlePage(normal, surprise);
        } else if (e.getSource() == normal || e.getSource() == surprise) {
            if (e.getSource() == normal) {
                System.out.println("normal");//stub
                setNormalSurpriseButton(DISABLE);
                backdrop.setImageIcon(ImageIconLibrary.getBgScaledRatio());
                setStaff(NORMAL);
            }
        } else if (e.getSource() == save) {
            saveScore();
        } else if (e.getSource() == edit) {
            //IconButtonLibrary.deactivateAll();
            PainterNoteRest.setIsMenu(true);
        }
    }

    /* else {
                System.out.println("surprise");//stub
                setNormalSurpriseButton(DISABLE);
                backdrop.setImageIcon(ImageIconLibrary.getSpookyGifRatio());
                timer.start();
            }*/

    // EFFECTS: saves the score to file
    private void saveScore() {
        try {
            jsonWriter.open();
            jsonWriter.write(StaffPanel.allScore);
            jsonWriter.close();
            System.out.println("Saved Score to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads allScore from file
    private void loadScore() {
        try {
            allScore = jsonReader.read();
            staffPanel.setAllScore(allScore);
            staffPanel.load();
            System.out.println("Loaded Score from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }



    //Modifies: this
    //EFFECT: sets panel for the 5 line staff
    private void setStaff(String command) {
        panelSouth.setPreferredSize(new Dimension(100, 0));//remove bottom panel
        staffPanel = new StaffPanel();

        if (command.equals(NORMAL)) {
            PainterStaff.setColor(Color.BLACK);
        } else if (command.equals(SURPRISE)) {
            PainterStaff.setColor(Color.WHITE);
        }
        setPanelWest();
        setPanelEast();

    }

    //Modifies: this
    //EFFECT: formats panelWest
    public void setPanelWest() {
        panelWest.add(Box.createRigidArea(new Dimension(700, 50)));
        panelWest.add(staffPanel.getLine1());
        panelWest.add(staffPanel.getLine2());
        panelWest.add(staffPanel.getLine3());
        panelWest.add(staffPanel.getLine4());
        panelWest.add(Box.createRigidArea(new Dimension(700, 40)));
    }

    //Modifies: this
    //EFFECT: formats panelEast
    public void setPanelEast() {
        JLabel header = new JLabel();
        header.setText("Add: ");
        header.setForeground(Color.darkGray);
        //header.setPreferredSize(new Dimension(150, 30));
        header.setFont(new Font("Comic Sans", Font.BOLD, 20));
        //header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setOpaque(false);

        edit.setText(" Delete ");
        edit.setAlignmentX(Component.CENTER_ALIGNMENT);
        save.setText(" Save ");
        save.setAlignmentX(Component.CENTER_ALIGNMENT);

        formatEditSave(edit);
        formatEditSave(save);
        setIconPanel();

        panelEast.add(Box.createRigidArea(new Dimension(700, 20)));
        panelEast.add(header);
        panelEast.add(Box.createRigidArea(new Dimension(700, 15)));
        panelEast.add(iconPanel);
        panelEast.add(Box.createRigidArea(new Dimension(700, 50)));
    }


    //EFFECT: listens for music note and rest buttons
    public void iconButtonAddActionListener() {
        IconButtonLibrary.getWholeNote().addActionListener(staffPanel);
        IconButtonLibrary.getHalfNote().addActionListener(staffPanel);
        IconButtonLibrary.getQuarterNote().addActionListener(staffPanel);
        IconButtonLibrary.getEightNote().addActionListener(staffPanel);
        IconButtonLibrary.getSixteenNote().addActionListener(staffPanel);
        IconButtonLibrary.getWholeRest().addActionListener(staffPanel);
        IconButtonLibrary.getHalfRest().addActionListener(staffPanel);
        IconButtonLibrary.getQuarterRest().addActionListener(staffPanel);
        IconButtonLibrary.getEightRest().addActionListener(staffPanel);
        IconButtonLibrary.getSixteenRest().addActionListener(staffPanel);

    }

    //Modifies: this
    //EFFECT: formats panel used to contain the icon Buttons
    public void setIconPanel() {

        iconPanel.add(IconButtonLibrary.getWholeNote());
        iconPanel.add(IconButtonLibrary.getHalfNote());
        iconPanel.add(IconButtonLibrary.getQuarterNote());
        iconPanel.add(IconButtonLibrary.getEightNote());
        iconPanel.add(IconButtonLibrary.getSixteenNote());

        iconPanel.add(IconButtonLibrary.getWholeRest());
        iconPanel.add(IconButtonLibrary.getHalfRest());
        iconPanel.add(IconButtonLibrary.getQuarterRest());
        iconPanel.add(IconButtonLibrary.getEightRest());
        iconPanel.add(IconButtonLibrary.getSixteenRest());

        iconButtonAddActionListener();

        Panel pad = new Panel();
        pad.setPreferredSize(new Dimension(150, 15));
        iconPanel.add(pad);
        iconPanel.add(edit);
        iconPanel.add(save);
    }

    //Modifies: this
    //EFFECT: formats edit and save button
    private void formatEditSave(MenuButton button) {

        button.addActionListener(this);
        button.setMinimumSize(new Dimension(100, 50));
        button.setPreferredSize(new Dimension(130, 50));

        //button.setHorizontalTextPosition(JButton.CENTER);
        //button.setVerticalTextPosition(JButton.BOTTOM);
        button.setFont(new Font("Comic Sans", Font.BOLD, 20));
        //button.setIconTextGap(-8);
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(255, 236, 224));
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setAlignmentY(Component.TOP_ALIGNMENT);
    }


    //getters
    public Integer getFrameWidth() {
        return FRAME_WIDTH;
    }

    public Integer getFrameHeight() {
        return FRAME_HEIGHT;
    }

    public Panel getPanelEast() {
        return panelEast;
    }

    public Panel getIconPanel() {
        return iconPanel;
    }

   //EFFECT: NONE
    @Override
    public void windowOpened(WindowEvent e) {
        //nothing happens
    }

    //EFFECT: Prints out Event Log
    @Override
    public void windowClosing(WindowEvent e) {
        AllScore.printEventLog();
    }

    //EFFECT: NONE
    @Override
    public void windowClosed(WindowEvent e) {
        AllScore.printEventLog();
    }

    //EFFECT: NONE
    @Override
    public void windowIconified(WindowEvent e) {

    }

    //EFFECT: NONE
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    //EFFECT: NONE
    @Override
    public void windowActivated(WindowEvent e) {

    }

    //EFFECT: NONE
    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
