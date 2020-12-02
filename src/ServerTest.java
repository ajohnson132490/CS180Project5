import org.junit.Assert;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.*;
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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.util.ArrayList;
import java.util.ServiceConfigurationError;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * A class to test Server and all of its
 * methods using JUnit4.
 *
 * The requirements are as follows:
 *
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
 *
 * tests methods save, loadProfiles, and handleMessage
 */
public class ServerTest {

    public static void main(String[] args) {
        try {
            File f = new File("forJUNITTests/saveTestOne.txt");
            f.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Result result = JUnitCore.runClasses(TestCase.class);

        if (result.wasSuccessful()) {
            System.out.println("All tests ran successfully.");
        } else {
            System.out.println("Incoming failures...");
            for (Failure f: result.getFailures()) {
                System.out.println(f.toString());
            }
        }
    }
    public static class TestCase {

        // Exists and inherits from correct superclass
        @Test(timeout = 1_000)
        public void ServerClassDeclarationTest() {
            Class<?> clazz;
            String className;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            // Set the class being tested
            clazz = Server.class;
            className = "Server";

            // Perform tests

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `" + className + "` is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "` is NOT `abstract`!", Modifier.isAbstract(modifiers));

            Assert.assertEquals("Ensure that `" + className + "` extends `Object`!", Object.class, superclass);

            Assert.assertEquals("Ensure that `" + className + "` implements Runnable!", 1, superinterfaces.length);
        }

        // All fields exist with correct type and access modifier
        @Test(timeout = 1_000)
        public void csockFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Server";
            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "csock";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = Socket.class;

            // Set the class being tested
            clazz = Server.class;

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
        public void betterBookProfilesFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Server";
            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "betterBookProfiles";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = ArrayList.class;

            // Set the class being tested
            clazz = Server.class;

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

            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is not `private`!", !Modifier.isPrivate(modifiers));

            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is not `final`!", !Modifier.isFinal(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is `static`!", !Modifier.isStatic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is `volatile`!", !Modifier.isVolatile(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the correct type!", expectedType, type);
        }

        // Verifying if each method exists and has the correct return type + access modifier
        @Test(timeout = 1000)
        public void serverSaveMethodTest() {
            Class<?> clazz;
            String className = "Server";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 1;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "save";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = void.class;


            // Set the class being tested
            clazz = Server.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 String parameter!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is `static`!", !Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 1 `throws` clause!", expectedLength, exceptions.length);
        }

        @Test(timeout = 1000)
        public void serverLoadProfilesMethodTest() {
            Class<?> clazz;
            String className = "Server";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 2;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "loadProfiles";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = void.class;


            // Set the class being tested
            clazz = Server.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 String parameter!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is `static`!", !Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 2 `throws` clause!", expectedLength, exceptions.length);
        }

        @Test(timeout = 1000)
        public void serverHandleMessageMethodTest() {
            Class<?> clazz;
            String className = "Server";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "handleMessage";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = void.class;


            // Set the class being tested
            clazz = Server.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, ObjectInputStream.class, ObjectOutputStream.class, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 ObjectInputStream parameter, 1 ObjectOutputStream, and 1 String parameter!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `synchronized`!", Modifier.isSynchronized(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is `static`!", !Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }

        // Two implementation tests per method
        @Test(timeout = 1000)
        public void save1(){
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
        public void save2(){
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

        @Test(timeout = 1000)
        public void loadProfiles1() throws IOException, ClassNotFoundException {
            Server s = new Server(new Socket()); //dummy server
            s.betterBookProfiles = new ArrayList<>();
            s.loadProfiles("forJUNITTests/loadProfilesTestOne.txt");
            Assert.assertTrue("ensure 'loadProfiles is loading profiles correctly",
                    s.betterBookProfiles.get(0).equals(new Profile("jebbush69", "jebisawesome", "Jeb Bush", "8005555555")));
        }

        @Test(timeout = 1000)
        public void loadProfiles2() throws IOException, ClassNotFoundException {
            Server s = new Server(new Socket()); //dummy server
            s.betterBookProfiles = null;
            try {
                s.loadProfiles("forJUNITTests/loadProfilesTestOne.txt");
                Assert.assertFalse("Incorrectly loading text file without betterBookProfiles instantiated.", true);
            } catch (Exception e) {
                Assert.assertTrue("Task failed successfully.", true);
            }

        }


    }
}