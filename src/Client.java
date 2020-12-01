import java.io.*;
import java.net.*;
import java.util.*;

/**
 * BetterBook Client
 *
 * This is the client that users use to connect to the server for information
 *
 * Used parts of HW 11
 *
 * @author Patrick Florendo | CS18000 Project 5 | Group 007-2
 * @version 26 November 2020
 */
public class Client {
    ArrayList<Profile> betterBookProfiles;
    private Socket sock;
    BufferedReader reader;
    PrintWriter writer;

    //Initializes new Client and Socket connected at hostName:portNumber
    public Client(String hostName, int portNumber) throws UnknownHostException, IOException {
        sock = new Socket(hostName, portNumber);
        betterBookProfiles = new ArrayList<Profile>();
        reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        writer = new PrintWriter(sock.getOutputStream());
    }

    //TODO: implement functions for messages sent/received to/from server
    //Not actually sure if this needs a run method tbh
    
    //All client stuff will run in the main method, no run method is needed - Austin
    
    public Profile signIn(String username, String password) throws UserNotFoundError {
    	//TODO Check users sign in information against the database, 
    	//Then return a profile that matches the username and password
    	//Throw user not found error if you can't find a profile to match
    	//I'll handle the exception - Austin
    	return new Profile("AJohnson132490", "Lets", "Austin", "6841 Tadpole Ct");
    }
    
    public static void main(String[] args) {
        while (true) {
            //"disconnect" (disconnects from server)

            //"receiveProfiles" (requests updated ArrayList<Profile> from server)

            //"sendProfiles" (sends updated ArrayList<Profile> to server)

            //"createProfile" (creates new profile)

            //"updateProfile" (fulfills "edit profile" requirement)

            //"deleteProfile" (deletes profile)
        }
    }
}
