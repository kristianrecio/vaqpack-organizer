/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;

import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kristian Recio
 */
public class DatabaseTest {
    
    public DatabaseTest() {
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
     * Test of deleteRecord method, of class Database.
     */
    @Test
    public void testDeleteRecord() {
        System.out.println("deleteRecord");
        String table = "";
        int id = 0;
        Database instance = null;
        instance.deleteRecord(table, id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertContact method, of class Database.
     */
    @Test
    public void testInsertContact() {
        System.out.println("insertContact");
        int user_id = 0;
        String name = "";
        String phone = "";
        String email = "";
        String address = "";
        Database instance = null;
        instance.insertContact(user_id, name, phone, email, address);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class Database.
     */
    @Test
    public void testGet_String() {
        System.out.println("get");
        String table = "";
        Database instance = null;
        ResultSet expResult = null;
        ResultSet result = instance.get(table);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class Database.
     */
    @Test
    public void testGet_String_int() {
        System.out.println("get");
        String table = "";
        int id = 0;
        Database instance = null;
        ResultSet expResult = null;
        ResultSet result = instance.get(table, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItems method, of class Database.
     */
    @Test
    public void testGetItems_3args() {
        System.out.println("getItems");
        ObservableList<String> items = null;
        String table = "";
        String column = "";
        Database instance = null;
        instance.getItems(items, table, column);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItems method, of class Database.
     */
    @Test
    public void testGetItems_5args() {
        System.out.println("getItems");
        ObservableList<String> items = null;
        String table = "";
        String column = "";
        String parameter = "";
        int id = 0;
        Database instance = null;
        instance.getItems(items, table, column, parameter, id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInt method, of class Database.
     */
    @Test
    public void testGetInt() {
        System.out.println("getInt");
        String table = "";
        int id = 0;
        String parameter = "";
        Database instance = null;
        int expResult = 0;
        int result = instance.getInt(table, id, parameter);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getString method, of class Database.
     */
    @Test
    public void testGetString() {
        System.out.println("getString");
        String table = "";
        int id = 0;
        String parameter = "";
        Database instance = null;
        String expResult = "";
        String result = instance.getString(table, id, parameter);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getID method, of class Database.
     */
    @Test
    public void testGetID_3args_1() {
        System.out.println("getID");
        String table = "";
        String column = "";
        int selection = 0;
        Database instance = null;
        int expResult = 0;
        int result = instance.getID(table, column, selection);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateProfessorList method, of class Database.
     */
    @Test
    public void testGenerateProfessorList() {
        System.out.println("generateProfessorList");
        ArrayList<Professor> professors = null;
        int major_id = 0;
        Database instance = null;
        instance.generateProfessorList(professors, major_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateEmergencyList method, of class Database.
     */
    @Test
    public void testGenerateEmergencyList() {
        System.out.println("generateEmergencyList");
        ArrayList<Emergency_contact> emergency = null;
        int user_id = 0;
        Database instance = null;
        instance.generateEmergencyList(emergency, user_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifyString method, of class Database.
     */
    @Test
    public void testModifyString() {
        System.out.println("modifyString");
        int inv_id = 0;
        String table = "";
        String Parameter = "";
        String value = "";
        Database instance = null;
        instance.modifyString(inv_id, table, Parameter, value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifyInt method, of class Database.
     */
    @Test
    public void testModifyInt() {
        System.out.println("modifyInt");
        int id = 0;
        String table = "";
        String Parameter = "";
        int value = 0;
        Database instance = null;
        instance.modifyInt(id, table, Parameter, value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getID method, of class Database.
     */
    @Test
    public void testGetID_3args_2() {
        System.out.println("getID");
        String table = "";
        String column = "";
        String selection = "";
        Database instance = null;
        int expResult = 0;
        int result = instance.getID(table, column, selection);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addEvent method, of class Database.
     */
    @Test
    public void testAddEvent() {
        System.out.println("addEvent");
        Event event = null;
        Database instance = null;
        instance.addEvent(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteUser method, of class Database.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        String username = "";
        Database instance = null;
        instance.deleteUser(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
