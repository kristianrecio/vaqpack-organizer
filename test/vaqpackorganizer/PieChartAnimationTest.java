/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kristian Recio
 */
public class PieChartAnimationTest {
    
    public PieChartAnimationTest() {
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
     * Test of setChart method, of class PieChartAnimation.
     */
    @Test
    public void testSetChart() {
        System.out.println("setChart");
        PieChartAnimation instance = new PieChartAnimation();
        instance.setChart();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setData method, of class PieChartAnimation.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
        PieChartAnimation instance = new PieChartAnimation();
        ObservableList<PieChart.Data> expResult = null;
        ObservableList<PieChart.Data> result = instance.setData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePieChart method, of class PieChartAnimation.
     */
    @Test
    public void testUpdatePieChart() {
        System.out.println("updatePieChart");
        PieChartAnimation instance = new PieChartAnimation();
        instance.updatePieChart();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChart method, of class PieChartAnimation.
     */
    @Test
    public void testGetChart() {
        System.out.println("getChart");
        PieChartAnimation instance = new PieChartAnimation();
        PieChart expResult = null;
        PieChart result = instance.getChart();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
