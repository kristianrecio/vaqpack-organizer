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
public class SendEMailTest {
    
    public SendEMailTest() {
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
     * Test of sendSimpleMail method, of class SendEMail.
     */
    @Test
    public void testSendSimpleMail() throws Exception {
        System.out.println("sendSimpleMail");
        String subject = "";
        String to = "";
        String from = "";
        String messageText = "";
        String attachmentPath = "";
        SendEMail instance = new SendEMail();
        instance.sendSimpleMail(subject, to, from, messageText, attachmentPath);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeHTMLFiles method, of class SendEMail.
     */
    @Test
    public void testWriteHTMLFiles() {
        System.out.println("writeHTMLFiles");
        SendEMail instance = new SendEMail();
        instance.writeHTMLFiles();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeTextFiles method, of class SendEMail.
     */
    @Test
    public void testWriteTextFiles() {
        System.out.println("writeTextFiles");
        String email = "";
        ArrayList<Event> events = null;
        SendEMail instance = new SendEMail();
        instance.writeTextFiles(email, events);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
