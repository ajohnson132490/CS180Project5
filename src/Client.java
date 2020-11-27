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
public class Client implements Runnable {
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
    public void run() {
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
