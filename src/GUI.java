import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.UnknownHostException;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

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
	
	// Buttons
	JButton deleteFriend = new JButton("-");
	JButton signInButton = new JButton("Sign In");
	JButton newAccountButton = new JButton("Create a New Account");
	JButton registerButton = new JButton("Register");
	JButton searchButton = new JButton("Search");
	JButton confirmButton = new JButton("Confirm");
	
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
	JTextField contactInformationField = new JTextField("Email or Phone #");
	JTextField searchField = new JTextField("Enter a Username:");
	
	// Labels
	JLabel username;
	JLabel name;
	JLabel contactInformation;
	JLabel aboutMe;
	JLabel privacySetting;
	
	/* CONSTRUCTOR  */
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
	
	/* ALL EVENT LISTENERS */
	ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == signInButton) {
				try {
					profile = client.signIn(usernameField.getText(), passwordField.getText());
					profilePage();
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
					try {
						client.createProfile(usernameField.getText(), passwordField.getText(),
								nameField.getText(), contactInformationField.getText());
						profile = new Profile(usernameField.getText(), passwordField.getText(),
								nameField.getText(), contactInformationField.getText());
					} catch (UserNotFoundError userNotFoundError) {
						userNotFoundError.printStackTrace();
					}
					signInPage();
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
	ActionListener menuBarListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == createAccount) {
				newAccountPage();
			}
			if (e.getSource() == editAccount) {
				editAccountPage();
			}
			if (e.getSource() == confirmButton) {
				Profile newProfile = new Profile(usernameField.getText(), passwordField.getText(),
						nameField.getText(), contactInformationField.getText());
				client.updateProfile(profile, newProfile);
			}
		}
	};
	
	
	/* ALL PAGES */
	// The JFrame where you sign in
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
		signInButton.addActionListener(actionListener);
		frame.add(signInButton, c);
		signInButton.addActionListener(e -> {
			frame.dispose();
		});
		
		c.gridx = 0;
		c.gridy = 5;
		newAccountButton.addActionListener(actionListener);
		frame.add(newAccountButton, c);
		newAccountButton.addActionListener(e -> {
			frame.dispose();
		});
		
		// Making the frame visible
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
	
	// The JFrame that appears when registerButton is clicked in order for the user
	// to create a new account
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
		
		c.gridx = 0;
		c.gridy = 10;
		registerButton.addActionListener(actionListener);
		frame.add(registerButton, c);
		registerButton.addActionListener(e -> {
			frame.dispose();
		});
		
		
		// Making the frame visible
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	//Allows users to edit their account
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
		
		c.gridx = 0;
		c.gridy = 10;
		confirmButton.addActionListener(menuBarListener);
		frame.add(confirmButton, c);
		confirmButton.addActionListener(e -> {
			usernameField.setEditable(true);
			frame.dispose();
		});
		
		
		// Making the frame visible
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	//This page is the main screen with all the users information
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
		
		createMenuBar(frame);
		displayAllInformation(frame, c);
		
		/// Making the frame visible
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/// Profile internal panels
	// Menu Bar at the top
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
					}
				} else if (selection == JOptionPane.NO_OPTION) {
					frame.dispose();
				}
			}
			
		});
		accountMenu.add(requestList);
		requestList.addActionListener(menuBarListener);
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
		
		// Adding the menuBar to the profile Page
		frame.setJMenuBar(menuBar);
	}
	
	// Profile Page objects
	public void displayAllInformation(JFrame frame, GridBagConstraints c) {
		displayUserInformation(frame, c);
		displayUserFriendList(frame, c);
		displayUserInterestList(frame, c);
	}
	
	//Shows all personal information in top left corner
	public void displayUserInformation(JFrame frame, GridBagConstraints c) {
		//Creating info buffer for precise location
		JPanel infoBuffer = new JPanel();
		infoBuffer.setLayout(new BoxLayout(infoBuffer, BoxLayout.Y_AXIS));
		infoBuffer.setBorder(new EmptyBorder(25, 25, 0, 0));
		
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
		c.gridx = 0;
		frame.add(infoBuffer, c);
		info.setVisible(true);
		// Delete this line
		info.setBackground(Color.red);
	}
	
	//Shows users friend list in top right corner
	public void displayUserFriendList(JFrame frame, GridBagConstraints c) {
		//Creating friend buffer for precise position
		JPanel friendPanelBuffer = new JPanel();
		friendPanelBuffer.setLayout(new BoxLayout(friendPanelBuffer, BoxLayout.Y_AXIS));
		friendPanelBuffer.setBorder(new EmptyBorder(25, 25, 0, 25));
		
		//Creating the friend Panel
		JPanel internalFriendPanel = new JPanel();
		internalFriendPanel.setLayout(new BoxLayout(internalFriendPanel, BoxLayout.Y_AXIS));
		internalFriendPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		internalFriendPanel.setMinimumSize(new Dimension(250, 100));
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
		c.gridx = 1;
		frame.add(friendPanelBuffer, c);
		internalFriendPanel.setVisible(true);
		internalFriendPanel.setBackground(Color.cyan);
	}
	
	//Shows user interest list in bottom left corner
	public void displayUserInterestList(JFrame frame, GridBagConstraints c) {
		//Creating a buffer for the interests panel
		JPanel interestsBuffer = new JPanel();
		interestsBuffer.setLayout(new BoxLayout(interestsBuffer, BoxLayout.Y_AXIS));
		interestsBuffer.setBorder(new EmptyBorder(0, 25, 0, 25));
		
		//Creating the interest Panel
		JPanel interestsPanel = new JPanel();
		JLabel interestsTitle = new JLabel("<html>Likes and Interests:</html>");
		interestsTitle.setFont(new Font("Verdana", Font.PLAIN, 16));
		interestsTitle.setBorder(new EmptyBorder(15,15,15,15));
		interestsPanel.add(interestsTitle);
		interestsPanel.setLayout(new BoxLayout(interestsPanel, BoxLayout.Y_AXIS));
		interestsPanel.setBorder(new EmptyBorder(15, 50, 15, 50));
		interestsPanel.setMinimumSize(new Dimension(300, 200));
		interestsPanel.setMaximumSize(new Dimension(300, 200));
		
		interestsBuffer.add(interestsPanel);
		ArrayList <String> interests = profile.getLikesAndInterests();
		
		//Default action if no friends exist
		if (interests.size() >= 0) {
			JButton emptyInterestsList = new JButton("Add likes and interests");
			emptyInterestsList.setBorder(new EmptyBorder(15, 15, 15, 15));
			emptyInterestsList.setFont(new Font("Verdana", Font.PLAIN, 15));
			interestsPanel.add(emptyInterestsList);
			interestsPanel.setBorder(new EmptyBorder(15, 50, 15, 50));
		} else {
			//Adding any friends that have accepted the friend request
			for (String i: profile.getLikesAndInterests()) {
				JLabel currentInterest = new JLabel(i);
				currentInterest.setBorder(new EmptyBorder(0, 0, 15, 0));
				currentInterest.setFont(new Font("Verdana", Font.PLAIN, 15));
				interestsPanel.add(currentInterest);
			}
		}
		//Adding the friend buffer and panel to the display at gridx 1 gridy 0
		c.gridx = 0;
		c.gridy = -1;
		frame.add(interestsBuffer, c);
		interestsPanel.setVisible(true);
		interestsPanel.setBackground(Color.orange);
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
