import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.UnknownHostException;

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
	JTextField searchField = new JTextField("Enter a Username:");

	// Labels
	JLabel username;
	JLabel name;
	JLabel contactInformation;
	JLabel aboutMe;
	JLabel privacySetting;

	/* MISC METHODS */
	public GUI() {
		// Creating a new client and passing it preset info to connect
		try {
			// this.client = new Client(hostname, portNumber);
		} catch (Exception e) {
			;
			JOptionPane.showMessageDialog(null, "There was an issue connecting to the server!", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	// Sending data to the client to send to the server
	public void sendToServer(Object o) {
		// c.sendToServer(o);
	}

	/* ALL EVENT LISTENERS */
	ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == signInButton) {
				try {
					client.signIn(usernameField.getText(), passwordField.getText());
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
				// Basically the same implementation as signInButton
				// I'm wondering if there is a way we could simplify this and just have both
				// point to one button
			}
			if (e.getSource() == addFriend) { // Change button?
				// friendList();
			}
			if (e.getSource() == requestList) {
				// requestList();
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

		c.gridx = 0;
		c.gridy = 5;
		newAccountButton.addActionListener(actionListener);
		frame.add(newAccountButton, c);

		// Making the frame visible
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		c.gridx = 0;
		c.gridy = 2;
		frame.add(passwordField, c);

		c.gridx = 0;
		c.gridy = 4;
		registerButton.addActionListener(actionListener);
		frame.add(registerButton, c);

		// Making the frame visible
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void profilePage() {
		// Creating the frame
		JFrame frame = new JFrame("BetterBook");
		Container content = frame.getContentPane();
		content.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints(5, 5, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0);
		gui = new GUI();
		frame.add(gui, c);

		createMenuBar(frame);
		displayPersonalInfo(frame, c);

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
		accountMenu.add(editAccount);
		accountMenu.add(deleteAccount);
		accountMenu.add(requestList);
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

	// Personal info at the upper left corner
	public void displayPersonalInfo(JFrame frame, GridBagConstraints c) {
		// Creating the Info Box
		JPanel info = new JPanel();
		info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));

		username = new JLabel(profile.getUsername());
		username.setBorder(new EmptyBorder(0, 0, 15, 0));
		username.setFont(new Font("Verdana", Font.PLAIN, 18));

		name = new JLabel(profile.getName());
		name.setBorder(new EmptyBorder(0, 0, 15, 0));
		name.setFont(new Font("Verdana", Font.PLAIN, 18));

		contactInformation = new JLabel(profile.getContactInformation());
		contactInformation.setBorder(new EmptyBorder(0, 0, 15, 0));
		contactInformation.setFont(new Font("Verdana", Font.PLAIN, 18));

		aboutMe = new JLabel(profile.getAboutMe());
		aboutMe.setBorder(new EmptyBorder(0, 0, 15, 0));
		aboutMe.setFont(new Font("Verdana", Font.PLAIN, 18));

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
		info.add(aboutMe);
		info.add(Box.createGlue());
		info.add(privacySetting);

		// Showing the panel
		frame.add(info, c);
		info.setVisible(true);
		// Delete this line
		info.setBackground(Color.red);
		
		
		//Creating friend box
		JPanel friendPanel = new JPanel();
		friendPanel.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
		
		

	}

	public void run() {
		// Running the sign in page
		signInPage();

		try {
			if (/* !profile.getUsername().equals(null) */ true) {
				// Running the profile page
				profilePage();
			}
		} catch (NullPointerException e) {
			System.out.println("You are not signed in");
		}
	}

	public static void main(String[] args) {
		// Running the GUI
		SwingUtilities.invokeLater(new GUI());
	}
}
