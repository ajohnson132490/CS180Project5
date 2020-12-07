# **CS180Project5**

**Client**

This is the client class that is used to connect to the server to send and receive information. It includes methods to keep the Profiles stored by the server and client in sync. It abides by a network protocol as follows:

1st Message to Server: &quot;receiveProfiles&quot; or &quot;sendProfiles&quot;

case receiveProfiles

- Client wipes current profileList
- Client adds all serialized profiles sent by Client one by one
- Client stops taking input when Server sends &quot;Goodbye&quot;

case sendProfiles

- Client sends all of the profiles, serialized, one by one
- ends sending profiles with a String, &quot;Goodbye&quot;

Testing on the Client class was predominantly done through JUnit4 tests, but methods involving Sockets were tested live. Test verifying that it exists and inherits from the correct superclass.

_private ArrayList\&lt;Profile\&gt; betterBookProfiles_

This is the ArrayList that contains every BetterBook Profile. The ArrayList is constantly kept synchronized with the server and other connected clients. Tested by making sure the Client is sending and receiving output and input streams of Profiles properly. Also tested using JUnit4. Test verifying that it exists, along with verifying it has the correct type and access modifier.

_private Socket sock_

This is the socket used to connect to the server. Tested by making sure the Client is sending and receiving output and input streams properly. Also tested using JUnit 4. Test verifying that it exists, along with verifying it has the correct type and access modifier.

_private ObjectOutputStream writer_

This is the ObjectOutputStream used to send messages and Profiles to the server. Tested by making sure that the client is sending messages and Profiles to the server properly. Also tested using JUnit 4. Test verifying that it exists, along with verifying it has the correct type and access modifier.

_private ObjectInputStream objectReader_

This is the ObjectInputStream used to receive Profiles from the server. Tested by making sure that the server is sending messages and Profiles to the client properly. Also tested using JUnit 4. Test verifying that it exists, along with verifying it has the correct type and access modifier.

_public Client(String hostname, int portNumber) throws UnknownHostException, IOException_

This is the constructor for Client. It connects using a socket on the given hostname and portNumber, instantiates betterBookProfiles, and instantiates objectReader and writer on the socket&#39;s input and output streams, respectively. Tested by making sure the Client is sending and receiving output and input streams properly. Also tested using JUnit4.

_public void disconnect()_

This is the method that tells the server the client is disconnecting and closes the writer and objectReader. Tested by making sure that the client is sending the goodbye message to the server properly.

_public void receiveProfiles()_

This function handles receiving Profiles from the server using the network protocol. Tested by making sure that the client is receiving the list of Profiles from the server correctly.

_public void sendProfiles()_

This function handles sending Profiles to the server using the network protocol. Tested by making sure that the server correctly receives Profiles from the client.

_public void createProfile(String userName, String pass, String name, String contactInfo) throws UserNotFoundError_

This function creates a new Profile using the given parameters, adds it to betterBookProfiles, and sends the updated list to the server. Tested by making sure that the server receives the updated list of Profiles, including the newly created one.

_public void updateProfile(Profile oldProfile, Profile newProfile) throws UserNotFoundError_

This function updates information from the oldProfile using information from the newProfile. Tested by making sure that the server receives the updated list of Profiles, including the newly edited one.

_public void deleteProfile(Profile p) throws UserNotFoundError_

This function deletes the specified Profile from betterBookProfiles. Tested by making sure that the server receives the updated list of Profiles with the given Profile missing.

_public void sendFriendRequest(Profile sending, Profile receiving)_

This function sends a friend request from sending to receiving. Tested by making sure that the Profiles in question are sent to the server and have been updated to reflect the pending request.

_public int locateProfile(Profile p)_

This function requests the updated list of Profiles from the server, then searches for a matching Profile. Tested by making sure that the client is receiving the most updated list from the server and is returning the correct index.

_public Profile locateProfile(String username)_

This function requests the updated list of Profiles from the server, then searches for a Profile with the matching username. Tested by manually making sure that the client is receiving the most updated list from the server and is returning the correct Profile.

_public Profile signIn(String username, String password) throws UserNotFoundError_

This function requests the updated list of Profiles from the server and checks it to see if any of them have data matching the given parameters. It returns the Profile with the matching parameters if one is found. Tested by making sure that the client returns the correct Profile when given the correct sign-in information and returns nothing otherwise.

_public ArrayList\&lt;Profile\&gt; getBetterBookProfiles()_

