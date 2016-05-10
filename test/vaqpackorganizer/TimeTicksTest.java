/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kristian Recio
 */
public class TimeTicksTest {
    
    public TimeTicksTest() {
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
     * Test of generateTicks method, of class TimeTicks.
     */
    @Test
    public void testGenerateTicks() {
        System.out.println("generateTicks");
        TimeTicks instance = new TimeTicks();
        String[] expResult = null;
        String[] result = instance.generateTicks();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printTicks method, of class TimeTicks.
     */
    @Test
    public void testPrintTicks() {
        System.out.println("printTicks");
        TimeTicks instance = new TimeTicks();
        instance.printTicks();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTime_from method, of class TimeTicks.
     */
    @Test
    public void testGetTime_from() {
        System.out.println("getTime_from");
        TimeTicks instance = new TimeTicks();
        int expResult = 0;
        int result = instance.getTime_from();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTime_from method, of class TimeTicks.
     */
    @Test
    public void testSetTime_from() {
        System.out.println("setTime_from");
        int time_from = 0;
        TimeTicks instance = new TimeTicks();
        instance.setTime_from(time_from);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTime_to method, of class TimeTicks.
     */
    @Test
    public void testGetTime_to() {
        System.out.println("getTime_to");
        TimeTicks instance = new TimeTicks();
        int expResult = 0;
        int result = instance.getTime_to();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTime_to method, of class TimeTicks.
     */
    @Test
    public void testSetTime_to() {
        System.out.println("setTime_to");
        int time_to = 0;
        TimeTicks instance = new TimeTicks();
        instance.setTime_to(time_to);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeIncrement method, of class TimeTicks.
     */
    @Test
    public void testGetTimeIncrement() {
        System.out.println("getTimeIncrement");
        TimeTicks instance = new TimeTicks();
        int expResult = 0;
        int result = instance.getTimeIncrement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTimeIncrement method, of class TimeTicks.
     */
    @Test
    public void testSetTimeIncrement() {
        System.out.println("setTimeIncrement");
        int timeIncrement = 0;
        TimeTicks instance = new TimeTicks();
        instance.setTimeIncrement(timeIncrement);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeTicksStrings method, of class TimeTicks.
     */
    @Test
    public void testGetTimeTicksStrings() {
        System.out.println("getTimeTicksStrings");
        TimeTicks instance = new TimeTicks();
        String[] expResult = null;
        String[] result = instance.getTimeTicksStrings();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTimeTicksStrings method, of class TimeTicks.
     */
    @Test
    public void testSetTimeTicksStrings() {
        System.out.println("setTimeTicksStrings");
        String[] timeTicksStrings = null;
        TimeTicks instance = new TimeTicks();
        instance.setTimeTicksStrings(timeTicksStrings);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
