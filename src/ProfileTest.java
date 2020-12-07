import org.junit.Assert;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.ServiceConfigurationError;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * A class to test Profile and all of its
 * methods using JUnit4.
 * <p>
 * The requirements are as follows:
 * <p>
 * Each class must have a test verifying that it exists and inherits from the
 * correct superclass. (Hint: If it doesn't inherit from anything, it's inheriting from Object.)
 * Each field in every class must have a test verifying that it exists, along with verifying
 * it has the correct type and access modifier.
 * Each method in every class must have a test verifying that it exists,
 * along with verifying it has the correct return type and access modifier.
 * Each method in every class must have two implementation tests:  One
 * that verifies it works correctly with proper input, and another that tests that it fails with
 * improper input.
 * Note: Methods solely used for GUI operations are exempt from this
 * requirement. Instead, include the testing used to verify they work in your documentation.
 * <p>
 * Fields to test:
 * private static final long serialVersionUID = 1L;
 * private String username;            //Unique username
 * private String password;            //User's password
 * private String name;                //Profile owner's name
 * private String privacySetting;      //Privacy setting - either "Public", "Private", or "Protected"
 * private String contactInformation;  //User's contact information -- potentially change to fixed size array
 * private String aboutMe;             //User's bio to be displayed on profile
 * private static transient BufferedImage defaultProfilePicture;
 * private transient BufferedImage profilePicture;   //User's profile picture
 * private byte[] profilePictureRawData;   //Data for profile picture that can be serialized
 * private final ArrayList<Profile> sentFriendRequests;  //List of profiles that the user has sent friend requests to
 * private ArrayList<String> likesAndInterests;    //User's likes and interests -- potentially have size cap
 * private final ArrayList<Profile> friendsList;         //User's friends list
 * private final ArrayList<Profile> friendRequestList;   //User's list of friend requests
 * <p>
 * Methods to test:
 * public Profile(String username, String password, String name, String contactInformation)
 * public Profile(Profile toCopy)
 * public String getUsername()
 * public void setUsername(String username)
 * public String getPassword()
 * public void setPassword(String password)
 * public String getName()
 * public void setName(String name)
 * public String getPrivacySetting()
 * public void setPrivacySetting(String privacySetting)
 * public String getContactInformation()
 * public void setContactInformation(String contactInformation)
 * public ArrayList<String> getLikesAndInterests()
 * public void setLikesAndInterests(ArrayList<String> likesAndInterests)
 * public ArrayList<Profile> getFriendsList()
 * public boolean addFriend(Profile profile)
 * public boolean removeFriend(Profile profile)
 * public ArrayList<Profile> getFriendRequestList()
 * public boolean addFriendRequest(Profile profile)
 * public boolean removeFriendRequest(Profile profile)
 * public String getAboutMe()
 * public void setAboutMe(String aboutMe)
 * public boolean equals(Object o)
 * public ArrayList<Profile> getSentFriendRequests()
 * public boolean addSentFriendRequest(Profile profile)
 * public boolean removeSentFriendRequest(Profile profile)
 * public BufferedImage getProfilePicture()
 * public void setProfilePicture(BufferedImage profilePicture)
 */
public class ProfileTest {
    
    public static void main(String[] args) {
        
        Result result = JUnitCore.runClasses(TestCase.class);
        
        if (result.wasSuccessful()) {
            System.out.println("All tests ran successfully.");
        } else {
            System.out.println("Incoming failures... >:(");
            for (Failure f : result.getFailures()) {
                System.out.println(f.toString());
            }
        }
    }
    
    public static class TestCase {
        
        // Exists and inherits from correct superclass
        @Test(timeout = 1_000)
        public void ServerProfileDeclarationTest() {
            Class<?> clazz;
            String className;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;
            
            // Set the class being tested
            clazz = Profile.class;
            className = "Profile";
            
            // Perform tests
            
            modifiers = clazz.getModifiers();
            
            superclass = clazz.getSuperclass();
            
            superinterfaces = clazz.getInterfaces();
            
            Assert.assertTrue("Ensure that `" + className + "` is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "` is NOT `abstract`!", Modifier.isAbstract(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "` extends `Object`!", Object.class, superclass);
            
            Assert.assertEquals("Ensure that `" + className + "` implements Serializable!", 1, superinterfaces.length);
        }
        
        // All fields exist with correct type and access modifier
        @Test(timeout = 1_000)
        public void serialVersionUIDFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Profile";
            Field testField;
            int modifiers;
            Class<?> type;
            
            // Set the field that you want to test
            String fieldName = "serialVersionUID";
            
            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = long.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = testField.getModifiers();
            
            type = testField.getType();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is `static`!", !Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the correct type!", expectedType, type);
        }
        
