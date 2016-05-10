/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Roberto
 */
public class SendEMail { 
    
    
    
    public class GMailAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            String user = "vaqpackdonotreply@gmail.com";
            String pwd = "Ryobmujg1";
            return new PasswordAuthentication(user, pwd);
        }
    }
    
    public void sendSimpleMail(String subject, String to,
        String from, String messageText, String attachmentPath) 
        throws AddressException, MessagingException {
        
        Properties mailProps = new Properties();
        
        mailProps.put("mail.transport.protocol", "smtp");
        mailProps.put("mail.smtp.starttls.enable", "true");
        mailProps.put("mail.smtp.host", "smtp.gmail.com");
        mailProps.put("mail.stmp.port", "587");
        mailProps.put("mail.smtp.auth", "true");
        mailProps.put("mail.user", from);
        mailProps.put("mail.password", "Ryobmujg1");
            
        Authenticator auth = new GMailAuthenticator();
        Session session = Session.getDefaultInstance(mailProps, auth);
            
        
        Message message = new MimeMessage(session);
            
        message.setFrom(new InternetAddress(from));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
            
        //prepare the message
        BodyPart messageBodyPart = new MimeBodyPart();
        message.setContent(messageText, "text/html");
        messageBodyPart.setContent(messageText, "text/html");
            
        //container for all parts
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        
        addAttachment(multipart, attachmentPath);
        message.setContent(multipart);
        
        Transport.send(message);
    }
    
    private void addAttachment(Multipart multipart, String attachmentPath)
            throws MessagingException {
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachmentPath);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(new File(attachmentPath).getName());
            multipart.addBodyPart(attachmentPart);
        }
    
    public void writeHTMLFiles(String email, ArrayList<Event> events) {
        try {
            File htmlFile = new File("template.html");
            FileOutputStream stream = new FileOutputStream(htmlFile, false);
            
            String fileContents = "<html><p>Events:\n\n</p></html>";
            
            for (int i = 0; i < events.size(); i++)
                fileContents += events.get(i).getEventInfo() + "\n\n";
            
            byte[] myBytes = fileContents.getBytes();

            stream.write(myBytes);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try{
            new SendEMail().sendSimpleMail("Event Reminder", Main_FX.person.getEmail(),
                    "vaqpackdonotreply@gmail.com", "you have an event!", "template.html");
            
        }catch (Throwable e) {
            e.printStackTrace();
        } 
        
    }
    
    public void writeTextFiles(String email, ArrayList<Event> events) {
        try {
            File textFile = new File("Events.txt");
            FileOutputStream stream = new FileOutputStream(textFile, false);
            
            String fileContents = "Events:\n\n";
            
            for (int i = 0; i < events.size(); i++)
                fileContents += events.get(i).getEventInfo() + "\n\n";
            
            byte[] myBytes = fileContents.getBytes();

            stream.write(myBytes);
            stream.close();
        }catch (IOException e) {
            Fn.showError(e);
        }
        
        try{
            sendSimpleMail("Event Reminder", email,
                    "vaqpackdonotreply@gmail.com", "Your events are in the attached file.", "Reminder.txt"); 
            
        }catch (Throwable e) {
            e.printStackTrace();
        } 
        
    }
}
