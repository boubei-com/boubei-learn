//package jinhe.lt.web.javamail;
//
///**
// * <p>
// * SendMailDemo.java
// * </p>
// * 
// * @author Jon.King 2006-6-2
// * 
// */
//
//import java.util.Properties;
//
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//public class SendMailDemo {
//    public static void main(String args[]) {
//        int argc = args.length;
//
//        // Check for valid number of parameters
//        if (argc != 2) {
//            System.out.println("Syntax :");
//            System.out.println("java SendMailDemo smtphost to_address");
//            return;
//        }
//
//        String host = args[0];
//        String to = args[1];
//
//        // Create a properties file, specifying mail settings
//        Properties prop = new Properties();
//        prop.put("mail.transport.default", "smtp");
//        prop.put("mail.smtp.host", host);
//
//        try {
//            // Get a session, with the specified properties
//            Session mySession = Session.getInstance(prop, null);
//
//            // Create a message to send, specifying our session
//            Message message = new MimeMessage(mySession);
//            message.setSubject("Test message");
//            message.setContent("This is a test message....", "text/plain");
//
//            // Create an InternetAddress, for specifying recipient
//            InternetAddress toAddr = new InternetAddress(to);
//            message.setRecipient(Message.RecipientType.TO, toAddr);
//
//            // Create an InternetAddress, for specifying sender address
//            InternetAddress fromAddr = new InternetAddress(
//                    "nobody@nowhere.com", "SendMailDemo");
//            message.setFrom(fromAddr);
//
//            System.out.println("Sending message");
//
//            // Send the message
//            Transport.send(message);
//
//            System.out.println("Message sent");
//        } catch (AddressException ae) {
//            System.err.println("Invalid address " + ae);
//        } catch (MessagingException me) {
//            System.err.println("Messaging failure : " + me);
//        } catch (Exception ex) {
//            System.err.println("Failure : " + ex);
//        }
//
//    }
//}
