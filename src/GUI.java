import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * BetterBook GUI
 * <p>
 * This is the bread and butter of the system, the GUI that allows users to see
 * and interact with the social networking site.
 * <p>
 * https://bit.ly/2KF8T36
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
 * https://www.tutorialspoint.com/how-to-create-a-jlabel-with-an-image-icon-in-java
 * https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
 * https://stackoverflow.com/questions/13334198/java-custom-buttons-in-showinputdialog
 *
 * @author Austin Johnson | CS18000 Project 5 | Group 007-2
 * @version 6 December 2020
 */

public class GUI extends JComponent implements Runnable {
    /* ALL VARIABLES */
    private static final long serialVersionUID = -239376208864108143L; // Serial ID
    private Client client; // The shorthand name for the client
    Profile profile; // The current users profile
    private final String hostname = "localhost"; // The host name for the client to connect to
    private final int portNumber = 4242; // The port for the client to connect to
    GUI gui;
    ArrayList<String> interestsArrayList = new ArrayList<String>();
    
    // Buttons
    JButton addFriend = new JButton("+");
    JButton signInButton = new JButton("Sign In");
    JButton newAccountButton = new JButton("Create a New Account");
    JButton registerButton = new JButton("Register");
    JButton confirmButton = new JButton("Confirm");
    JButton confirmInterestsButton = new JButton("Confirm");
    JButton[] confirmFriendRequest;
    JButton[] denyFriendRequest;
    JButton refresh = new JButton("Refresh");
    
    // Menu Bar
    JMenu accountMenu = new JMenu("Account");
    JMenuItem editAccount = new JMenuItem("Edit your account");
    JMenuItem editInterests = new JMenuItem("Edit your interests");
    JMenuItem deleteAccount = new JMenuItem("Delete your account");
    
    JMenu allUsersMenu = new JMenu("Users");
    JMenuItem[] allUsers;
    
    JMenu pendingFriendRequests = new JMenu("Friend Requests");
    JMenuItem[] allRequests;
    
    // Text fields
    JTextField usernameField = new JTextField("Username");
    JTextField passwordField = new JTextField("Password");
    JTextField verifyPassword = new JTextField("Verify Password");
    JTextField nameField = new JTextField("Name");
    JTextField aboutMeField = new JTextField("Tell people about yourself");
    JTextField contactInformationField = new JTextField("Email or Phone #");
    JTextField[] interests = new JTextField[5];
    JButton editPictureButton = new JButton("Edit Profile Picture");
    
    // Labels
    JLabel username;
    JLabel name;
    JLabel contactInformation;
    JLabel aboutMe = new JLabel();
    JLabel privacySetting;
    JLabel profilePicture;
    
