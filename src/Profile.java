import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

/**
 * Profile
 * <p>
 * A class that creates Profiles for BetterBook
 *
 * @author Paul Kraessig | CS18000 Project 5 | Group 007-2
 * @version 22 November 2020
 */
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;            //Unique username
    private String password;            //User's password
    private String name;                //Profile owner's name
    private String privacySetting;      //Privacy setting - either "Public", "Private", or "Protected"
    private String contactInformation;  //User's contact information -- potentially change to fixed size array
    private String aboutMe;             //User's bio to be displayed on profile

    private static transient BufferedImage defaultProfilePicture;

    //Default profile picture if user has no profile picture
    static {
        try {
            defaultProfilePicture = ImageIO.read(new File("BetterBookDefaultProfilePicture.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private transient BufferedImage profilePicture;   //User's profile picture
    private byte[] profilePictureRawData;   //Data for profile picture that can be serialized

    private final ArrayList<Profile> sentFriendRequests;  //List of profiles that the user has sent friend requests to
    private ArrayList<String> likesAndInterests;    //User's likes and interests -- potentially have size cap
    private final ArrayList<Profile> friendsList;         //User's friends list
    private final ArrayList<Profile> friendRequestList;   //User's list of friend requests

    /**
     * Constructor for a Profile object. Takes parameters for username, password, name, and contact information.
     * Sets privacy setting to public, aboutMe to an empty String, profile picture to the default image,
     * and initializes ArrayLists for likes and interests, friends list, sent friend requests,
     * and friend requests list.
     *
     * @param username           username to be set
     * @param password           password to be set
     * @param name               name to be set
     * @param contactInformation contact info to be set
     */
    public Profile(String username, String password, String name, String contactInformation) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.contactInformation = contactInformation;
        this.privacySetting = "Public";
        this.aboutMe = "";
        this.profilePicture = defaultProfilePicture;

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(this.profilePicture, "png", baos);
            this.profilePictureRawData = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        this.sentFriendRequests = new ArrayList<Profile>();
        this.likesAndInterests = new ArrayList<String>();
        this.friendsList = new ArrayList<Profile>();
        this.friendRequestList = new ArrayList<Profile>();
    }

    /**
     * Copy constructor for a Profile object
     *
     * @param toCopy Profile object to be copied
     */
    public Profile(Profile toCopy) {
        this.username = toCopy.getUsername();
        this.password = toCopy.getPassword();
        this.name = toCopy.getName();
        this.contactInformation = toCopy.getContactInformation();
        this.privacySetting = toCopy.getPrivacySetting();
        this.aboutMe = toCopy.getAboutMe();
        this.sentFriendRequests = toCopy.getSentFriendRequests();
        this.likesAndInterests = toCopy.getLikesAndInterests();
        this.friendsList = toCopy.getFriendsList();
        this.friendRequestList = toCopy.getFriendRequestList();
        try {
            this.profilePicture = toCopy.getProfilePicture();
        } catch (NullPointerException e) {
            this.profilePicture = defaultProfilePicture;
            System.out.println("Setting profile pic to default");
        }
        
    }

    /**
     * Gets the profile's username
     *
     * @return profile's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the profile's username
     *
     * @param username username to be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the profile's password
     *
     * @return profile's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the profile's password
     *
     * @param password password to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the profile owner's name
     *
     * @return profile owner's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the profile owner's name
     *
     * @param name name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the profile's privacy setting
     *
     * @return profile's privacy setting
     */
    public String getPrivacySetting() {
        return privacySetting;
    }

    /**
     * Sets the profile's privacy setting
     *
     * @param privacySetting privacy setting to be set
     */
    public void setPrivacySetting(String privacySetting) {
        this.privacySetting = privacySetting;
    }

    /**
     * Gets the user's contact information
     *
     * @return user's contact information
     */
    public String getContactInformation() {
        return contactInformation;
    }

    /**
     * Sets the user's contact information
     *
     * @param contactInformation contact info to be set
     */
    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    /**
     * Gets the user's likes and interests
     *
     * @return user's likes and interests
     */
    public ArrayList<String> getLikesAndInterests() {
        return likesAndInterests;
    }

    /**
     * Sets the users likes and interests
     *
     * @param likesAndInterests likes and interests to be set
     */
    public void setLikesAndInterests(ArrayList<String> likesAndInterests) {
        this.likesAndInterests = likesAndInterests;
    }

    /**
     * Gets the user's friends list
     *
     * @return user's friends list
     */
    public ArrayList<Profile> getFriendsList() {
        return friendsList;
    }

    /**
     * Adds a friend to the user's friends list if the two profiles are not already friends
     *
     * @param profile profile to be added
     * @return true if the users are now friends, false if they were already friends
     */
    public boolean addFriend(Profile profile) {
        if (!this.friendsList.contains(profile)) {
            this.friendsList.add(profile);
            return true;
        }
        return false;
    }

    /**
     * Removes a profile from the user's friends list if the two profiles are friends
     *
     * @param profile profile to be removed
     * @return true if the profile was successfully removed, false if the two users were not already friends
     */
    public boolean removeFriend(Profile profile) {
        if (this.friendsList.contains(profile)) {
            this.friendsList.remove(profile);
            return true;
        }
        return false;
    }

    /**
     * Gets the user's friend request list
     *
     * @return user's friend request list
     */
    public ArrayList<Profile> getFriendRequestList() {
        return friendRequestList;
    }

    /**
     * Adds a profile to the user's friend request list if the given profile does not already have a pending
     * friend request
     *
     * @param profile profile to be added
     * @return true if the friend request was successfully added, false if there was already a pending friend request
     */
    public boolean addFriendRequest(Profile profile) {
        if (!this.friendRequestList.contains(profile)) {
            this.friendRequestList.add(profile);
            return true;
        }
        return false;
    }

    /**
     * Removes a profile from the user's friend request list if the given profile has a pending
     * friend request with the user
     *
     * @param profile profile to be removed
     * @return true if the profile was successfully removed, false if there was not a pending friend request
     */
    public boolean removeFriendRequest(Profile profile) {
        if (this.friendRequestList.contains(profile)) {
            this.friendRequestList.remove(profile);
            return true;
        }
        return false;
    }

    /**
     * Gets the user's bio
     *
     * @return the user's bio
     */
    public String getAboutMe() {
        return this.aboutMe;
    }

    /**
     * Sets the user's bio
     *
     * @param aboutMe bio to be set
     */
    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    /**
     * returns true if username, password, contact info, and name are all equal
     *
     * @param o The object to compare against
     * @return true if o contains the same data
     */
    public boolean equals(Object o) {
        if (!(o instanceof Profile)) {
            return false;
        }

        Profile p = (Profile) o;
        return p.getUsername().equals(getUsername()) &&
                p.getPassword().equals(getPassword()) &&
                p.getContactInformation().equals(getContactInformation()) &&
                p.getName().equals(getName());
    }

    /**
     * Returns the list of friend requests that the user has sent.
     *
     * @return ArrayList of sent friend requests
     */
    public ArrayList<Profile> getSentFriendRequests() {
        return this.sentFriendRequests;
    }

    /**
     * Adds a profile to the user's sent friend requests list if the given profile does not already have a pending
     * friend request
     *
     * @param profile profile to be added
     * @return true if the friend request was successfully added, false if there was already a pending friend request
     */
    public boolean addSentFriendRequest(Profile profile) {
        if (!this.sentFriendRequests.contains(profile)) {
            this.sentFriendRequests.add(profile);
            return true;
        }
        return false;
    }

    /**
     * Removes a profile from the user's sent friend requests list if the given profile has a pending
     * friend request with the user
     *
     * @param profile profile to be removed
     * @return true if the profile was successfully removed, false if there was not a pending friend request
     */
    public boolean removeSentFriendRequest(Profile profile) {
        if (this.sentFriendRequests.contains(profile)) {
            this.sentFriendRequests.remove(profile);
            return true;
        }
        return false;
    }

    /**
     * First ensures that the raw image data and profile picture correspond,
     * then gets the user's profile picture
     *
     * @return user's profile picture
     */
    public BufferedImage getProfilePicture() {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(this.profilePictureRawData)) {
            this.profilePicture = ImageIO.read(bais);
        } catch (IOException e) {
            this.profilePicture = defaultProfilePicture;
            e.printStackTrace();
        } catch (NullPointerException e) {
            setProfilePicture(defaultProfilePicture);
        }
        return this.profilePicture;
    }

    /**
     * Sets the user's profile picture, and then fills out the raw image data to correspond for
     * serialization
     *
     * @param profilePicture profile picture to be set
     */
    public void setProfilePicture(BufferedImage profilePicture) {
        this.profilePicture = profilePicture;

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(this.profilePicture, "png", baos);
            this.profilePictureRawData = baos.toByteArray();
        } catch (IOException e) {
            this.profilePicture = defaultProfilePicture;
            e.printStackTrace();
        }

    }


}
