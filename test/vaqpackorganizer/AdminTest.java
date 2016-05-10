/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

import javafx.scene.control.Tab;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kristian Recio
 */
public class AdminTest {
    
    public AdminTest() {
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
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setTab method, of class Admin.
     */
    @org.junit.Test
    public void testSetTab() {
        System.out.println("setTab");
        Admin instance = new Admin();
        instance.setTab();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class Admin.
     */
    @org.junit.Test
    public void testDelete() {
        System.out.println("delete");
        String username = "";
        Admin instance = new Admin();
        instance.delete(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of refresh method, of class Admin.
     */
    @org.junit.Test
    public void testRefresh() {
        System.out.println("refresh");
        Admin instance = new Admin();
        instance.refresh();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getTab method, of class Admin.
     */
    @org.junit.Test
    public void testGetTab() {
        System.out.println("getTab");
        Admin instance = new Admin();
        Tab expResult = null;
        Tab result = instance.getTab();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
