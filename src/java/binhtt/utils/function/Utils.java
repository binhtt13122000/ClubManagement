package binhtt.utils.function;

import java.util.Properties;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Utils {
    public static boolean sendMail(String[] userMails, String messageContent) {
        String myEmail = "binhttse140125@fpt.edu.vn";
        String password = "thanhbinhvllamnhe69";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            Address[] address = new Address[userMails.length];
            for(int i = 0; i < userMails.length; i++){
                address[i] = new InternetAddress(userMails[i]);
            }
            message.addRecipients(Message.RecipientType.TO, address);
            message.setSubject("Notification Fcode");
            message.setText(messageContent);
            Transport.send(message);
            return true;
        } catch (Exception e) {
            Logger.getLogger("Error at SendMail");
        }
        return false;
    }
}