This function returns betterBookProfiles. Test verifying that it exists, along with verifying it has the correct return type and access modifier. Further testing was done manually.

_public void setBetterBookProfiles(ArrayList\&lt;Profile\&gt; betterBookProfiles)_

This function sets betterBookProfiles. Test verifying that it exists, along with verifying it has the correct return type and access modifier.

**GUI**

Gui Class: GUI.java creates a complex GUI for user interaction. It implements runnable so it can run the actual GUI on the client device. It also creates a Client object in the constructor which allows the GUI to seamlessly connect to the server.

Testing for this class was done manually.

_private Client client_

The client object that connects to localhost port 4242.

_public Profile profile_

The local user, who is currently logged on.

_private final String hostname_

The default hostname, localhost.

_private final String portNumber_

The default port to connect to, 4242.

_public GUI gui_

The GUI, for adding to the frame

_public ArrayList\&lt;String\&gt; interestsArrayList_

A local version of the profiles interests, stored as an array list

_public JButton signInButton_

The button users push to sign in on the sign in page.

_public JButton newAccountButton_

The button users push to create a new account on the sign in page

_public JButton registerButton_

The button users push to confirm an account creation on the new account page.

_public JButton confirmButton_

The button users push to confirm an account edit on the edit account page.

_public JButton confirmInterestsButton_

The button users push to confirm editing their interests on the edit interests page.

_public JButton[] confirmRequestButton_

This is an array of confirm buttons. It only creates the number of buttons you need based on the number of friend requests to be confirmed or denied.

_public JButton[] denyRequestButton_

This is an array of deny buttons. It only creates the number of buttons you need based on the number of friend requests to be confirmed or denied.

_public JButton refresh_

This button allows the user to refresh the page to see any changes that didn&#39;t automatically update.

_public JButton editProfilePicture_

This button allows the user to edit their profile picture on the edit profile page

_public JMenu accountMenu_

This menu hosts all menu options relating to modifying your account.

_public JMenu allUsersMenu_

This menu displays all the current users in a drop down fashion, giving the user the option to select a user and send them a friend request or view their profile.

_public JMenu pendingFriendRequests_

This menu displays all of the users pending friend requests, giving them options to revoke the friend request.

_public JMenuItem__editAccount_

Allows the user to edit their account.

_public JMenuItem__editInterests_

Allows the user to edit their listed interests.

_public JMenuItem deleteAccount_

Allows the user to delete their account.

_public JMenuItem[] allUsers_

An array of all current users, to be displayed in the menu bar.

_public JMenuItem[] allRequests_

An array of all current pending friend requests sent by the user to be displayed in the menu bar.

_public JTextField usernameField_

This field allows the user to set their username when creating an account or editing an existing one.

_public JTextField__passwordField_

This field allows the user to set their password when creating an account or editing an existing one.

_public JTextField verifyPassword_

This field allows the GUI to verify the users password when creating an account or editing an existing one.

_public JTextField nameField_

This field allows the user to set their publicly displayed name when creating an account or editing an existing one.

_public JTextField aboutMeField_

This field allows the user to write a short about me section when creating an account or editing an existing one.

_public JTextField contactInformationField_

This field allows the user to add an email or phone number when creating an account or editing an existing one.

_public JTextField[] interests_

This array allows the GUI to locally store and display all of the users interests based on the profile.getLikesAndInterests();

_public JLabel username_

A local storage of the profile&#39;s username, shown on the profile page.

_public JLabel name_

A local storage of the profile&#39;s displayed name, shown on the profile page.

_public JLabel contactInformation_

A local storage of the profile&#39;s contact information, shown on the profile page.

_public JLabel aboutMe_

A local storage of the profile&#39;s about me section, shown on the profile page.

_public JLabel privacySetting_

A local storage of the profile&#39;s privacy setting, shown on the profile page.

_public JLabel profilePicture_

A local storage of the profile&#39;s profile picture, shown on the profile page.

_public File file_

The file where the profile picture is located.

_public JFileChooser fileChooser_

The file chooser that selects the new profile picture in the edit account page.

_public BufferedImage image_

The BufferedImage that reads in the file chosen by the JFileChooser and converts it into a manipulatable image.

_public Image resizedImage_

The end-result of the profile picture, a resized version of the BufferedImage of the profile picture file selected in the JFileChooser.

_public int chooserResponse_

The integer value of the user response in the JFileChooser window.

