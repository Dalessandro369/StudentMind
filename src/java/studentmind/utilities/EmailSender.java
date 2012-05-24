/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.utilities;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ProjetJava
 */
public class EmailSender {
    public EmailSender(String emailDestinataire, String emailExpediteur, String objetMessage, String contenuMessage) {
        
        final String username = "contact.studentmind@gmail.com";
        final String password = "studentmind369";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                }
            });

        try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(emailDestinataire));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(emailExpediteur));
                message.setSubject(objetMessage);
                message.setText(contenuMessage);
                
                Transport.send(message);


        } catch (MessagingException e) {
                throw new RuntimeException(e);
        }
    }
}
