/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

import javafx.scene.layout.AnchorPane;
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
public class Main_FXTest {
    
    public Main_FXTest() {
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
     * Test of start method, of class Main_FX.
     */
    @Test
    public void testStart() {
        System.out.println("start");
        Stage primaryStage = null;
        Main_FX instance = null;
        instance.start(primaryStage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startMIout method, of class Main_FX.
     */
    @Test
    public void testStartMIout() {
        System.out.println("startMIout");
        Stage Current = null;
        Main_FX instance = null;
        instance.startMIout(Current);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMain method, of class Main_FX.
     */
    @Test
    public void testSetMain() {
        System.out.println("setMain");
        Main_FX instance = null;
        AnchorPane expResult = null;
        AnchorPane result = instance.setMain();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
