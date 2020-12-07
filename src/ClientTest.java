import org.junit.Assert;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.*;
import java.net.ServerSocket;
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
 * A class to test Client and all of its
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
 * tests methods getBetterBookProfiles, signIn
 */
public class ClientTest {
    
    public static void main(String[] args) {
        
        Result result = JUnitCore.runClasses(TestCase.class);
        
        if (result.wasSuccessful()) {
            System.out.println("All tests ran successfully.");
        } else {
            System.out.println("Incoming failures... sad");
            for (Failure f : result.getFailures()) {
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
            clazz = Client.class;
            className = "Client";
            
            // Perform tests
            
            modifiers = clazz.getModifiers();
            
            superclass = clazz.getSuperclass();
            
            superinterfaces = clazz.getInterfaces();
            
            Assert.assertTrue("Ensure that `" + className + "` is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "` is NOT `abstract`!", Modifier.isAbstract(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "` extends `Object`!", Object.class, superclass);
            
            Assert.assertEquals("Ensure that `" + className + "` doesn't implement an interface!", 0, superinterfaces.length);
        }
        
        // All fields exist with correct type and access modifier
        @Test(timeout = 1_000)
        public void sockFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Client";
            Field testField;
            int modifiers;
            Class<?> type;
            
            // Set the field that you want to test
            String fieldName = "sock";
            
            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = Socket.class;
            
            // Set the class being tested
            clazz = Client.class;
            
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
            String className = "Client";
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
            clazz = Client.class;
            
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
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is not `static`!", Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the correct type!", expectedType, type);
        }
        
        // Verifying if each method exists and has the correct return type + access modifier
        @Test(timeout = 1000)
        public void clientSignInMethodTest() {
            Class<?> clazz;
            String className = "Client";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 1;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "signIn";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = Profile.class;
            
            // Set the class being tested
            clazz = Client.class;
            
            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, String.class, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 2 String parameters!");
                
                return;
            } //end try catch
            
            // Perform tests
            
            modifiers = method.getModifiers();
            
            actualReturnType = method.getReturnType();
            
            exceptions = method.getExceptionTypes();
            
            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!",
                    Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 1 `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void clientGetBetterBookProfilesMethodTest() {
            Class<?> clazz;
            String className = "Client";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;
            
            // Set the method that you want to test
            String methodName = "getBetterBookProfiles";
            
            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = ArrayList.class;
            
            // Set the class being tested
            clazz = Client.class;
            
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
            
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is not `static`!",
                    Modifier.isStatic(modifiers));
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has 0 `throws` clause!", expectedLength, exceptions.length);
        }
        
    }
}