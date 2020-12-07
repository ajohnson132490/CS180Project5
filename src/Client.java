import java.io.*;
import java.net.*;
import java.util.*;

/**
 * BetterBook Client
 * <p>
 * This is the client that users use to connect to the server for information
 * Includes methods to keep the Profiles stored by the server and client in sync.
 * <p>
 * Used parts of HW 11
 *
 * @author Patrick Florendo | CS18000 Project 5 | Group 007-2
 * @version 30 November 2020
 */
public class Client {
    private ArrayList<Profile> betterBookProfiles;  // ArrayList containing all profiles
    private Socket sock;    // Socket used to connect to server
    private ObjectOutputStream writer;  // sends messages to server
    private ObjectInputStream objectReader; // receives messages from server

    /**
     * Initializes new Client connected at hostName:portNumber
     *
     * @param hostName   host to connect to
     * @param portNumber port to connect to
     */
    public Client(String hostName, int portNumber) throws UnknownHostException, IOException {
        sock = new Socket(hostName, portNumber);
        betterBookProfiles = new ArrayList<Profile>();
        writer = new ObjectOutputStream(sock.getOutputStream());
        objectReader = new ObjectInputStream(sock.getInputStream());
        try {
            Thread.sleep(500);
            receiveProfiles();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error connecting to server");
        }
    }

    /**
     * deprecated, use createProfile instead
     */
    public Profile createUser(String username, String password, String name, String contactInformation) {
        return new Profile(username, password, name, contactInformation);
    }

    /**
     * Disconnects from server
     */
    public void disconnect() {
        // Tell server we're disconnecting
        try {
            writer.writeObject("Goodbye");
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error disconnecting from server");
        } finally {
            try {
                writer.close();
                objectReader.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error disconnecting from server");
            }
        }
    }

    /**
     * Receives profiles sent from the server
     */
    public void receiveProfiles() {
        try {
            // Tells the server we want to receive profiles
            writer.writeObject("receiveProfiles");
            writer.flush();

            // Reset ArrayList
            betterBookProfiles = new ArrayList<Profile>();
            Object receive = objectReader.readObject();
            // Loop until server says "Goodbye" (Profile -> String)
            while (!(receive instanceof String)) {
                betterBookProfiles.add((Profile) receive);
                receive = objectReader.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error receiving profiles from server");
        }
    }

    /**
     * Sends profiles to the server
     */
    public void sendProfiles() {
        try {
            // Tell the server we want to send profiles
            writer.writeObject("sendProfiles");
            writer.flush();

            // Write all of the Profiles we have stored
            for (Profile p : betterBookProfiles) {
                writer.writeObject(p);
            }
            // Tell the server that it is at the end of the list
            writer.writeObject("Goodbye");
            writer.flush();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error sending profiles to server");
        }
    }

    /**
     * Creates a Profile with the given parameters and adds it to the ArrayList
     *
     * @param userName    The Profile's userName
     * @param pass        The Profile's password
     * @param name        The Profile's name
     * @param contactInfo The Profile's contact info
     * @throws UserNotFoundError Thrown if userName is already taken by a different user
     */
    public void createProfile(String userName, String pass, String name, String contactInfo) throws UserNotFoundError {
        receiveProfiles();

        // check if username is available
        for (Profile p : betterBookProfiles) {
            if (p.getUsername().equals(userName)) {
                throw new UserNotFoundError("Username taken!");
            }
        }

        // create a new Profile w given parameters, add to list, update server's list
        Profile p = new Profile(userName, pass, name, contactInfo);
        betterBookProfiles.add(p);
        sendProfiles();
    }

    /**
     * Updates the Profile in betterBookProfiles with the desired information
     *
     * @param oldProfile The Profile to be updated
     * @param newProfile The Profile with the updated information
     */
    public void updateProfile(Profile oldProfile, Profile newProfile) throws UserNotFoundError {
        int ind = locateProfile(oldProfile);
        if (ind != -1) {
            betterBookProfiles.set(ind, newProfile);
        } else {
            throw new UserNotFoundError("User not found!");
        }
        sendProfiles();
    }

    /**
     * Deletes the given profile
     *
     * @param p The Profile to be deleted
     */
    public void deleteProfile(Profile p) throws UserNotFoundError {
        int ind = locateProfile(p);
        if (ind != -1) {
            betterBookProfiles.remove(ind);
        } else {
            throw new UserNotFoundError("User not found!");
        }
        sendProfiles();
    }

    /**
     * Sends a friend request from sending to receiving
     * Basically, adds sending to receiving's friend request list
     *
     * @param sending   The Profile sending the friend request
     * @param receiving The Profile receiving the friend request
     */
    public void sendFriendRequest(Profile sending, Profile receiving) {
        receiveProfiles();
        if (!receiving.getFriendRequestList().contains(sending)) {
            receiving.getFriendRequestList().add(sending);
        }
        sendProfiles();
    }

    /**
     * Locates the given profile
     *
     * @param p The Profile to be located
     * @return The index of p, -1 if not found
     */
    public int locateProfile(Profile p) {
        receiveProfiles();
        for (int i = 0; i < betterBookProfiles.size(); i++) {
            if (p.getUsername().equals(betterBookProfiles.get(i).getUsername())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Locates the given profile
     *
     * @param username The Profile to be located
     * @return The index of p, -1 if not found
     */
    public Profile locateProfile(String username) {
        receiveProfiles();
        for (int i = 0; i < betterBookProfiles.size(); i++) {
            if (username.equals(betterBookProfiles.get(i).getUsername())) {
                return betterBookProfiles.get(i);
            }
        }
        return null;
    }

    /**
     * Returns the profile with matching username and password
     *
     * @param username The user being logged into
     * @param password The password to the user being logged into
     * @return The profile with matching username and password
     * @throws UserNotFoundError Thrown if no user has a matching username or passwords don't match
     */
    public Profile signIn(String username, String password) throws UserNotFoundError {
        receiveProfiles();
        for (Profile p : betterBookProfiles) {
            if (p.getUsername().equals(username)) {
                if (p.getPassword().equals(password)) {
                    return p;
                } else {
                    // Passwords didn't match
                    throw new UserNotFoundError("Passwords don't match!");
                }
            }
        }
        throw new UserNotFoundError("User not found!");
    }

    /**
     * Returns betterBookProfiles
     *
     * @return betterBookProfiles, the ArrayList containing the client's profiles
     */
    public ArrayList<Profile> getBetterBookProfiles() {
        return betterBookProfiles;
    }

    /**
     * Sets betterBookProfiles to the specified parameter
     *
     * @param betterBookProfiles the new value for betterBookProfiles
     */
    public void setBetterBookProfiles(ArrayList<Profile> betterBookProfiles) {
        this.betterBookProfiles = betterBookProfiles;
    }

    /**
     * Prints the username of every Profile in betterBookProfiles for debugging purposes
     */
    public void printBetterBookProfiles() {
        for (Profile p : getBetterBookProfiles()) {
            System.out.println(p.getUsername());
        }
    }
}
