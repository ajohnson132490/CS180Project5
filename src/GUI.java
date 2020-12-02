import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * BetterBook GUI
 *
 * This is the bread and butter of the system, the GUI that allows users to see
 * and interact with the social networking site.
 *
 * https://bit.ly/2KF8T36
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
 *
 * @author Austin Johnson | CS18000 Project 5 | Group 007-2
 * @version 25 November 2020
 */

public class GUI extends JComponent implements Runnable {
    /* ALL VARIABLES */
    private static final long serialVersionUID = -239376208864108143L; // Serial ID
    private Client client; // The shorthand name for the client
    Profile profile; // The current users profile
    private String hostname = "localhost"; // The host name for the client to connect to
    private int portNumber = 4242; // The port for the client to connect to
    GUI gui;
    ArrayList<String> interestsArrayList = new ArrayList<String>();
    
    // Buttons
    JButton deleteFriend = new JButton("-");
    JButton signInButton = new JButton("Sign In");
    JButton newAccountButton = new JButton("Create a New Account");
    JButton registerButton = new JButton("Register");
    JButton searchButton = new JButton("Search");
    JButton confirmButton = new JButton("Confirm");
    JButton confirmInterestsButton = new JButton("Confirm");
    JButton confirmFriendRequest[];
    JButton denyFriendRequest[];
    
    // Menu Bar
    JMenu accountMenu = new JMenu("Account");
    JMenuItem createAccount = new JMenuItem("Create a new account");
    JMenuItem editAccount = new JMenuItem("Edit your account");
    JMenuItem deleteAccount = new JMenuItem("Delete your account");
    JMenuItem requestList = new JMenuItem("Friend Requests");
    JMenu addFriend = new JMenu("+");
    
    // Text fields
    JTextField usernameField = new JTextField("Username");
    JTextField passwordField = new JTextField("Password");
    JTextField verifyPassword = new JTextField("Verify Password");
    JTextField nameField = new JTextField("Name");
    JTextField aboutMeField = new JTextField("Tell people about yourself");
    JTextField contactInformationField = new JTextField("Email or Phone #");
    JTextField searchField = new JTextField("Enter a Username:");
    JTextField interests[] = new JTextField[5];
    
    // Labels
    JLabel username;
    JLabel name;
    JLabel contactInformation;
    JLabel aboutMe = new JLabel();
    JLabel privacySetting;
    
