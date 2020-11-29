import java.net.Socket;
import java.util.ArrayList;

import static org.junit.Assert.*;
/**
 * A class to test Server and all of its
 * methods using JUnit4.
 */
public class ServerTest {

    @org.junit.Test
    public void run() {
    }

    @org.junit.Test
    public void handleMessage() {
    }

    @org.junit.Test
    public void main() {
    }

    @org.junit.Test
    public void loadProfiles() {
    }

    @org.junit.Test
    public void save() {
    }

    public static void main(String[] args) {
        Server s = new Server(new Socket());
        s.betterBookProfiles = new ArrayList<Profile>();
        try {
            s.loadProfiles("betterBookProfiles.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(s.betterBookProfiles.get(0).getName());
    }
}