_public ActionListener_ actionListener

This is the general purpose action listener, for all buttons and listeners not related to the menu bar on the profile page.

_public ActionListener menuBarListener_

This action listener is specific to checking actions on the profile page menu bar.

_public GUI()_

This constructor creates a client object and connects to the server.

_public void signInPage()_

This page is the initial startup page that allows the user to either sign in, or create a new account. Testing was done by making sure the frame appeared correctly after connecting to the server, with each field and button placed in their intended positions. Next, each button was tested. It was verified that the signInButton only allowed users to login to the application if a correct username and password combination was submitted and authenticated by the client, and to show an error message to the user if the combination was incorrect. The newAccountButton was tested to make sure that it correctly loaded the newAccountPage, and that after an account was created, the profile page would appear.

_public void newAccountPage()_

This page is where users can create a new account. On creation it logs the user in and takes them to the profile page. Testing was done by making sure the frame appeared correctly after the newAccountButton was pressed on the signInPage. Then, it was verified that after pressing the registerButton, each of the text fields would be added to a profile object and displayed on the profile page. For pre-existing usernames, it was verified that the page would show an error pane, prompting the user to choose a different username.

_public void editAccountPage()_

This page is where users can edit their account. Testing was done by accessing the edit account page and verifying that all fields, including the profile picture, could be modified and that the profile page reflected the changes.

_public void addInterestsPage()_

This page is where users can edit their displayed interests. Testing was done by making sure interests that were added appeared on the profile picture frame.

_public void profilePage()_

This page displays all of the users info, interests, friends, and pending friend requests. It also allows the user to edit their account details, send friend requests, and revoke unanswered friend requests. Testing was done by clicking all menu items, and pressing the add interests button. Additionally, when menu items ran their respective functions, we verified that the profile page updated accordingly.

_public void viewAllProfiles(JMenuBar menuBar)_

This function allows the user to view all the current users in the system and send them friend requests, or view their profile. It adds the list of users to the JMenuBar menuBar. Testing was done by verifying that the user could view all users in the menuBar, and that all users were only listed once.

_public void viewPendingFriendRequests(JMenuBar menuBar)_

This function allows the user to view all of their current pending friend requests and revoke any if necessary. It adds the list of the pending friends requests to the JMenuBar menuBar Testing was done by sending a friend request and verifying that it appears in the view pending friend requests menu, and the request removes itself when answered or revoked.

_public void createMenuBar(JFrame frame)_

This function adds a menu bar with account management and friend request menus to the JFrame frame. Testing was done by making sure that the menu bar exists on the profile page frame, and that each selection in the menu bar opened up the proper frame from the action listener.

_public JPanel displayUserInformation(Profile currentProfile) throws UserNotFoundError_

This function returns a JPanel with the currentProfile&#39;s username, name, about me paragraph, contact information, and privacy setting. It throws a user not found error if the Profile currentProfile cannot be found. This method was tested by verifying that the returned JPanel had all the users expected information and updates when modified.

_public JPanel displayUserFriendList(Profile currentProfile) throws UserNotFoundError_

This function returns a JPanel with the currentProfile&#39;s list of friends printed out. It throws a user not found error if the Profile currentProfile cannot be found. Testing was done by making sure that on application startup, existing friends were displayed on the profile page, and new friends were added to the list when friend requests were accepted.

_public JPanel displayUserInterestList(Profile currentProfile) throws UserNotFoundError_

This function returns a JPanel with the list of the currentProfile&#39;s interests printed out. It throws a user not found error if the Profile currentProfile cannot be found. This method was tested by verifying that the returned JPanel has all expected interests displayed, of an &quot;Add Interests&quot; button if no interests had been previously set.

_public JPanel displayPendingFriendRequests(JFrame frame) throws UserNotFoundError_

This function returns a JPanel with a list of the pending friend requests for the Profile profile. It reloads the JFrame frame when a request is accepted or denied to reflect the change. It throws a user not found error if the Profile currentProfile cannot be found. Testing was done by making sure that all friend requests showed up on the profile page frame and that the add and deny friend request buttons functioned properly.

_public void run()_

This function is implemented from Runnable, and it runs the signInPage(); function. This method was verified by the fact that the program ran and the GUI appeared as expected.

_public static void main(String[] args)_

This function uses swing utilities to start the GUI. This method was verified by the fact that the program ran and the GUI appeared as expected.

**Profile**

