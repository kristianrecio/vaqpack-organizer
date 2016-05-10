/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

import javafx.stage.Stage;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kristian Recio
 */
public class Register_FXTest {
    
    public Register_FXTest() {
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
     * Test of start method, of class Register_FX.
     */
    @Test
    public void testStart() {
        System.out.println("start");
        Stage primaryStage = null;
        Register_FX instance = null;
        instance.start(primaryStage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startBsubmit method, of class Register_FX.
     */
    @Test
    public void testStartBsubmit() {
        System.out.println("startBsubmit");
        Register_FX instance = null;
        instance.startBsubmit();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkErrors method, of class Register_FX.
     */
    @Test
    public void testCheckErrors() {
        System.out.println("checkErrors");
        String name = "";
        String pass = "";
        String confirm = "";
        Register_FX instance = null;
        boolean expResult = false;
        boolean result = instance.checkErrors(name, pass, confirm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
