/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.emailSender;

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
    public EmailSender(String emailDestinataire, String objetMessage, String contenuMessage) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("contact.studentmind","studentmind369");
                        }
                });

        try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("contact.studentmind@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(emailDestinataire));
                message.setSubject(objetMessage);
                message.setText(contenuMessage);
                        
                Transport.send(message);

        } catch (MessagingException e) {
                throw new RuntimeException(e);
        }
    }
}
