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
public class MajorTest {
    
    public MajorTest() {
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
     * Test of getPList method, of class Major.
     */
    @Test
    public void testGetPList() {
        System.out.println("getPList");
        Major instance = null;
        ArrayList<Professor> expResult = null;
        ArrayList<Professor> result = instance.getPList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEList method, of class Major.
     */
    @Test
    public void testGetEList() {
        System.out.println("getEList");
        Major instance = null;
        ArrayList<Emergency_contact> expResult = null;
        ArrayList<Emergency_contact> result = instance.getEList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEList method, of class Major.
     */
    @Test
    public void testSetEList() {
        System.out.println("setEList");
        Major instance = null;
        instance.setEList();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Major.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Major instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPhone method, of class Major.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        Major instance = null;
        String expResult = "";
        String result = instance.getPhone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class Major.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Major instance = null;
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUrl method, of class Major.
     */
    @Test
    public void testGetUrl() {
        System.out.println("getUrl");
        Major instance = null;
        String expResult = "";
        String result = instance.getUrl();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMajor_id method, of class Major.
     */
    @Test
    public void testGetMajor_id() {
        System.out.println("getMajor_id");
        Major instance = null;
        int expResult = 0;
        int result = instance.getMajor_id();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
