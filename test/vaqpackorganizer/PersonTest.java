/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kristian Recio
 */
public class PersonTest {
    
    public PersonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of generateCourses method, of class Person.
     */
    @Test
    public void testGenerateCourses() {
        System.out.println("generateCourses");
        Person instance = null;
        instance.generateCourses();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateEvents method, of class Person.
     */
    @Test
    public void testGenerateEvents() {
        System.out.println("generateEvents");
        Person instance = null;
        instance.generateEvents();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserId method, of class Person.
     */
    @Test
    public void testGetUserId() {
        System.out.println("getUserId");
        Person instance = null;
        int expResult = 0;
        int result = instance.getUserId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Person.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Person instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Person.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Person instance = null;
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId_num method, of class Person.
     */
    @Test
    public void testGetId_num() {
        System.out.println("getId_num");
        Person instance = null;
        String expResult = "";
        String result = instance.getId_num();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId_num method, of class Person.
     */
    @Test
    public void testSetId_num() {
        System.out.println("setId_num");
        String id_num = "";
        Person instance = null;
        instance.setId_num(id_num);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSemester method, of class Person.
     */
    @Test
    public void testGetSemester() {
        System.out.println("getSemester");
        Person instance = null;
        String expResult = "";
        String result = instance.getSemester();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSemester method, of class Person.
     */
    @Test
    public void testSetSemester() {
        System.out.println("setSemester");
        String semester = "";
        Person instance = null;
        instance.setSemester(semester);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCourses method, of class Person.
     */
    @Test
    public void testGetCourses() {
        System.out.println("getCourses");
        Person instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getCourses();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCourses method, of class Person.
     */
    @Test
    public void testSetCourses() {
        System.out.println("setCourses");
        ArrayList<Course> courses = null;
        Person instance = null;
        instance.setCourses(courses);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEvents method, of class Person.
     */
    @Test
    public void testGetEvents() {
        System.out.println("getEvents");
        Person instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getEvents();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEvents method, of class Person.
     */
    @Test
    public void testSetEvents() {
        System.out.println("setEvents");
        ArrayList<Event> events = null;
        Person instance = null;
        instance.setEvents(events);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class Person.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Person instance = null;
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmail method, of class Person.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        Person instance = null;
        instance.setEmail(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMajor method, of class Person.
     */
    @Test
    public void testGetMajor() {
        System.out.println("getMajor");
        Person instance = null;
        String expResult = "";
        String result = instance.getMajor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMajor method, of class Person.
     */
    @Test
    public void testSetMajor() {
        System.out.println("setMajor");
        int major_id = 0;
        Person instance = null;
        instance.setMajor(major_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class Person.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Person instance = null;
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class Person.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String oldpass = "";
        String newpass = "";
        String confirm = "";
        Person instance = null;
        instance.setPassword(oldpass, newpass, confirm);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsername method, of class Person.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        Person instance = null;
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsername method, of class Person.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "";
        Person instance = null;
        instance.setUsername(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProfile_url method, of class Person.
     */
    @Test
    public void testGetProfile_url() {
        System.out.println("getProfile_url");
        Person instance = null;
        String expResult = "";
        String result = instance.getProfile_url();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProfile_url method, of class Person.
     */
    @Test
    public void testSetProfile_url() {
        System.out.println("setProfile_url");
        String profile_url = "";
        Person instance = null;
        instance.setProfile_url(profile_url);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
