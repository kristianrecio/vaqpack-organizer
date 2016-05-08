/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpackorganizer;



import java.util.Properties;
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
    
    String messageText;//needs to get data from database to be sent via e-mail
    
    public class GMailAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            String user = "vaqpackdonotreply@gmail.com";
            String pwd = "Ryobmujg1";
            return new PasswordAuthentication(user, pwd);
        }
    }
    
    public void mailSender() {
        try{
            new SendEMail().sendSimpleMail("Event Reminder", Main_FX.person.getEmail(),
                    "vaqpackdonotreply@gmail.com", messageText);
            
        }catch (Throwable e) {
            e.printStackTrace();
        } 
    }
    
    public void sendSimpleMail(String subject, String to,
        String from, String messageText) 
        throws AddressException, MessagingException {
        
        Properties mailProps = new Properties();
        
        mailProps.put("mail.transport.protocol", "smtp");
        mailProps.put("mail.starttls.enable", "true");
        mailProps.put("mail.smtp.host", "smtp.gmail.com");
        mailProps.put("mail.smtp.auth", "true");
        mailProps.put("mail.smtp.user", "username@gmail.com");
        mailProps.put("mail.debug", "true");
        mailProps.put("mail.smtp.report", "465");
        mailProps.put("mail.mime.charset", "ISO-8859-1");
        mailProps.put("mail.stmp.socketFactory.port", "465");
        mailProps.put("mail.stmp.socketFactory.fallback", "false");
        mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SLLSocketFactory");
            
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
            
        message.setContent(multipart);
           
        Transport.send(message);
    }
}