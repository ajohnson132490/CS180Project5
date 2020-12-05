# CS180Project5
ALL TIMES ARE EST

BetterBook
 
Server at ajohnson132490.xyz
 
Roles:
 Front End:
	 Austin Johnson (Client)
  Paul Kraessig?
  Nicholas Fang…? (flexible)
 Backend:
  Jacob Zietek (Server)
  Patrick Florendo (Client)
 
ToDo's
 Client:
  //Insert todo's
  
 Server:
  //Insert todo's
  
 GUI:
  I just finished creating the basic structure of the GUI. It has the menu bar, but overall, no functionality yet, but I wanted to get some sort of start of the GUI. - Austin
 
Classes:
 Profile (Stores user information)
 BetterBookServer (Does stuff)
 BetterBookClient (Takes user input and sends it to the server)
 

Profile Class:
Profile.java creates objects for each profile of BetterBook. It implements
the serializable interface which allows Profiles to be stored in a .txt file.
All of the information for each profile is stored in a Profile object. 
The fields of each profile object include Strings for username, password, 
name, their account's privacy setting, contact information, and an 
"About me" section. There are also ArrayList fields that contain the 
user's likes and interests as well as friends list, list of sent friend 
requests, and list of pending friend requests. The final fields are a transient
BufferedImage that stores the user's profile picture, and a byte array that holds 
the profile picture's raw data to be serialized. 

There are two contructors, one that will be called upon intialization that intitialies the fields and
creates the user's profile upon sign-in. There is also a copy contructor, to be used internally in some
updating methods.

Profile also contains accessor and mutator methods for the fields username, password, name, privacy setting,
contact information, about me, and likes and interests. There are also accessor methods for friends list, 
sent friend requests, pending freind requests, and instead of mutators for these fields, there are
methods to add a profile and remove a profile from these lists, as long as that profile exists in the ArrayList.
There is also an accessor and mutator method for the profile picture, which upon intialization, is 
set to the default profile picture, stored statically in the profile, pointing to a png image in the package. 
The accessor and mutator methods take in a buffered image, and update the byte arrays of raw data to ensure
that the image is correctly serialized and stored. Profile.java was tested using JUnit, including tests for
each field, contructor, and method with incorrect and correct input. 
 
Ideas:
 Store all user data as an object in the client, 
 using a refresher method to update users.
 Create client in the constructor of the GUI.
 Multithread GUI constructor to create client
 and load your profile.
 Server is mostly for storage.
 Server has master list of profiles.
 Storing friend requests in an arraylist.
 Do private and protected profiles.
 
