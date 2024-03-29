import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * BetterBook Server
 * <p>
 * This is the server designed to send, store, and
 * process data from clients. Multithreaded.
 * <p>
 * https://www.tutorialspoint.com/javaexamples/net_multisoc.htm
 * https://stackoverflow.com/questions/13582395/sharing-a-variable-between-multiple-different-threads
 * https://mkyong.com/java/how-to-read-and-write-java-object-to-a-file/
 * Used parts of HW 11
 * Client Server Protocol
 * https://docs.google.com/document/d/1HkZXhTeCeuC6dR1nIDILRl5ete3qi1Vw8AO2i9qOdFU/edit
 *
 * @author Jacob Zietek | CS18000 Project 5 | Group 007-2
 * @version 25 November 2020
 */

public class Server implements Runnable {
    
    // NOTE: "betterBookProfiles" will be synchronized with all threads
    volatile static ArrayList<Profile> betterBookProfiles; // Arraylist of type Profile containing all user profiles
    private Socket csock; // Socket that connects to client
    
    public Server(Socket csock) {
        this.csock = csock; // Initializes new thread with client socket
    }
    
    /**
     * This function handles all of the interaction between server and client.
     * It is called once a client connects to the server.
     */
    public void run() {
        // Starts reading and writing streams
        ObjectOutputStream objectWriter;
        ObjectInputStream reader;
        try {
            objectWriter = new ObjectOutputStream(csock.getOutputStream());
            reader = new ObjectInputStream(csock.getInputStream());
        } catch (Exception e) {
            System.out.println("Error Initializing streams.");
            return;
        }
        
        while (true) {
            System.out.println("Client connected!");
            try {
                
                // Reads message from client
                // As per protocol, first message is a String
                String message = (String) reader.readObject();
                
                // If the message is null the client disconnected, close all streams.
                if (message == null) {
                    reader.close();
                    objectWriter.close();
                    csock.close();
                    return;
                }
                
                handleMessage(reader, objectWriter, message);
                
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error after client connected.");
                return;
            }
            
        }
    }
    
    /**
     * Handles client requests synchronized to ensure that
     * betterBookProfiles list is updated correctly. All clients
     * access the list once the other is done with it. This ensures
     * modifications in the list don't mess with other user client
     * interaction.
     *
     * @param message Message from client
     */
    public synchronized static void handleMessage(ObjectInputStream reader,
                                                  ObjectOutputStream objectWriter, String message) {
        System.out.println("Starting to process message...");
        try {
            if (message.equals("receiveProfiles")) {
                // Client is requesting profile list
                for (Profile p : betterBookProfiles) {
                    objectWriter.writeObject(p);
                }
                // Protocol states String "Goodbye" will end sending profiles
                objectWriter.writeObject("Goodbye");
                objectWriter.flush();
                System.out.println("Sending Profiles");
            } else {
                // Client is writing new profile list
                ArrayList<Profile> newList = new ArrayList<>();
                Object input = reader.readObject();
                // Protocol states String "Goodbye" will end sending profiles
                while (!(input instanceof String)) {
                    newList.add((Profile) input);
                    //System.out.println(((Profile) input).getName());
                    input = reader.readObject();
                }
                betterBookProfiles = newList;
                // Saves new list to memory.
                save("betterBookProfiles.txt");
                System.out.println("Receiving Profiles");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws Exception {
        ServerSocket ssock = new ServerSocket(4242);
        betterBookProfiles = new ArrayList<>();
        try {
            loadProfiles("betterBookProfiles.txt"); // Initializes synchronized profile list.
        } catch (Exception e) {
            // If the profile list isn't found create a new save
            save("betterBookProfiles.txt");
        }
        
        for (Profile p : betterBookProfiles) {
            System.out.println(p.getName());
        }
        
        System.out.println("Listening...");
        
        while (true) { // Forever loops and accepts new clients. Assigns a thread to each client.
            Socket sock = ssock.accept();
            new Thread(new Server(sock)).start();
        }
    }
    
    /**
     * Loads serialized BetterBook profiles from a .txt file.
     *
     * @param fileName File name to load profiles from
     */
    public static void loadProfiles(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(new File(fileName));
        ObjectInputStream oi;
        try {
            oi = new ObjectInputStream(fi);
        } catch (Exception e) {
            System.out.println("Empty profile file!");
            return;
        }
        
        // Loads every profile stored in memory to "betterBookProfiles"
        Profile p = (Profile) oi.readObject();
        try {
            while (p != null) {
                betterBookProfiles.add(p);
                p = (Profile) oi.readObject();
            }
        } catch (EOFException e) {
            System.out.println("Existing profiles added.");
            oi.close();
            return;
        }
    }
    
    /**
     * Saves serialized BetterBook profiles to a .txt file.
     * This should be done frequently.
     *
     * @param fileName File name to save profiles to
     */
    public static void save(String fileName) throws IOException {
        FileOutputStream f = new FileOutputStream(new File(fileName));
        ObjectOutputStream o = new ObjectOutputStream(f);
        
        for (Profile p : betterBookProfiles) {
            o.writeObject(p);
        }
        o.flush();
        o.close();
    }
}