Profile Class: Profile.java creates objects for each profile of BetterBook. It implements the serializable interface which allows Profiles to be stored in a .txt file. All of the information for each user&#39;s profile is stored in a Profile object.

JUnit testing verifying that it exists and inherits from the correct superclass.

_private static final long serialVersionUID_

The serial id to be used in serialization.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_private String username_

Stores the username for each profile as a String.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_private String password_

Stores the password for each profile as a String.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_private String name_

Stores the user&#39;s name as a String.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_private String privacySetting_

Stores the user&#39;s privacy setting as a String.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_private String contactInformation_

Stores the user&#39;s contact information as a String.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_private String aboutMe_

Stores the user&#39;s &quot;bio&quot; as a String.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_private ArrayList\&lt;String\&gt; likesAndInterests_

Stores the user&#39;s likes and interests as an ArrayList of Strings.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_private ArrayList\&lt;Profile\&gt; friendsList_

Stores the user&#39;s friends list as an ArrayList of Profiles.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_private ArrayList\&lt;Profile\&gt; friendRequestList_

Stores the user&#39;s pending friend requests as an ArrayList of Profiles.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_private ArrayList\&lt;Profile\&gt; sentFriendRequests_

Store&#39;s the user&#39;s sent friend requests as an ArrayList of Profiles.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_private static transient BufferedImage defaultProfilePicture_

Stores the BetterBook default profile picture, pointing to a .png image.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_private transient BufferedImage profilePicture_

A BufferedImage that stores the user&#39;s profile picture.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_private byte[] profilePictureRawData_

A byte array that stores the profile picture&#39;s raw data, to be used in serialization, since BufferedImage cannot be directly serialized.

_public Profile(String username, String password, String name, String contactInformation)_

\*Constructor for a Profile object. Takes parameters for username, password, name, and contact information. Sets privacy setting to public, aboutMe to an empty String, profile picture to the default image, and initializes ArrayLists for likes and interests, friends list, sent friend requests, and friend requests list.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public Profile(Profile toCopy)_

Copy constructor for a Profile object

_public String getUsername()_

Gets the profile&#39;s username.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public void setUsername(String username)_

Sets the profile&#39;s username.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public String getPassword()_

Gets the user&#39;s password.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public String setPassword(String password)_

Sets the profile&#39;s password.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public String getName()_

Gets the user&#39;s name.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public void setName(String name)_

Sets the user&#39;s name.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public string getPrivacySetting()_

Gets the user&#39;s privacy setting.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public void setPrivacySetting(String privacySetting)_

Sets the user&#39;s privacy setting.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public string getContactInformation_

Gets the user&#39;s contact information.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public void setContactInformation(String contactInformation)_

Sets the user&#39;s privacy setting.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public ArrayList\&lt;String\&gt; getLikesAndInterests_

Gets the user&#39;s likes and interests.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public string getAboutMe_

Gets the user&#39;s about me section.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public void setAboutMe(String aboutMe)_

Sets the user&#39;s about me section.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public void setLikesAndInterests(ArrayList\&lt;String\&gt; likesAndInterests)_

Sets the user&#39;s likes and interests.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public ArrayList\&lt;Profile\&gt; getFriendsList_

Gets the user&#39;s friends list.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public boolean addFriend(Profile profile)_

Adds a friend to the user&#39;s friends list if the two profiles are not already friends.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public boolean removeFriend(Profile profile)_

Removes a profile from the user&#39;s friends list if the two profiles are friends.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public ArrayList\&lt;Profile\&gt; getFriendRequestList_

Gets the user&#39;s friend request list.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public boolean addFriendRequest(Profile profile)_

Adds a profile to the user&#39;s friend request list, if the other user does not already have a pending friend request

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public boolean removeFriendRequest(Profile profile)_

Removes a profile from the user&#39;s friend request list, if the other profile already has a pending friend request.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public ArrayList\&lt;Profile\&gt; getSentFriendRequests_

Gets the user&#39;s list of sent friend requests.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public boolean addSentFriendRequests(Profile profile)_

Adds a profile to the user&#39;s sent friends request list if the two profiles do not already have a pending friend request.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public boolean removeSentFriendRequest(Profile profile)_

Removes a profile from the user&#39;s sent friend request list if the two profiles do not already have a pending friend request list.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public boolean equals(Object o)_

Returns true if username, password, name, and contact info of another profile are all equal.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public BufferedImage getProfilePicture()_