        @Test(timeout = 1_000)
        public void usernameFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Profile";
            Field testField;
            int modifiers;
            Class<?> type;
            
            // Set the field that you want to test
            String fieldName = "username";
            
            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = String.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = testField.getModifiers();
            
            type = testField.getType();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT `final`!", !Modifier.isFinal(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the correct type!", expectedType, type);
        }
        
        @Test(timeout = 1_000)
        public void passwordFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Profile";
            Field testField;
            int modifiers;
            Class<?> type;
            
            // Set the field that you want to test
            String fieldName = "password";
            
            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = String.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = testField.getModifiers();
            
            type = testField.getType();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT `final`!", !Modifier.isFinal(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the correct type!", expectedType, type);
        }
        
        @Test(timeout = 1_000)
        public void nameFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Profile";
            Field testField;
            int modifiers;
            Class<?> type;
            
            // Set the field that you want to test
            String fieldName = "name";
            
            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = String.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = testField.getModifiers();
            
            type = testField.getType();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT `final`!", !Modifier.isFinal(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the correct type!", expectedType, type);
        }
        
        @Test(timeout = 1_000)
        public void privacySettingFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Profile";
            Field testField;
            int modifiers;
            Class<?> type;
            
            // Set the field that you want to test
            String fieldName = "privacySetting";
            
            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = String.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = testField.getModifiers();
            
            type = testField.getType();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT `final`!", !Modifier.isFinal(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the correct type!", expectedType, type);
        }
        
        @Test(timeout = 1_000)
        public void contactInformationFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Profile";
            Field testField;
            int modifiers;
            Class<?> type;
            
            // Set the field that you want to test
            String fieldName = "contactInformation";
            
            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = String.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = testField.getModifiers();
            
            type = testField.getType();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT `final`!", !Modifier.isFinal(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the correct type!", expectedType, type);
        }
        
        @Test(timeout = 1_000)
        public void aboutMeFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Profile";
            Field testField;
            int modifiers;
            Class<?> type;
            
            // Set the field that you want to test
            String fieldName = "aboutMe";
            
            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = String.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = testField.getModifiers();
            
            type = testField.getType();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT `final`!", !Modifier.isFinal(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the correct type!", expectedType, type);
        }
        
        @Test(timeout = 1_000)
        public void defaultProfilePictureFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Profile";
            Field testField;
            int modifiers;
            Class<?> type;
            
            // Set the field that you want to test
            String fieldName = "defaultProfilePicture";
            
            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = BufferedImage.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = testField.getModifiers();
            
            type = testField.getType();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT `final`!", !Modifier.isFinal(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is `static`!", !Modifier.isStatic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is `transient`!", !Modifier.isTransient(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the correct type!", expectedType, type);
        }
        
        @Test(timeout = 1_000)
        public void profilePictureFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Profile";
            Field testField;
            int modifiers;
            Class<?> type;
            
            // Set the field that you want to test
            String fieldName = "profilePicture";
            
            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = BufferedImage.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = testField.getModifiers();
            
            type = testField.getType();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT `final`!", !Modifier.isFinal(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is `transient`!", !Modifier.isTransient(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the correct type!", expectedType, type);
        }
        
        @Test(timeout = 1_000)
        public void profilePictureRawDataFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Profile";
            Field testField;
            int modifiers;
            Class<?> type;
            
            // Set the field that you want to test
            String fieldName = "profilePictureRawData";
            
            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = byte[].class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = testField.getModifiers();
            
            type = testField.getType();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT `final`!", !Modifier.isFinal(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the correct type!", expectedType, type);
        }
        
        @Test(timeout = 1_000)
        public void sentFriendRequestsFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Profile";
            Field testField;
            int modifiers;
            Class<?> type;
            
            // Set the field that you want to test
            String fieldName = "sentFriendRequests";
            
            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = ArrayList.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = testField.getModifiers();
            
            type = testField.getType();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the correct type!", expectedType, type);
        }
        
        @Test(timeout = 1_000)
        public void likesAndInterestsFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Profile";
            Field testField;
            int modifiers;
            Class<?> type;
            
            // Set the field that you want to test
            String fieldName = "likesAndInterests";
            
            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = ArrayList.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = testField.getModifiers();
            
            type = testField.getType();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is not `final`!", !Modifier.isFinal(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the correct type!", expectedType, type);
        }
        
        @Test(timeout = 1_000)
        public void friendsListFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Profile";
            Field testField;
            int modifiers;
            Class<?> type;
            
            // Set the field that you want to test
            String fieldName = "friendsList";
            
            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = ArrayList.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = testField.getModifiers();
            
            type = testField.getType();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the correct type!", expectedType, type);
        }
        
        @Test(timeout = 1_000)
        public void friendRequestListFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Profile";
            Field testField;
            int modifiers;
            Class<?> type;
            
            // Set the field that you want to test
            String fieldName = "friendRequestList";
            
            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = ArrayList.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = testField.getModifiers();
            
            type = testField.getType();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the correct type!", expectedType, type);
        }
        
        // Verifying if each method exists and has the correct return type + access modifier
        @Test(timeout = 1000)
        public void getUsernameMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "getUsername";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = String.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 0 parameters!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void getPasswordMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "getPassword";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = String.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 0 parameters!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void getNameMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "getName";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = String.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 0 parameters!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void getPrivacySettingMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "getPrivacySetting";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = String.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 0 parameters!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void getContactInformationMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "getContactInformation";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = String.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 0 parameters!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void getLikesAndInterestsMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "getLikesAndInterests";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = ArrayList.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 0 parameters!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void getFriendsListMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "getFriendsList";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = ArrayList.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 0 parameters!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void getFriendRequestListMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "getFriendRequestList";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = ArrayList.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 0 parameters!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void getSentFriendRequestsMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "getSentFriendRequests";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = ArrayList.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 0 parameters!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void getProfilePictureMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "getProfilePicture";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = BufferedImage.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 0 parameters!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void setUsernameMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "setUsername";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = void.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameters!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void setPasswordMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "setPassword";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = void.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameters!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void setNameMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "setName";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = void.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameters!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void setPrivacySettingMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "setPrivacySetting";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = void.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameters!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void setContactInformationMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "setContactInformation";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = void.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameters!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void setLikesAndInterestsMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "setLikesAndInterests";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = void.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, ArrayList.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameters!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void setProfilePictureMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "setProfilePicture";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = void.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, BufferedImage.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameters!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void setAboutMeMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "setAboutMe";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = void.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameters!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void addFriendMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "addFriend";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = boolean.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, Profile.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 Profile parameter!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void removeFriendMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "removeFriend";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = boolean.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, Profile.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 Profile parameter!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void addFriendRequestMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "addFriendRequest";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = boolean.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, Profile.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 Profile parameter!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void removeFriendRequestMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "removeFriendRequest";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = boolean.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, Profile.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 Profile parameter!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void addSentFriendRequestMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "addSentFriendRequest";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = boolean.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, Profile.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 Profile parameter!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void removeSentFriendRequestMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "removeSentFriendRequest";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = boolean.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, Profile.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 Profile parameter!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void equalsMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "equals";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = boolean.class;
            
            // Set the class being tested
            clazz = Profile.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, Object.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 Object parameter!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
        // new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555")
        
        // Two implementation tests per method
        @Test(timeout = 1000)
        public void addFriend1() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            Profile prof2 = new Profile("jebbush68", "jebisawesome", "Jeb Bush", "8005555555");
            
            boolean tf = prof1.addFriend(prof2);
            
            Assert.assertTrue("Should return true since the profile2 is not in profile1's friends list", tf);
        }
        
        // No second test, no way to give invalid input
        
        @Test(timeout = 1000)
        public void removeFriend1() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            Profile prof2 = new Profile("jebbush68", "jebisawesome", "Jeb Bush", "8005555555");
            
            prof1.addFriend(prof2);
            
            boolean tf = prof1.removeFriend(prof2);
            
            Assert.assertTrue("Should return true since the profile2 is in profile1's friends list", tf);
        }
        
        // No second test, no way to give invalid input
        
        @Test(timeout = 1000)
        public void addFriendRequest1() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            Profile prof2 = new Profile("jebbush68", "jebisawesome", "Jeb Bush", "8005555555");
            
            boolean tf = prof1.addFriendRequest(prof2);
            
            Assert.assertTrue("Should return true since the profile2 is not in profile1's friendRequestList", tf);
        }
        
        // No second test, no way to give invalid input
        
        @Test(timeout = 1000)
        public void removeFriendRequest1() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            Profile prof2 = new Profile("jebbush68", "jebisawesome", "Jeb Bush", "8005555555");
            
            prof1.addFriendRequest(prof2);
            
            boolean tf = prof1.removeFriendRequest(prof2);
            
            Assert.assertTrue("Should return true since the profile2 is in profile1's friendRequestList", tf);
        }
        
        // Second test need not apply
        
        @Test(timeout = 1000)
        public void addSentFriendRequest() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            Profile prof2 = new Profile("jebbush68", "jebisawesome", "Jeb Bush", "8005555555");
            
            boolean tf = prof1.addSentFriendRequest(prof2);
            
            Assert.assertTrue("Should return true since the profile2 is not in profile1's sentFriendRequests", tf);
        }
        
        // second test yada yada yk the drill
        
        @Test(timeout = 1000)
        public void removeSentFriendRequest() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            Profile prof2 = new Profile("jebbush68", "jebisawesome", "Jeb Bush", "8005555555");
            
            prof1.addSentFriendRequest(prof2);
            
            boolean tf = prof1.removeSentFriendRequest(prof2);
            
            Assert.assertTrue("Should return true since the profile2 is in profile1's sentFriendRequests", tf);
        }
        
        // No second test needed
        
        @Test(timeout = 1000)
        public void equals1() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            Profile prof2 = new Profile("jebbush68", "jebisawesome", "Jeb Bush", "8005555555");
            
            boolean tf = prof1.equals(prof2);
            
            Assert.assertTrue("Should return false", !tf);
        }
        
        @Test(timeout = 1000)
        public void equals2() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            Profile prof2 = new Profile("jebbush68", "jebisawesome", "Jeb Bush", "8005555555");
            
            boolean tf = prof1.equals("bruh");
            
            Assert.assertTrue("Should return false", !tf);
        }
        
        @Test(timeout = 1000)
        public void getUsername() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            
            Assert.assertTrue("Should return username", ("jebbush69".equals(prof1.getUsername())));
        }
        
        @Test(timeout = 1000)
        public void getPassword() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            
            Assert.assertTrue("Should return password", ("jebisawesome".equals(prof1.getPassword())));
        }
        
        @Test(timeout = 1000)
        public void getName() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            
            Assert.assertTrue("Should return name", ("Jeb Bush".equals(prof1.getName())));
        }
        
        @Test(timeout = 1000)
        public void getPrivacySetting() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            
            Assert.assertTrue("Should return privacy setting", ("Public".equals(prof1.getPrivacySetting())));
        }
        
        @Test(timeout = 1000)
        public void getContactInformation() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            
            Assert.assertTrue("Should return privacy setting", ("8005555555".equals(prof1.getContactInformation())));
        }
        
        @Test(timeout = 1000)
        public void getLikesAndInterests() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            
            Assert.assertTrue("Should return likes and interests", (new ArrayList<String>().equals(prof1.getLikesAndInterests())));
        }
        
        @Test(timeout = 1000)
        public void getFriendsList() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            
            Assert.assertTrue("Should return friends list", (new ArrayList<Profile>().equals(prof1.getFriendsList())));
        }
        
        @Test(timeout = 1000)
        public void getFriendRequestList() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            
            Assert.assertTrue("Should return friends list", (new ArrayList<Profile>().equals(prof1.getFriendRequestList())));
        }
        
        @Test(timeout = 1000)
        public void getAboutMe() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            
            Assert.assertTrue("Should return about me", ("".equals(prof1.getAboutMe())));
        }
        
        @Test(timeout = 1000)
        public void getSentFriendRequests() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            
            Assert.assertTrue("Should return sent friend requests", (new ArrayList<Profile>().equals(prof1.getSentFriendRequests())));
        }
        
        /**
         * Compares two images pixel by pixel.
         *
         * @param imgA the first image.
         * @param imgB the second image.
         * @return whether the images are both the same or not.
         * <p>
         * https://stackoverflow.com/questions/11006394/is-there-a-simple-way-to-compare-bufferedimage-instances
         */
        public static boolean compareImages(BufferedImage imgA, BufferedImage imgB) {
            // The images must be the same size.
            if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
                return false;
            }
            
            int width = imgA.getWidth();
            int height = imgA.getHeight();
            
            // Loop over every pixel.
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    // Compare the pixels for equality.
                    if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                        return false;
                    }
                }
            }
            
            return true;
        }
        
        @Test(timeout = 1000)
        public void getProfilePicture() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            
            try {
                Assert.assertTrue("Should return profile picture", (compareImages(ImageIO.read(new File("BetterBookDefaultProfilePicture.png")), prof1.getProfilePicture())));
            } catch (Exception e) {
                Assert.fail("Error thrown");
            }
        }
        
        @Test(timeout = 1000)
        public void setUsername() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            
            prof1.setUsername("ok");
            
            Assert.assertTrue("Not setting username", ("ok".equals(prof1.getUsername())));
        }
        
        @Test(timeout = 1000)
        public void setPassword() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            
            prof1.setPassword("ok");
            
            Assert.assertTrue("Not setting password", ("ok".equals(prof1.getPassword())));
        }
        
        @Test(timeout = 1000)
        public void setName() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            
            prof1.setName("ok");
            
            Assert.assertTrue("Not setting name", ("ok".equals(prof1.getName())));
        }
        
        @Test(timeout = 1000)
        public void setPrivacySetting() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            
            prof1.setPrivacySetting("ok");
            
            Assert.assertTrue("Not setting privacy setting", ("ok".equals(prof1.getPrivacySetting())));
        }
        
        @Test(timeout = 1000)
        public void setContactInformation() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            
            prof1.setContactInformation("ok");
            
            Assert.assertTrue("Not setting contact info", ("ok".equals(prof1.getContactInformation())));
        }
        
        @Test(timeout = 1000)
        public void setLikesAndInterests() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            
            ArrayList<String> a = new ArrayList<String>();
            a.add("ok");
            
            prof1.setLikesAndInterests(a);
            
            Assert.assertTrue("Not setting likes and interests", (prof1.getLikesAndInterests().get(0).equals("ok")));
        }
        
        @Test(timeout = 1000)
        public void setAboutMe() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            
            prof1.setAboutMe("ok");
            
            Assert.assertTrue("Not setting likes and interests", (prof1.getAboutMe().equals("ok")));
        }
        
        @Test(timeout = 1000)
        public void setProfilePicture() {
            Profile prof1 = new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555");
            try {
                prof1.setProfilePicture(ImageIO.read(new File("forJUNITTests/bruh.jpg")));
                Assert.assertTrue("Not setting profile correctly", (compareImages(ImageIO.read(new File("forJUNITTests/bruh.jpg")), prof1.getProfilePicture())));
            } catch (Exception e) {
                Assert.fail("Error thrown.");
            }
        }
        
        
        @Test(timeout = 1000)
        public void save1() {
            Server s = new Server(new Socket()); //dummy server
            s.betterBookProfiles = new ArrayList<>();
            s.betterBookProfiles.add(new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555"));
            try {
                s.save("forJUNITTests/saveTestOne.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileInputStream fi;
            try {
                fi = new FileInputStream(new File("forJUNITTests/saveTestOne.txt"));
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            ObjectInputStream oi;
            try {
                oi = new ObjectInputStream(fi);
            } catch (Exception e) {
                System.out.println("Empty profile file!");
                Assert.fail("'save' Did not write to file.");
                return;
            }
            try {
                Profile p = (Profile) oi.readObject();
                Assert.assertTrue(p.equals(new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555")));
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail("'save' Error after reading object");
                return;
            }
        }
        
        @Test(timeout = 1000)
        public void save2() {
            Server s = new Server(new Socket()); //dummy server
            s.betterBookProfiles = null;
            try {
                s.save("forJUNITTests/saveTestOne.txt"); // .txt file should be empty at time of test
                Assert.assertTrue("Task 'save' succeeded incorrectly. (try block)", false);
                return;
            } catch (IOException e) {
                e.printStackTrace();
                Assert.assertTrue("Task 'save' failed successfully.", true); // Omegalul I guess
                return;
            } catch (NullPointerException e) {
                // If betterBookProfiles isn't initialized it will throw
                // a NPE
                Assert.assertTrue("Task 'save' failed successfully.", true); // Omegalul I guess
                return;
            }
        }
        
        
    }
}