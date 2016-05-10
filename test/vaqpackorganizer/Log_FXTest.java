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
public class Log_FXTest {
    
    public Log_FXTest() {
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
     * Test of start method, of class Log_FX.
     */
    @Test
    public void testStart() {
        System.out.println("start");
        Stage primaryStage = null;
        Log_FX instance = new Log_FX();
        instance.start(primaryStage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startBlog method, of class Log_FX.
     */
    @Test
    public void testStartBlog() {
        System.out.println("startBlog");
        Log_FX instance = new Log_FX();
        instance.startBlog();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startBregister method, of class Log_FX.
     */
    @Test
    public void testStartBregister() {
        System.out.println("startBregister");
        Log_FX instance = new Log_FX();
        instance.startBregister();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Log_FX.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Log_FX.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
