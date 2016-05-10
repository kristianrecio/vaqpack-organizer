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
public class ScheduleTest {
    
    public ScheduleTest() {
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
     * Test of generateCourseSchedule method, of class Schedule.
     */
    @Test
    public void testGenerateCourseSchedule() {
        System.out.println("generateCourseSchedule");
        Schedule instance = new Schedule();
        instance.generateCourseSchedule();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateEventSchedule method, of class Schedule.
     */
    @Test
    public void testGenerateEventSchedule() {
        System.out.println("generateEventSchedule");
        Schedule instance = new Schedule();
        instance.generateEventSchedule();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializeCoursesPlace method, of class Schedule.
     */
    @Test
    public void testInitializeCoursesPlace() {
        System.out.println("initializeCoursesPlace");
        Schedule instance = new Schedule();
        instance.initializeCoursesPlace();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializeEventsPlace method, of class Schedule.
     */
    @Test
    public void testInitializeEventsPlace() {
        System.out.println("initializeEventsPlace");
        Schedule instance = new Schedule();
        instance.initializeEventsPlace();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of timeConflictAlert method, of class Schedule.
     */
    @Test
    public void testTimeConflictAlert() {
        System.out.println("timeConflictAlert");
        int type = 0;
        Schedule instance = new Schedule();
        instance.timeConflictAlert(type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isThereCourseTimeConflict method, of class Schedule.
     */
    @Test
    public void testIsThereCourseTimeConflict() {
        System.out.println("isThereCourseTimeConflict");
        int course = 0;
        Schedule instance = new Schedule();
        boolean expResult = false;
        boolean result = instance.isThereCourseTimeConflict(course);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of courseTimeConflictDayCheck method, of class Schedule.
     */
    @Test
    public void testCourseTimeConflictDayCheck() {
        System.out.println("courseTimeConflictDayCheck");
        Course course1 = null;
        Course course2 = null;
        Schedule instance = new Schedule();
        boolean expResult = false;
        boolean result = instance.courseTimeConflictDayCheck(course1, course2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isThereEventTimeConflict method, of class Schedule.
     */
    @Test
    public void testIsThereEventTimeConflict() {
        System.out.println("isThereEventTimeConflict");
        int event = 0;
        Schedule instance = new Schedule();
        boolean expResult = false;
        boolean result = instance.isThereEventTimeConflict(event);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isThereEventCourseConflict method, of class Schedule.
     */
    @Test
    public void testIsThereEventCourseConflict() {
        System.out.println("isThereEventCourseConflict");
        int eventNum = 0;
        Schedule instance = new Schedule();
        boolean expResult = false;
        boolean result = instance.isThereEventCourseConflict(eventNum);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of courseEventConflictDayCheck method, of class Schedule.
     */
    @Test
    public void testCourseEventConflictDayCheck() {
        System.out.println("courseEventConflictDayCheck");
        Event event = null;
        Course course = null;
        Schedule instance = new Schedule();
        boolean expResult = false;
        boolean result = instance.courseEventConflictDayCheck(event, course);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCoursesPlace method, of class Schedule.
     */
    @Test
    public void testSetCoursesPlace_0args() {
        System.out.println("setCoursesPlace");
        Schedule instance = new Schedule();
        instance.setCoursesPlace();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEventsPlace method, of class Schedule.
     */
    @Test
    public void testSetEventsPlace_0args() {
        System.out.println("setEventsPlace");
        Schedule instance = new Schedule();
        instance.setEventsPlace();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateTimeTicks method, of class Schedule.
     */
    @Test
    public void testGenerateTimeTicks() {
        System.out.println("generateTimeTicks");
        Schedule instance = new Schedule();
        instance.generateTimeTicks();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeIncrement method, of class Schedule.
     */
    @Test
    public void testGetTimeIncrement() {
        System.out.println("getTimeIncrement");
        Schedule instance = new Schedule();
        int expResult = 0;
        int result = instance.getTimeIncrement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeIntervals method, of class Schedule.
     */
    @Test
    public void testGetTimeIntervals() {
        System.out.println("getTimeIntervals");
        Schedule instance = new Schedule();
        String[] expResult = null;
        String[] result = instance.getTimeIntervals();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTimeIntervals method, of class Schedule.
     */
    @Test
    public void testSetTimeIntervals() {
        System.out.println("setTimeIntervals");
        String[] timeIntervals = null;
        Schedule instance = new Schedule();
        instance.setTimeIntervals(timeIntervals);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoursesPlace method, of class Schedule.
     */
    @Test
    public void testGetCoursesPlace() {
        System.out.println("getCoursesPlace");
        Schedule instance = new Schedule();
        int[][] expResult = null;
        int[][] result = instance.getCoursesPlace();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCoursesPlace method, of class Schedule.
     */
    @Test
    public void testSetCoursesPlace_intArrArr() {
        System.out.println("setCoursesPlace");
        int[][] coursesPlace = null;
        Schedule instance = new Schedule();
        instance.setCoursesPlace(coursesPlace);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEventsPlace method, of class Schedule.
     */
    @Test
    public void testGetEventsPlace() {
        System.out.println("getEventsPlace");
        Schedule instance = new Schedule();
        int[][] expResult = null;
        int[][] result = instance.getEventsPlace();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEventsPlace method, of class Schedule.
     */
    @Test
    public void testSetEventsPlace_intArrArr() {
        System.out.println("setEventsPlace");
        int[][] eventsPlace = null;
        Schedule instance = new Schedule();
        instance.setEventsPlace(eventsPlace);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEvents method, of class Schedule.
     */
    @Test
    public void testGetEvents() {
        System.out.println("getEvents");
        Schedule instance = new Schedule();
        ArrayList<Event> expResult = null;
        ArrayList<Event> result = instance.getEvents();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEvents method, of class Schedule.
     */
    @Test
    public void testSetEvents() {
        System.out.println("setEvents");
        ArrayList<Event> events = null;
        Schedule instance = new Schedule();
        instance.setEvents(events);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
