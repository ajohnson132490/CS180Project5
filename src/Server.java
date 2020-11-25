import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * BetterBook Server
 *
 * This is the server designed to send, store, and
 * process data from clients. Multithreaded.
 *
 * https://www.tutorialspoint.com/javaexamples/net_multisoc.htm
 * https://stackoverflow.com/questions/13582395/sharing-a-variable-between-multiple-different-threads
 * https://mkyong.com/java/how-to-read-and-write-java-object-to-a-file/
 * Used parts of HW 11
 *
 * @author Jacob Zietek | CS18000 Project 5 | Group 007-2
 * @version 25 November 2020
 */

public class Server implements Runnable {

    // NOTE: "betterBookProfiles" will be synchronized with all threads
    volatile static ArrayList<Profile> betterBookProfiles; // Arraylist of type Profile containing all user profiles
    private Socket csock; // Socket that connects to client

    public Server(Socket csock){
        this.csock = csock; // Initializes new thread with client socket
    }

    /**
     * This function handles all of the interaction between server and client.
     * It is called once a client connects to the server.
     */
    public void run() { // TODO
        while (true) {
            System.out.println("Client connected!");
            try {
                // Starts reading and writing streams
                BufferedReader reader = new BufferedReader(new InputStreamReader(csock.getInputStream()));
                ObjectOutputStream objectWriter = new ObjectOutputStream(csock.getOutputStream());

                // Reads message from client
                String message = reader.readLine();

                // If the message is null the client disconnected, close all streams.
                if (message == null) {
                    reader.close();
                    objectWriter.close();
                    csock.close();
                    return;
                }

                String messageToSend = "";

                /*
                    TODO Process data here & prep "messageToSend"
                 */

                // Sends data to client as needed.
                objectWriter.writeObject(messageToSend);
                objectWriter.flush();

            } catch (Exception e){
                // Exception handling. TODO, throw exception errors
                e.printStackTrace();
                System.out.println("Error after client connected.");
                return;
            }

        }
    }

    public static void main(String[] args) throws Exception {
        ServerSocket ssock = new ServerSocket(54234);
        betterBookProfiles = new ArrayList<Profile>();
        loadProfiles("betterBookProfiles.txt"); // Initializes synchronized profile list.
        System.out.println("Listening");

        while (true) { // Forever loops and accepts new clients. Assigns a thread to each client.
            Socket sock = ssock.accept();
            System.out.println("Connected");
            new Thread(new Server(sock)).start();
        }
    }

    /**
     * Loads serialized BetterBook profiles from a .txt file.
     *
     *
     * @param fileName File name to load profiles from
     */
    public static void loadProfiles(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(new File(fileName));
        ObjectInputStream oi = new ObjectInputStream(fi);

        // Loads every profile stored in memory to "betterBookProfiles"
        Profile p = (Profile) oi.readObject();
        while(p != null){
            betterBookProfiles.add(p);
            p = (Profile) oi.readObject();
        }
    }

    /**
     * Saves serialized BetterBook profiles to a .txt file.
     * This should be done frequently.
     *
     * @param fileName File name to save profiles to
     */
    public void save(String fileName) throws IOException {
        FileOutputStream f = new FileOutputStream(new File(fileName));
        ObjectOutputStream o = new ObjectOutputStream(f);

        for(Profile p: betterBookProfiles){
            o.writeObject(p);
        }
    }

}
