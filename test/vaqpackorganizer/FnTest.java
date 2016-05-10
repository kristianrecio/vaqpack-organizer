/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

import java.sql.Connection;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kristian Recio
 */
public class FnTest {
    
    public FnTest() {
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
     * Test of showError method, of class Fn.
     */
    @Test
    public void testShowError() {
        System.out.println("showError");
        Exception e = null;
        Fn.showError(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class Fn.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        Connection connection = null;
        Connection expResult = null;
        Connection result = Fn.get(connection);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTextField method, of class Fn.
     */
    @Test
    public void testSetTextField() {
        System.out.println("setTextField");
        double LayoutX = 0.0;
        double LayoutY = 0.0;
        String pText = "";
        TextField expResult = null;
        TextField result = Fn.setTextField(LayoutX, LayoutY, pText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPasswordField method, of class Fn.
     */
    @Test
    public void testSetPasswordField() {
        System.out.println("setPasswordField");
        double LayoutX = 0.0;
        double LayoutY = 0.0;
        String pText = "";
        PasswordField expResult = null;
        PasswordField result = Fn.setPasswordField(LayoutX, LayoutY, pText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setButton method, of class Fn.
     */
    @Test
    public void testSetButton() {
        System.out.println("setButton");
        double LayoutX = 0.0;
        double LayoutY = 0.0;
        String text = "";
        Button expResult = null;
        Button result = Fn.setButton(LayoutX, LayoutY, text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLabel method, of class Fn.
     */
    @Test
    public void testSetLabel() {
        System.out.println("setLabel");
        double LayoutX = 0.0;
        double LayoutY = 0.0;
        String text = "";
        boolean visible = false;
        String color = "";
        Label expResult = null;
        Label result = Fn.setLabel(LayoutX, LayoutY, text, visible, color);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMenuItem method, of class Fn.
     */
    @Test
    public void testSetMenuItem() {
        System.out.println("setMenuItem");
        String Text = "";
        MenuItem expResult = null;
        MenuItem result = Fn.setMenuItem(Text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