Gets the user&#39;s profile picture, and updates the byte array profilePictureRawData accordingly.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

_public void setProfilePicture(BufferedImage profilePicture)_

Sets the user&#39;s profile picture, and updates the byte array profilePictureRawData accordingly.

JUnit Testing verifying that it exists, along with verifying it has the correct type and access modifier.

**Server**

This is the server class designed to send, store, and process data from clients. It is the heart of BetterBook. It is also multithreaded to accept multiple clients at once. It abides by a network protocol as follows:

1st Message to Server: &quot;receiveProfiles&quot; or &quot;sendProfiles&quot;

case receiveProfiles

- Server sends all of the profiles, serialized, one by one
- ends sending profiles with a String, &quot;Goodbye&quot;

case sendProfiles

- Server wipes current profileList
- Server appends all serialized profiles sent by Client one by one
- Server stops taking input when Client sends &quot;Goodbye&quot;

Testing on the Server class was predominantly done through JUnit4 tests, but methods involving Sockets were tested live. Test verifying that it exists and inherits from the correct superclass.

_private Socket csock_

This is the socket used to connect with clients. Tested by making sure the Server is sending and receiving output and input streams properly. Test verifying that it exists, along with verifying it has the correct type and access modifier.

_volatile static ArrayList\&lt;Profile\&gt; betterBookProfiles_

This is the ArrayList that contains every BetterBook profile as the server class is running. This ArrayList is synchronized across all threads, meaning each client that connects will always have access to the current list of updates profiles. Tested by making sure the Server is sending and receiving output and input streams of Profiles properly. Also tested using JUnit4. Test verifying that it exists, along with verifying it has the correct type and access modifier.

_public static void main(String[] args) throws Exception_

This is the main method of class Server. Once it runs, it will load in all saved BetterBook Profiles from a text file &quot;betterBookProfiles.txt&quot; containing serialized profile objects and starts accepting clients using a ServerSocket from localhost on port 4242. Once there is a new connection, it spawns a new Server thread. Tested live by making sure it loads in saved profiles, this can be seen through print statements. Also tested by making sure it spawns Threads properly, this can be seen through print statements.

_public Server(Socket csock)_

This is the only constructor for Server. It takes in a client socket (csock) as a parameter. The main method will set up a server socket and create a new Thread of Server with the connected client sockets. Tested by making sure the Server is sending and receiving output and input streams properly. Also tested using JUnit4.

_public void run()_

This function handles all of the interaction between server and client. It is called after a client connects to the server. Tested by making sure the Server is sending and receiving output and input streams properly.

_public synchronized static void handleMessage(ObjectInputStream reader,_

_ObjectOutputStream objectWriter, String message)_

handleMessage will handle messages sent from the client to the server, as per the aforementioned network protocol. It will either send the current profile list to the client, or it will write a profile list into memory when the client modifies it. If the profile list is modified, handleMessage will call save to save the current list to a text file called &quot;betterBookProfiles.txt&quot;. Tested by making sure the Server is sending and receiving output and input streams properly, and is handling the messages as per protocol. If it didn&#39;t work, the client and server would both fail on connection as the first thing the Client does is retrieve a profile list. Also tested using JUnit4. Test verifying that it exists, along with verifying it has the correct return type and access modifier.

_public static void loadProfiles(String fileName) throws IOException, ClassNotFoundException_

Loads serialized BetterBook profiles from a specified .txt file. Tested using JUnit4. Test verifying that it exists, along with verifying it has the correct return type and access modifier. Two implementation tests: One that verifies it works correctly with proper input, and another that tests that it fails with improper input.

_public static void save(String fileName) throws IOException_

Saves serialized BetterBook profiles to a .txt file. This should be done frequently to ensure the list is saved if the Server crashes. Tested using JUnit4. Test verifying that it exists, along with verifying it has the correct return type and access modifier. Two implementation tests: One that verifies it works correctly with proper input, and another that tests that it fails with improper input.

**UserNotFoundError**

This is the custom exception that is thrown and caught in the client and GUI classes for when a profile object cannot be found.

_public UserNotFoundError(String message)_

A constructor that takes the String parameter and lets its superclass Exception handle it. Tested with JUnit 4. Test verifying that it exists, along with verifying that it has the correct access modifier.

_public UserNotFoundError()_

A constructor that throws an error to the runtime without a message. Tested with JUnit 4. Test verifying that it exists, along with verifying that it has the correct access modifier.
