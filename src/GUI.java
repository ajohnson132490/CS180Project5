import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * BetterBook GUI
 * 
 * This is the bread and butter of the system, the GUI that allows users to see
 * and interact with the social networking site.
 * 
 * https://bit.ly/2KF8T36
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
 *
 * @author Austin Johnson| CS18000 Project 5 | Group 007-2
 * @version 25 November 2020
 */

public class GUI extends JComponent implements Runnable {
	/* ALL VARIABLES */
	private static final long serialVersionUID = -239376208864108143L; // Serial ID
	// private Client client; //The shorthand name for the client
	Profile profile;
	private String hostname = ""; // The host name for the client to connect to
	private int portNumber = 69420; // The port for the client to connect to
	GUI gui;

	// Buttons
	JButton addFriend = new JButton("+");
	JButton signInButton = new JButton("Sign In");

	// Menu Bar
	JMenu accountMenu = new JMenu("Account");
	JMenuItem createAccount = new JMenuItem("Create a new account");
	JMenuItem editAccount = new JMenuItem("Edit your account");
	JMenuItem deleteAccount = new JMenuItem("Delete your account");

	// Text fields
	JTextField usernameField = new JTextField("Username");
	JTextField passwordField = new JTextField("Password");

	// Labels
	JLabel username;
	JLabel name;
	JLabel contactInformation;
	JLabel aboutMe;
	JLabel privacySetting;

	/* MISC METHODS */
	public GUI() {
		// Creating a new client and passing it preset info to connect
		// this.client = new Client(hostname, portNumber);
	}

	// Sending data to the client to send to the server
	public void sendToServer(Object o) {
		// c.sendToServer(o);
	}

	/* ALL EVENT LISTENERS */

	/* ALL PAGES */
	// The JFrame where you sign in
	public void signInPage() {
		JFrame frame = new JFrame("Sign In");
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		usernameField.setPreferredSize(new Dimension(200, 50));
		c.gridx = 0;
		c.gridy = 0;
		frame.add(usernameField, c);

		passwordField.setPreferredSize(new Dimension(200, 50));
		c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 2;
		frame.add(passwordField, c);

		c.gridx = 0;
		c.gridy = 4;
		frame.add(signInButton, c);

		/// Making the frame visible
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void profilePage() {
		// Creating the frame
		JFrame frame = new JFrame("BetterBook");
		Container content = frame.getContentPane();
		content.setLayout(new BorderLayout());
		gui = new GUI();
		content.add(gui, BorderLayout.CENTER);

		createMenuBar(frame);
		displayPersonalInfo(frame);

		/// Making the frame visible
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	// Profile internal panels
	public void createMenuBar(JFrame frame) {
		JMenuBar menuBar = new JMenuBar();
		// Account tab
		accountMenu.add(createAccount);
		accountMenu.add(editAccount);
		accountMenu.add(deleteAccount);
		menuBar.add(accountMenu);

		// Add friend button to the left side
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(addFriend);
		frame.setJMenuBar(menuBar);
	}

	public void displayPersonalInfo(JFrame frame) {
		JPanel info = new JPanel(new BorderLayout());
		// Temp profile
		profile = new Profile("ajohnson", "Lets", "Austin", "6841 tadpole ct");
		username = new JLabel(profile.getUsername());

		//Adding information
		info.add(username);
		
		
		// Showing the panel and making it borderless
		info.setBackground(Color.RED);
		frame.add(info, BorderLayout.WEST);
		info.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
		info.setVisible(true);
		info.setBorder(getBorder());
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