    /**
     * This is the only constructor, it creates a default client object that connects
     * to localhost servers at port 4242.
     */
    public GUI() {
        // Creating a new client and passing it preset info to connect
        try {
            this.client = new Client(hostname, portNumber);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "There was an issue connecting to the server!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    /**
     * These are the two action listeners. The first one is for most buttons and text fields
     * The second one is for the menu bar on the profile page specifically.
     */
    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == signInButton) {
                try {
                    profile = client.signIn(usernameField.getText(), passwordField.getText());
                } catch (UserNotFoundError e1) {
                    JOptionPane.showMessageDialog(null, "Username or password is incorrect!\nPlease Try again!", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
                
            }
            if (e.getSource() == newAccountButton) {
                newAccountPage();
            }
            if (e.getSource() == registerButton) {
                if (passwordField.getText().equals(verifyPassword.getText())) {
                    profile = new Profile(usernameField.getText(), passwordField.getText(),
                            nameField.getText(), contactInformationField.getText());
                    profile.setAboutMe(aboutMeField.getText());
                }
                else {
                    JOptionPane.showMessageDialog(null, "Passwords do not match!", "ERROR",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if (e.getSource() == addFriend) { // Change button?
                // friendList();
            }
            if (e.getSource() == requestList) {
                // requestList();
            }
        }
    };
    
    ActionListener menuBarListener = e -> {
        if (e.getSource() == createAccount) {
            newAccountPage();
        }
        if (e.getSource() == editAccount) {
            editAccountPage();
        }
        if (e.getSource() == confirmButton) {
            Profile newProfile = new Profile(usernameField.getText(), passwordField.getText(),
                    nameField.getText(), contactInformationField.getText());
            newProfile.setAboutMe(aboutMeField.getText());
            try {
                client.updateProfile(profile, newProfile);
            } catch (UserNotFoundError e1) {
                JOptionPane.showMessageDialog(null, "Error updating profile!", "ERROR",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if (e.getSource() == confirmInterestsButton) {
            Profile newProfile = new Profile(profile.getUsername(), profile.getPassword(),
                    profile.getName(), profile.getContactInformation());
            newProfile.setLikesAndInterests(interestsArrayList);
            try {
                client.updateProfile(profile, newProfile);
            } catch (UserNotFoundError e1) {
                JOptionPane.showMessageDialog(null, "Error updating profile!", "ERROR",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    };
    
    
    /**
     * Allows the user to sign in
     *
     * Connects to the actionListener ActionListener
     */
    public void signInPage() {
        JFrame frame = new JFrame("Sign In");
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        usernameField.setPreferredSize(new Dimension(200, 30));
        c.gridx = 0;
        c.gridy = 0;
        frame.add(usernameField, c);
        
        passwordField.setPreferredSize(new Dimension(200, 30));
        c.insets = new Insets(10, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 2;
        frame.add(passwordField, c);
        
        c.gridx = 0;
        c.gridy = 4;
        frame.add(signInButton, c);
        
        c.gridx = 0;
        c.gridy = 5;
        newAccountButton.addActionListener(actionListener);
        frame.add(newAccountButton, c);
        
        //Adding some action listeners
        signInButton.addActionListener(e ->{
            try {
                profile = client.signIn(usernameField.getText(), passwordField.getText());
                frame.dispose();
                profilePage();
            } catch (NullPointerException e1) {
                System.out.println("Tried to hit sign in too early");
            } catch (UserNotFoundError e1) {
                JOptionPane.showMessageDialog(null, "Username or password is incorrect!\nPlease Try again!", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        newAccountButton.addActionListener(e -> {
            frame.dispose();
        });
        
        // Making the frame visible
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    
    /**
     * Allows the user to create a new account
     *
     * Connects to the actionListener ActionListener
     */
    public void newAccountPage() {
        JFrame frame = new JFrame("Create a New Account");
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        usernameField.setPreferredSize(new Dimension(200, 30));
        c.gridx = 0;
        c.gridy = 0;
        frame.add(usernameField, c);
        
        passwordField.setPreferredSize(new Dimension(200, 30));
        c.insets = new Insets(10, 0, 0, 0);
        c.gridy = 2;
        frame.add(passwordField, c);
        
        verifyPassword.setPreferredSize(new Dimension(200, 30));
        c.gridy = 4;
        frame.add(verifyPassword, c);
        
        nameField.setPreferredSize(new Dimension(200, 30));
        c.gridy = 6;
        frame.add(nameField, c);
        
        contactInformationField.setPreferredSize(new Dimension(200, 30));
        c.gridy = 8;
        frame.add(contactInformationField, c);
        
        aboutMeField.setPreferredSize(new Dimension(200, 30));
        c.gridy = 10;
        frame.add(aboutMeField, c);
        
        c.gridx = 0;
        c.gridy = 12;
        registerButton.addActionListener(actionListener);
        frame.add(registerButton, c);
        registerButton.addActionListener(e -> {
            try {
                client.createProfile(usernameField.getText(), passwordField.getText(),
                        nameField.getText(), contactInformationField.getText());
                profile = client.signIn(usernameField.getText(), passwordField.getText());
                profilePage();
                frame.dispose();
            } catch (UserNotFoundError e1) {
                System.out.println("Something went wrong");
            } catch (NullPointerException e1) {
                System.out.println("Null Pointer");
            }
        });
        
        
        // Making the frame visible
        frame.setSize(300, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        
    }
    
    /**
     * Allows the user to edit their account from the profile page
     *
     * Connects to the menuBarListener ActionListener
     */
    public void editAccountPage() {
        JFrame frame = new JFrame("Edit Your Account");
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        usernameField.setPreferredSize(new Dimension(200, 30));
        usernameField.setText(profile.getUsername());
        usernameField.setEditable(false);
        c.gridx = 0;
        c.gridy = 0;
        frame.add(usernameField, c);
        
        passwordField.setPreferredSize(new Dimension(200, 30));
        c.insets = new Insets(10, 0, 0, 0);
        c.gridy = 2;
        frame.add(passwordField, c);
        
        verifyPassword.setPreferredSize(new Dimension(200, 30));
        c.gridy = 4;
        frame.add(verifyPassword, c);
        
        nameField.setPreferredSize(new Dimension(200, 30));
        c.gridy = 6;
        frame.add(nameField, c);
        
        contactInformationField.setPreferredSize(new Dimension(200, 30));
        c.gridy = 8;
        frame.add(contactInformationField, c);
    
        aboutMeField.setPreferredSize(new Dimension(200, 30));
        c.gridy = 10;
        frame.add(aboutMeField, c);
        
        c.gridx = 0;
        c.gridy = 12;
        confirmButton.addActionListener(menuBarListener);
        frame.add(confirmButton, c);
        confirmButton.addActionListener(e -> {
            usernameField.setEditable(true);
            if (passwordField.getText().equals(verifyPassword.getText())) {
                profile.setPassword(passwordField.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Passwords do not match!\nPlease try again", "Passwords Do not " +
                        "match!", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                editAccountPage();
            }
            profile.setName(nameField.getText());
            name.setText(nameField.getName());
            profile.setContactInformation(contactInformationField.getText());
            contactInformation.setText(contactInformationField.getText());
            profile.setAboutMe(aboutMeField.getText());
            aboutMe.setText(aboutMeField.getText());
            profilePage();
            frame.dispose();
        });
        
        
        // Making the frame visible
        frame.setSize(300, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        
    }
    
    /**
     * Allows the user to add up to 5 interests to their profile
     */
    public void addInterestsPage() {
        JFrame frame = new JFrame("Add Interests");
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        for (int i = 0; i < interests.length; i++) {
            c.gridy = i;
            c.insets = new Insets(10, 0, 0, 0);
            interests[i] = new JTextField("Interest " + (i+1) +":");
            interests[i].setPreferredSize(new Dimension(250, 30));
            interests[i].setBorder(new EmptyBorder(2, 5, 2, 2));
            frame.add(interests[i], c);
        }
        
        c.gridx = 0;
        c.gridy = 22;
        confirmInterestsButton.addActionListener(menuBarListener);
        frame.add(confirmInterestsButton, c);
        confirmInterestsButton.addActionListener(e -> {
            for (int i = 0; i < interests.length; i ++) {
                interestsArrayList.add(interests[i].getText());
            }
            profile.setLikesAndInterests(interestsArrayList);
            frame.dispose();
            profilePage();
        });
        
        
        // Making the frame visible
        frame.setSize(300, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setBackground(Color.decode("#246EB9"));
    }
    /**
     * This page displays the main JFrame where all the other information is kept.
     */
    public void profilePage() {
        // Creating the frame
        JFrame frame = new JFrame("BetterBook");
        frame.setResizable(false);
        //frame.setUndecorated(true); This line makes the program borderless. It's nice but not good for testing
        Container content = frame.getContentPane();
        content.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints(5, 5, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0);
        gui = new GUI();
        frame.add(gui, c);
        
        //Adding the content to the frame
        createMenuBar(frame);
        c.gridx = 0;
        c.gridy = 0;
        frame.add(displayUserInformation(), c);
        c.gridx = 1;
        frame.add(displayUserFriendList(), c);
        c.gridx = 0;
        c.gridy = -1;
        frame.add(displayUserInterestList(), c);
        c.gridx = 1;
        frame.add(displayPendingFriendRequests(), c);
        
        confirmButton.addActionListener(e ->{
            if (e.getSource() == confirmButton) {
                frame.dispose();
            }
        });
        confirmInterestsButton.addActionListener(e ->{
            if (e.getSource() == confirmInterestsButton) {
                frame.dispose();
            }
        });
        
        /// Making the frame visible
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content.setBackground(Color.decode("#246EB9"));
        frame.setVisible(true);
    }
    
    /**
     * This page creates the menu bar at the top of the profile page
     *
     * @param frame frame to add the menu bar to
     */
    public void createMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        // Account tab
        accountMenu.add(createAccount);
        createAccount.addActionListener(menuBarListener);
        accountMenu.add(editAccount);
        editAccount.addActionListener(menuBarListener);
        accountMenu.add(deleteAccount);
        deleteAccount.addActionListener(e -> {
            if (e.getSource() == deleteAccount) {
                int selection = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account?\n"
                                + "This operation cannot be reversed!", "We're sorry to see you go...",
                        JOptionPane.YES_NO_OPTION);
                if (selection == JOptionPane.YES_OPTION) {
                    signInPage();
                    frame.dispose();
                    try {
                        client.deleteProfile(profile);
                    } catch (NullPointerException e1) {
                        System.out.println("Null pointer on account delete. Still Runs.");
                    } catch (UserNotFoundError e1) {
                        JOptionPane.showMessageDialog(null, "Could not delete profile!", "ERROR",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } else if (selection == JOptionPane.NO_OPTION) {
                    frame.dispose();
                }
            }
        });
        menuBar.add(accountMenu);
        
        // Add Space so that the search Bar is on the left side
        menuBar.add(Box.createHorizontalGlue());
        
        /// Adding the friend Search Bar
        // Text field
        GridBagConstraints c = new GridBagConstraints();
        searchField.setPreferredSize(new Dimension(200, 30));
        searchField.setMaximumSize(searchField.getPreferredSize());
        c.gridx = GridBagConstraints.LINE_END - 2;
        c.gridy = 0;
        menuBar.add(searchField, c);
        
        // Button
        c.gridx = GridBagConstraints.LINE_END;
        c.gridy = 0;
        menuBar.add(searchButton, c);
        searchButton.addActionListener(e ->{
            System.out.println("Something is freezing it");
            try {
                if (client.locateProfile(searchField.getText()) != null) {
                    int selection = JOptionPane.showConfirmDialog(null, "Would you like to send "
                                    + searchField.getText() + "\na friend request?", "User found!",
                            JOptionPane.YES_NO_OPTION);
                    switch (selection) {
                        case 0:
                            client.sendFriendRequest(profile, client.locateProfile(searchField.getText()));
                            break;
                    }
                }
            } catch (NullPointerException e1) {
                JOptionPane.showMessageDialog(null, "User not found!", "User not found",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        // Adding the menuBar to the profile Page
        frame.setJMenuBar(menuBar);
    }
    
    /**
     * This displays the users username, real name, contact information, and privacy setting
     */
    public JPanel displayUserInformation() {
        //Creating info buffer for precise location
        JPanel infoBuffer = new JPanel();
        infoBuffer.setLayout(new BoxLayout(infoBuffer, BoxLayout.Y_AXIS));
        infoBuffer.setBorder(new EmptyBorder(25, 25, 0, 0));
        infoBuffer.setBackground(Color.decode("#246EB9"));
        
        // Creating the Info Box
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBorder(new EmptyBorder(15,15,15,15));
        info.setMinimumSize(new Dimension(450, 200));
        info.setSize(new Dimension(450, 200));
        infoBuffer.add(info);
        
        username = new JLabel("Username: " + profile.getUsername());
        username.setBorder(new EmptyBorder(0, 0, 15, 0));
        username.setFont(new Font("Verdana", Font.PLAIN, 18));
        
        name = new JLabel("Name: " + profile.getName());
        name.setBorder(new EmptyBorder(0, 0, 15, 0));
        name.setFont(new Font("Verdana", Font.PLAIN, 18));
        
        contactInformation = new JLabel("Contact Info:" + profile.getContactInformation());
        contactInformation.setBorder(new EmptyBorder(0, 0, 15, 0));
        contactInformation.setFont(new Font("Verdana", Font.PLAIN, 18));
        
        if (!profile.getAboutMe().equals("")) {
            aboutMe = new JLabel(profile.getAboutMe());
            aboutMe.setBorder(new EmptyBorder(0, 0, 15, 0));
            aboutMe.setFont(new Font("Verdana", Font.PLAIN, 18));
        }
        
        privacySetting = new JLabel("Curret Privacy Setting: " + profile.getPrivacySetting());
        privacySetting.setBorder(new EmptyBorder(0, 0, 0, 0));
        privacySetting.setFont(new Font("Verdana", Font.PLAIN, 18));
        
        /// Adding information
        // The Glue puts space between the lines
        info.add(username);
        info.add(Box.createGlue());
        info.add(name);
        info.add(Box.createGlue());
        info.add(contactInformation);
        info.add(Box.createGlue());
        try {
            info.add(aboutMe);
        } catch (NullPointerException e) {
        
        }
        info.add(Box.createGlue());
        info.add(privacySetting);
        
        // Showing the panel
        info.setVisible(true);
        info.setBackground(Color.decode("#F0CEA0"));
        return infoBuffer;
    }
    
    /**
     * This displays the users friend list in the order that it is stored
     */
    public JPanel displayUserFriendList() {
        //Creating friend buffer for precise position
        JPanel friendPanelBuffer = new JPanel();
        friendPanelBuffer.setLayout(new BoxLayout(friendPanelBuffer, BoxLayout.Y_AXIS));
        friendPanelBuffer.setBorder(new EmptyBorder(25, 25, 0, 25));
        friendPanelBuffer.setBackground(Color.decode("#246EB9"));
        
        //Creating the friend Panel
        JPanel internalFriendPanel = new JPanel();
        internalFriendPanel.setLayout(new BoxLayout(internalFriendPanel, BoxLayout.Y_AXIS));
        internalFriendPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        internalFriendPanel.setMinimumSize(new Dimension(250, 200));
        internalFriendPanel.setMaximumSize(new Dimension(250, 200));
        friendPanelBuffer.add(internalFriendPanel);
        ArrayList <Profile> friends = profile.getFriendsList();
        
        //Default action if no friends exist
        if (friends.size() >= 0) {
            JLabel emptyFriendList = new JLabel("<html>To add friends, search a username in the top search bar!</html>");
            emptyFriendList.setBorder(new EmptyBorder(0, 0, 15, 0));
            emptyFriendList.setFont(new Font("Verdana", Font.PLAIN, 15));
            internalFriendPanel.add(emptyFriendList);
        } else {
            //Adding any friends that have accepted the friend request
            for (Profile f: profile.getFriendsList()) {
                JLabel currentFriend = new JLabel(f.getUsername());
                currentFriend.setBorder(new EmptyBorder(0, 0, 15, 0));
                currentFriend.setFont(new Font("Verdana", Font.PLAIN, 15));
                internalFriendPanel.add(currentFriend);
            }
        }
        //Adding the friend buffer and panel to the display at gridx 1 gridy 0
        internalFriendPanel.setVisible(true);
        internalFriendPanel.setBackground(Color.decode("#F0CEA0"));
        return friendPanelBuffer;
    }
    
    /**
     * This displays the users interest list, substituting for a button if no interests
     * are listed
     */
    public JPanel displayUserInterestList() {
        //Creating a buffer for the interests panel
        JPanel interestsBuffer = new JPanel();
        interestsBuffer.setLayout(new BoxLayout(interestsBuffer, BoxLayout.Y_AXIS));
        interestsBuffer.setBorder(new EmptyBorder(0, 50, 0, 25));
        interestsBuffer.setBackground(Color.decode("#246EB9"));
        
        //Creating the interest Panel
        JPanel interestsPanel = new JPanel();
        JLabel interestsTitle = new JLabel("<html>Likes and Interests:</html>");
        interestsTitle.setFont(new Font("Verdana", Font.PLAIN, 16));
        interestsPanel.add(interestsTitle);
        interestsPanel.setLayout(new BoxLayout(interestsPanel, BoxLayout.Y_AXIS));
        interestsPanel.setBorder(new EmptyBorder(15, 50, 15, 50));
        interestsPanel.setMinimumSize(new Dimension(300, 200));
        interestsPanel.setMaximumSize(new Dimension(300, 200));
        
        interestsBuffer.add(interestsPanel);
        
        //Default action if no friends exist
        if (interestsArrayList.size() == 0) {
            //Creating a one time deal button to add some interests
            JButton emptyInterestsList = new JButton("Add likes and interests");
            emptyInterestsList.setBorder(new EmptyBorder(15, 15, 15, 15));
            emptyInterestsList.setFont(new Font("Verdana", Font.PLAIN, 15));
            
            //Adding that button to the panel
            interestsPanel.add(emptyInterestsList);
            
            //Setting borders to make the title and button look nice
            interestsPanel.setBorder(new EmptyBorder(15, 50, 15, 50));
            interestsTitle.setBorder(new EmptyBorder(15,15,15,15));
            
            //Adding an event listener to remove the button if it is clicked
            emptyInterestsList.addActionListener(e ->{
                if (e.getSource() == emptyInterestsList) {
                    addInterestsPage();
                }
            });
        } else {
            //Adding any friends that have accepted the friend request
            for (int i = 0; i < interestsArrayList.size(); i ++) {
                JLabel currentInterest = new JLabel(interestsArrayList.get(i));
                currentInterest.setBorder(new EmptyBorder(0, 0, 2, 0));
                currentInterest.setFont(new Font("Verdana", Font.PLAIN, 15));
                interestsPanel.add(currentInterest);
                
                //Setting borders to make the title and interests look nice
                interestsPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
                interestsTitle.setBorder(new EmptyBorder(15,50,15,50));
            }
        }
        //Adding the friend buffer and panel to the display at gridx 1 gridy 0
        interestsPanel.setVisible(true);
        interestsPanel.setBackground(Color.decode("#F0CEA0"));
        return interestsBuffer;
    }
    
    /**
     * This displays the users pending friend requests, with a default message if
     * no requests are found
     */
    public JPanel displayPendingFriendRequests() {
        //Creating friend buffer for precise position
        JPanel friendPanelBuffer = new JPanel();
        friendPanelBuffer.setLayout(new BoxLayout(friendPanelBuffer, BoxLayout.Y_AXIS));
        friendPanelBuffer.setBorder(new EmptyBorder(0, 25, 0, 150));
        friendPanelBuffer.setBackground(Color.decode("#246EB9"));
        
        //Creating the friend Panel
        JPanel requestPanel = new JPanel();
        requestPanel.setLayout(new BoxLayout(requestPanel, BoxLayout.Y_AXIS));
        requestPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        requestPanel.setMinimumSize(new Dimension(300, 200));
        requestPanel.setMaximumSize(new Dimension(300, 200));
        
        
        //Adding the panel to the buffer
        friendPanelBuffer.add(requestPanel);
        
        //Getting a local list of all the friend requests
        ArrayList <Profile> friends = profile.getFriendRequestList();
        
        //Default action if no friends exist
        if (friends.size() >= 0) {
            JLabel emptyFriendList = new JLabel("<html>No Pending Friend Requests...</html>");
            emptyFriendList.setBorder(new EmptyBorder(0, 15, 15, 15));
            emptyFriendList.setFont(new Font("Verdana", Font.PLAIN, 15));
            requestPanel.add(emptyFriendList);
        } else {
            //Loading up the right number of friend request buttons
            confirmFriendRequest = new JButton[friends.size()];
            denyFriendRequest = new JButton[friends.size()];
            
            //Adding any friends that have accepted the friend request
            for (int i = 0; i < friends.size(); i++) {
                //Setting the button to be yes or no
                confirmFriendRequest[i] = new JButton("✓");
                denyFriendRequest[i] = new JButton("X");
                
                //Creating a panel for the current request
                JPanel currentRequest = new JPanel();
                currentRequest.setLayout(new BoxLayout(currentRequest, BoxLayout.X_AXIS));
                currentRequest.setBorder(new EmptyBorder(15, 15, 15, 15));
                currentRequest.setMinimumSize(new Dimension(200, 20));
                currentRequest.setMaximumSize(new Dimension(200, 20));
                
                //Loading the username
                JLabel currentFriend = new JLabel(friends.get(i).getUsername());
                currentFriend.setBorder(new EmptyBorder(0, 0, 15, 0));
                currentFriend.setFont(new Font("Verdana", Font.PLAIN, 15));
                currentRequest.add(currentFriend);
                currentRequest.add(Box.createHorizontalBox());
                
                //Adding the buttons
                currentRequest.add(confirmFriendRequest[i]);
                currentRequest.add(denyFriendRequest[i]);
                requestPanel.add(currentRequest);
                
            }
        }
        //Adding the friend buffer and panel to the display at gridx 1 gridy 0
        requestPanel.setVisible(true);
        requestPanel.setBackground(Color.decode("#F0CEA0"));
        return friendPanelBuffer;
    }
    
    public void run() {
        // Running the sign in page
        signInPage();
    }
    
    public static void main(String[] args) {
        // Running the GUI
        SwingUtilities.invokeLater(new GUI());
    }
}
