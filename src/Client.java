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
    	return new Profile("AJohnson132490", "Lets", "Austin", "john2761@purdue.edu");
    }
    
    public Profile createUser(String username, String password, String name, String contactInformation) {
        //TODO given this information, create a new user, add it to the database, then return the profile object for
        // the gui to use. Don't forget to make sure that the username and password adhere to any conventions that
        // you want, like no spaces, or must have a capital, ect. -Austin
        return new Profile(username, password, name, contactInformation);
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
