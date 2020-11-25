import java.awt.*;
import javax.swing.*;

/**
 * BetterBook GUI
 * 
 * This is the bread and butter of the system, the GUI
 * that allows users to see and interact with the social
 * networking site.
 * 
 *  https://bit.ly/2KF8T36
 *
 * @author Austin Johnson| CS18000 Project 5 | Group 007-2
 * @version 25 November 2020
 */

public class GUI extends JComponent implements Runnable{
	//Variables of the GUI Class
	private static final long serialVersionUID = -239376208864108143L; //Serial ID
	//private Client c;		//The shorthand name for the client
	private String hostname; //The host name for the client to connect to
	private int portNumber; //The port for the client to connect to
	GUI gui;
	
	//Buttons
	
	//Menu Bar
	JMenu accountMenu = new JMenu("Account");
	JMenuItem createAccount = new JMenuItem("Create a new account");
	JMenuItem editAccount = new JMenuItem("Edit your account");
	JMenuItem deleteAccount = new JMenuItem("Delete your account");
	
	//Text fields
	
	public GUI() {
		//this.c = new Client(hostname, portNumber);
	}
	
	public void sendToServer(Object o) {
		//c.sendToServer(o);
	}

	public void run() {
		//Creating the frame 
		JFrame frame = new JFrame("Challenge Exercise");
		Container content = frame.getContentPane();
		content.setLayout(new BorderLayout());
		gui = new GUI();
		content.add(gui, BorderLayout.CENTER);
		
		//Creating the top menu bar
		JMenuBar menuBar = new JMenuBar();
		accountMenu.add(createAccount);
		accountMenu.add(editAccount);
		accountMenu.add(deleteAccount);
		menuBar.add(accountMenu);
		frame.setJMenuBar(menuBar);
		
		///Making the frame visible
		//Keep this at the bottom of run
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		//Running the GUI
		SwingUtilities.invokeLater(new GUI());
	}
	
	
}