    // Profile Picture
    File file;
    JFileChooser fileChooser;
    int chooserResponse;
    BufferedImage image;
    Image resizedImage;
    
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
                } else {
                    JOptionPane.showMessageDialog(null, "Passwords do not match!", "ERROR",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    };
    
    ActionListener menuBarListener = e -> {
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
        if (e.getSource() == editInterests) {
            addInterestsPage();
        }
        
    };
    
    /**
     * Allows the user to sign in
     * <p>
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
        signInButton.addActionListener(e -> {
            try {
                profile = client.signIn(usernameField.getText(), passwordField.getText());
                frame.dispose();
                profilePage();
            } catch (NullPointerException e1) {
                System.out.println("Tried to hit sign in too early");
                e1.printStackTrace();
            } catch (UserNotFoundError e1) {
                JOptionPane.showMessageDialog(null,
                        "Username or password is incorrect!\nPlease Try again!", "ERROR",
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
     * <p>
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
     * <p>
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
        
        c.gridy = 12;
        frame.add(editPictureButton, c);
        editPictureButton.addActionListener(e -> {
            fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".jpg, .png, .jpeg", "jpg", "png", "jpeg");
            fileChooser.setFileFilter(filter);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooserResponse = fileChooser.showOpenDialog(null);
            if (chooserResponse == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                try {
                    image = ImageIO.read(file);
                    Profile newProfile = new Profile(profile);
                    newProfile.setProfilePicture(image);
                    System.out.println("This is the new pfp: " + newProfile.getProfilePicture()); //test
                    try {
                        profile = client.signIn(profile.getUsername(), profile.getPassword());
                        client.updateProfile(profile, newProfile);
                        System.out.println("Client updated"); //test
                    } catch (UserNotFoundError e1) {
                        JOptionPane.showMessageDialog(null, "Error updating profile!",
                                "ERROR", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        editAccountPage();
                    }
                } catch (IOException f) {
                    JOptionPane.showMessageDialog(null, "Invalid file format!", "ERROR",
                            JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    editAccountPage();
                }
            }
            profilePage();
            frame.dispose();
        });
        
        c.gridx = 1;
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
            profile.setContactInformation(contactInformationField.getText());
            if (aboutMeField.getText().equals("Tell people about yourself")) {
                profile.setAboutMe(aboutMe.getText());
            }
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
            interests[i] = new JTextField("Interest " + (i + 1) + ":");
            interests[i].setPreferredSize(new Dimension(250, 30));
            interests[i].setBorder(new EmptyBorder(2, 5, 2, 2));
            frame.add(interests[i], c);
        }
        
        c.gridx = 0;
        c.gridy = 22;
        confirmInterestsButton.addActionListener(menuBarListener);
        frame.add(confirmInterestsButton, c);
        confirmInterestsButton.addActionListener(e -> {
            for (int i = 0; i < interests.length; i++) {
                interestsArrayList.add(interests[i].getText());
            }
            try {
                profile = client.signIn(profile.getUsername(), profile.getPassword());
            } catch (UserNotFoundError userNotFoundError) {
                userNotFoundError.printStackTrace();
            }
            profile.setLikesAndInterests(interestsArrayList);
            client.sendProfiles();
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
        try {
            profile = client.signIn(profile.getUsername(), profile.getPassword());
        } catch (UserNotFoundError userNotFoundError) {
            userNotFoundError.printStackTrace();
        }
        System.out.println(profile.getFriendsList().toString());
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
        try {
            createMenuBar(frame);
            c.gridx = 0;
            c.gridy = 0;
            frame.add(displayUserInformation(profile), c);
            c.gridx = 1;
            frame.add(displayUserFriendList(profile), c);
            c.gridx = 0;
            c.gridy = -1;
            frame.add(displayUserInterestList(profile), c);
            c.gridx = 1;
            frame.add(displayPendingFriendRequests(frame), c);
        } catch (UserNotFoundError e) {
            e.printStackTrace();
        }
        
        confirmButton.addActionListener(e -> {
            if (e.getSource() == confirmButton) {
                frame.dispose();
            }
        });
        confirmInterestsButton.addActionListener(e -> {
            if (e.getSource() == confirmInterestsButton) {
                frame.dispose();
            }
        });
        editPictureButton.addActionListener(e -> {
            if (e.getSource() == editPictureButton) {
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
     * Allows users to view all users and see their profile
     * Also allows users to send a friend request to any user
     *
     * @param menuBar the menu bar to add the profiles to
     */
    public void viewAllProfiles(JMenuBar menuBar) {
        //Getting all the profiles
        client.receiveProfiles();
        allUsers = new JMenuItem[client.getBetterBookProfiles().size()];
        //Adding all the profiles to the list
        for (int i = 0; i < client.getBetterBookProfiles().size(); i++) {
            allUsers[i] = new JMenuItem(client.getBetterBookProfiles().get(i).getUsername());
            if (allUsersMenu.getMenuComponentCount() > 1) {
                for (int k = 0; k < client.getBetterBookProfiles().size(); k++) {
                    if (!allUsersMenu.getItem(k).equals(allUsers[k])) {
                        allUsersMenu.add(allUsers[i]);
                    }
                }
            } else {
                allUsersMenu.add(allUsers[i]);
            }
            int finalI = i;
            allUsers[i].addActionListener(e -> {
                if (e.getSource() == allUsers[finalI]) {
                    //Creating some options when you click on the name of a user
                    Object[] options1 = {"Send friend request",
                            "View profile"};
                    
                    int selection = JOptionPane.showOptionDialog(null,
                            "What would you like to do?",
                            client.getBetterBookProfiles().get(finalI).getUsername(),
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            options1,
                            null);
                    
                    switch (selection) {
                        case 0:
                            //Sending a friend request
                            try {
                                profile = client.signIn(profile.getUsername(), profile.getPassword());
                                client.receiveProfiles();
                                Profile temp = new Profile(client.getBetterBookProfiles().get(finalI));
                                boolean notFriends = true;
                                int sel = 0;
                                for (int j = 0; j < profile.getFriendsList().size(); j++) {
                                    if (profile.getFriendsList().get(j).getUsername().equals(temp.getUsername())) {
                                        notFriends = false;
                                    }
                                }
                                if (notFriends) {
                                    sel = JOptionPane.showConfirmDialog(null,
                                            "Would you like to send "
                                                    + client.getBetterBookProfiles().get(finalI).getUsername() +
                                                    "\na friend request?", "User found!",
                                            JOptionPane.YES_NO_OPTION);
                                    
                                    switch (sel) {
                                        case 0:
                                            try {
                                                profile = client.signIn(profile.getUsername(), profile.getPassword());
                                                client.sendFriendRequest(profile, temp);
                                                client.signIn(profile.getUsername(),
                                                        profile.getPassword()).addSentFriendRequest(temp);
                                                client.updateProfile(profile, profile);
                                            } catch (UserNotFoundError userNotFoundError) {
                                                userNotFoundError.printStackTrace();
                                            }
                                            JOptionPane.showConfirmDialog(null,
                                                    "Friend request sent!",
                                                    "Request Sent",
                                                    JOptionPane.OK_OPTION);
                                            client.sendProfiles();
                                            viewPendingFriendRequest(menuBar);
                                            break;
                                    }
                                } else {
                                    JOptionPane.showConfirmDialog(null,
                                            "You are already friends with "
                                                    + client.getBetterBookProfiles().get(finalI).getUsername(),
                                            "",
                                            JOptionPane.OK_OPTION);
                                }
                                
                            } catch (NullPointerException | UserNotFoundError e1) {
                                JOptionPane.showMessageDialog(null, "User not found!",
                                        "User not found!",
                                        JOptionPane.OK_OPTION);
                            }
                            break;
                        case 1:
                            //Viewing their profile
                            JFrame frame = new JFrame("BetterBook");
                            frame.setResizable(false);
                            Container content = frame.getContentPane();
                            content.setLayout(new GridBagLayout());
                            GridBagConstraints c = new GridBagConstraints(5, 5, 1,
                                    1, 1.0, 1.0, GridBagConstraints.NORTHWEST,
                                    GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0)
                                    , 0, 0);
                            gui = new GUI();
                            frame.add(gui, c);
                            
                            //Adding the content to the frame
                            try {
                                c.gridx = 0;
                                c.gridy = 0;
                                frame.add(displayUserInformation(client.getBetterBookProfiles().get(finalI)), c);
                                c.gridx = 1;
                                frame.add(displayUserFriendList(client.getBetterBookProfiles().get(finalI)), c);
                                c.gridx = 0;
                                c.gridy = -1;
                                frame.add(displayUserInterestList(client.getBetterBookProfiles().get(finalI)), c);
                            } catch (UserNotFoundError e1) {
                                e1.printStackTrace();
                            }
                            
                            /// Making the frame visible
                            frame.setSize(800, 600);
                            frame.setLocationRelativeTo(null);
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            content.setBackground(Color.decode("#246EB9"));
                            frame.setVisible(true);
                            break;
                        
                    }
                }
            });
        }
        //Adding the users to the menubar
        menuBar.add(allUsersMenu);
        client.sendProfiles();
    }
    
    /**
     * Allows users to see all their pending friend requests
     *
     * @param menuBar the menu bar to add the pending requests to
     */
    public void viewPendingFriendRequest(JMenuBar menuBar) throws UserNotFoundError {
        //Getting all the pending requests
        client.receiveProfiles();
        profile = client.signIn(profile.getUsername(), profile.getPassword());
        ArrayList<Profile> requests = profile.getSentFriendRequests();
        allRequests = new JMenuItem[requests.size()];
        for (int i = 0; i < requests.size(); i++) {
            //Adding all the requests to the pending menu
            allRequests[i] = new JMenuItem(requests.get(i).getUsername());
            pendingFriendRequests.add(allRequests[i]);
            
            //Adding a listener for being clicked
            int finalI = i;
            allRequests[i].addActionListener(e -> {
                if (e.getSource() == allRequests[finalI]) {
                    //Creating some options when you click on the name of a user
                    Object[] options1 = {"Revoke friend request",
                            "Cancel"};
                    
                    int selection = JOptionPane.showOptionDialog(null,
                            "What would you like to do?",
                            requests.get(finalI).getUsername(),
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            options1,
                            null);
                    
                    switch (selection) {
                        case 0:
                            //Revoke the friend request
                            profile.removeSentFriendRequest(requests.get(finalI));
                            pendingFriendRequests.remove(allRequests[finalI]);
                            break;
                        case 1:
                            break;
                    }
                }
            });
        }
        menuBar.add(pendingFriendRequests);
        client.sendProfiles();
    }
    
    /**
     * This page creates the menu bar at the top of the profile page
     *
     * @param frame frame to add the menu bar to
     */
    public void createMenuBar(JFrame frame) throws UserNotFoundError {
        JMenuBar menuBar = new JMenuBar();
        // Account tab
        accountMenu.add(editAccount);
        editAccount.addActionListener(menuBarListener);
        accountMenu.add(editInterests);
        editInterests.addActionListener(menuBarListener);
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
        
        //Adding a place to view all users
        viewAllProfiles(menuBar);
        viewPendingFriendRequest(menuBar);
        
        menuBar.add(refresh);
        refresh.addActionListener(e -> {
            if (e.getSource() == refresh) {
                frame.dispose();
                profilePage();
            }
        });
        // Adding the menuBar to the profile Page
        frame.setJMenuBar(menuBar);
    }
    
    /**
     * This displays the users username, real name, contact information, and privacy setting
     *
     * @param currentProfile the profile that is currently being viewed
     */
    public JPanel displayUserInformation(Profile currentProfile) throws UserNotFoundError {
        client.receiveProfiles();
        //Creating info buffer for precise location
        JPanel infoBuffer = new JPanel();
        infoBuffer.setLayout(new BoxLayout(infoBuffer, BoxLayout.Y_AXIS));
        infoBuffer.setBorder(new EmptyBorder(25, 25, 0, 0));
        infoBuffer.setBackground(Color.decode("#246EB9"));
        
        // Creating the Info Box
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBorder(new EmptyBorder(15, 15, 15, 15));
        info.setMinimumSize(new Dimension(450, 275));
        info.setSize(new Dimension(450, 275));
        infoBuffer.add(info);
        
        // Creating the profile picture and JLabel
        currentProfile = client.signIn(currentProfile.getUsername(), currentProfile.getPassword());
        resizedImage = currentProfile.getProfilePicture().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        System.out.println("This is the profile picture: " + currentProfile.getProfilePicture());
        
        profilePicture = new JLabel();
        
        ImageIcon icon = new ImageIcon(resizedImage);
        profilePicture.setIcon(icon);
        
        username = new JLabel("Username: " + currentProfile.getUsername());
        username.setBorder(new EmptyBorder(0, 0, 15, 0));
        username.setFont(new Font("Verdana", Font.PLAIN, 18));
        
        name = new JLabel("Name: " + currentProfile.getName());
        name.setBorder(new EmptyBorder(0, 0, 15, 0));
        name.setFont(new Font("Verdana", Font.PLAIN, 18));
        
        contactInformation = new JLabel("Contact Info:" + currentProfile.getContactInformation());
        contactInformation.setBorder(new EmptyBorder(0, 0, 15, 0));
        contactInformation.setFont(new Font("Verdana", Font.PLAIN, 18));
        
        aboutMe = new JLabel(currentProfile.getAboutMe());
        aboutMe.setBorder(new EmptyBorder(0, 0, 15, 0));
        aboutMe.setFont(new Font("Verdana", Font.PLAIN, 18));
        
        privacySetting = new JLabel("Current Privacy Setting: " + currentProfile.getPrivacySetting());
        privacySetting.setBorder(new EmptyBorder(0, 0, 0, 0));
        privacySetting.setFont(new Font("Verdana", Font.PLAIN, 18));
        
        /// Adding information
        // The Glue puts space between the lines
        info.add(profilePicture);
        info.add(Box.createGlue());
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
        
        client.sendProfiles();
        
        // Showing the panel
        info.setVisible(true);
        info.setBackground(Color.decode("#F0CEA0"));
        return infoBuffer;
    }
    
    /**
     * This displays the users friend list in the order that it is stored
     *
     * @param currentProfile the profile that is currently being viewed
     */
    public JPanel displayUserFriendList(Profile currentProfile) throws UserNotFoundError {
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
        currentProfile = client.signIn(currentProfile.getUsername(), currentProfile.getPassword());
        ArrayList<Profile> friends = currentProfile.getFriendsList();
        
        //Default action if no friends exist
        if (friends.size() <= 0) {
            JLabel emptyFriendList = new JLabel("<html>To add friends, search a username in the top search bar!</html>");
            emptyFriendList.setBorder(new EmptyBorder(0, 0, 15, 0));
            emptyFriendList.setFont(new Font("Verdana", Font.PLAIN, 15));
            internalFriendPanel.add(emptyFriendList);
        } else {
            //Adding any friends that have accepted the friend request
            for (Profile f : currentProfile.getFriendsList()) {
                JLabel currentFriend = new JLabel(f.getUsername());
                currentFriend.setBorder(new EmptyBorder(0, 0, 15, 0));
                currentFriend.setFont(new Font("Verdana", Font.PLAIN, 15));
                internalFriendPanel.add(currentFriend);
            }
        }
        
        client.sendProfiles();
        
        //Adding the friend buffer and panel to the display at gridx 1 gridy 0
        internalFriendPanel.setVisible(true);
        internalFriendPanel.setBackground(Color.decode("#F0CEA0"));
        return friendPanelBuffer;
    }
    
    /**
     * This displays the users interest list, substituting for a button if no interests
     * are listed
     *
     * @param currentProfile the profile that is currently being viewed
     */
    public JPanel displayUserInterestList(Profile currentProfile) {
        client.receiveProfiles();
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
        if (currentProfile.getLikesAndInterests().size() == 0) {
            //Creating a one time deal button to add some interests
            JButton emptyInterestsList = new JButton("Add likes and interests");
            emptyInterestsList.setBorder(new EmptyBorder(15, 15, 15, 15));
            emptyInterestsList.setFont(new Font("Verdana", Font.PLAIN, 15));
            emptyInterestsList.setContentAreaFilled(true);
            
            //Adding that button to the panel
            interestsPanel.add(emptyInterestsList);
            
            //Setting borders to make the title and button look nice
            interestsPanel.setBorder(new EmptyBorder(15, 50, 15, 50));
            interestsTitle.setBorder(new EmptyBorder(15, 15, 15, 15));
            
            //Adding an event listener to remove the button if it is clicked
            emptyInterestsList.addActionListener(e -> {
                if (e.getSource() == emptyInterestsList) {
                    addInterestsPage();
                }
            });
        } else {
            //Adding any friends that have accepted the friend request
            for (int i = 0; i < currentProfile.getLikesAndInterests().size(); i++) {
                if (!currentProfile.getLikesAndInterests().get(i).equals("Interest " + i + ":")) {
                    JLabel currentInterest = new JLabel(currentProfile.getLikesAndInterests().get(i));
                    currentInterest.setBorder(new EmptyBorder(0, 0, 2, 0));
                    currentInterest.setFont(new Font("Verdana", Font.PLAIN, 15));
                    interestsPanel.add(currentInterest);
                }
                //Setting borders to make the title and interests look nice
                interestsPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
                interestsTitle.setBorder(new EmptyBorder(15, 50, 15, 50));
            }
        }
        
        client.sendProfiles();
        
        //Adding the friend buffer and panel to the display at gridx 1 gridy 0
        interestsPanel.setVisible(true);
        interestsPanel.setBackground(Color.decode("#F0CEA0"));
        return interestsBuffer;
    }
    
    /**
     * This displays the users pending friend requests, with a default message if
     * no requests are found
     *
     * @param frame the frame in which the friend requests is being viewed in
     */
    public JPanel displayPendingFriendRequests(JFrame frame) throws UserNotFoundError {
        client.receiveProfiles();
        //Creating friend buffer for precise position
        JPanel friendPanelBuffer = new JPanel();
        friendPanelBuffer.setLayout(new BoxLayout(friendPanelBuffer, BoxLayout.Y_AXIS));
        friendPanelBuffer.setBorder(new EmptyBorder(0, 25, 0, 150));
        friendPanelBuffer.setBackground(Color.decode("#246EB9"));
        friendPanelBuffer.setMinimumSize(new Dimension(500, 200));
        friendPanelBuffer.setMaximumSize(new Dimension(500, 200));
        
        //Creating the friend Panel
        JPanel requestPanel = new JPanel();
        requestPanel.setLayout(new BoxLayout(requestPanel, BoxLayout.Y_AXIS));
        requestPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        requestPanel.setMinimumSize(new Dimension(300, 200));
        requestPanel.setMaximumSize(new Dimension(300, 200));
        
        //Adding the panel to the buffer
        friendPanelBuffer.add(requestPanel);
        
        //Getting a local list of all the friend requests
        ArrayList<Profile> friends = client.signIn(profile.getUsername(), profile.getPassword()).getFriendRequestList();
        
        //Default action if no requests exist
        if (friends.size() <= 0) {
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
                client.receiveProfiles();
                
                Profile currentProfile = new Profile(friends.get(i));
                //Setting the button to be yes or no
                confirmFriendRequest[i] = new JButton("✓");
                confirmFriendRequest[i].setMinimumSize(new Dimension(25, 25));
                confirmFriendRequest[i].setMaximumSize(new Dimension(25, 25));
                confirmFriendRequest[i].setBorder(new EmptyBorder(2, 2, 2, 2));
                denyFriendRequest[i] = new JButton("X");
                denyFriendRequest[i].setMinimumSize(new Dimension(25, 25));
                denyFriendRequest[i].setMaximumSize(new Dimension(25, 25));
                denyFriendRequest[i].setBorder(new EmptyBorder(2, 2, 2, 2));
                
                //Creating a panel for the current request
                JPanel currentRequest = new JPanel();
                currentRequest.setLayout(new BoxLayout(currentRequest, BoxLayout.X_AXIS));
                currentRequest.setBorder(new EmptyBorder(5, 5, 5, 5));
                currentRequest.setMinimumSize(new Dimension(230, 50));
                currentRequest.setMaximumSize(new Dimension(230, 50));
                
                //Loading the username
                JLabel currentFriend = new JLabel(friends.get(i).getUsername());
                currentFriend.setBorder(new EmptyBorder(0, 0, 0, 0));
                currentFriend.setFont(new Font("Verdana", Font.PLAIN, 12));
                currentRequest.add(currentFriend);
                currentRequest.add(Box.createHorizontalBox());
                //Adding the buttons
                currentRequest.add(confirmFriendRequest[i]);
                currentRequest.add(denyFriendRequest[i]);
                
                //Making some event listeners for the buttons
                int current = i;
                confirmFriendRequest[i].addActionListener(e -> {
                    if (e.getSource() == confirmFriendRequest[current]) {
                        try {
                            profile = client.signIn(profile.getUsername(), profile.getPassword());
                            profile.addFriend(currentProfile);
                            friends.get(current).addFriend(profile);
                            profile.removeFriendRequest(currentProfile);
                            friends.remove(current);
                            //See if hes still there and update client
                            frame.dispose();
                            profilePage();
                        } catch (UserNotFoundError userNotFoundError) {
                            userNotFoundError.printStackTrace();
                        }
                        
                    }
                });
                
                denyFriendRequest[i].addActionListener(e -> {
                    if (e.getSource() == denyFriendRequest[current]) {
                        profile.removeFriendRequest(currentProfile);
                        client.sendProfiles();
                        friends.remove(current);
                        frame.dispose();
                        profilePage();
                    }
                });
                Profile temp = new Profile(friends.get(current));
                temp = client.signIn(friends.get(current).getUsername(), friends.get(current).getPassword());
                temp.addFriend(profile);
                requestPanel.add(currentRequest);
                client.updateProfile(friends.get(current), temp);
            }
            requestPanel.setMinimumSize(new Dimension(300, 200));
            requestPanel.setMaximumSize(new Dimension(300, 200));
        }
        client.sendProfiles();
        
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
