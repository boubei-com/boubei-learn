//package jinhe.lt.web.javamail;
//
///**
// * <p>
// * SendEventDemo.java
// * </p>
// * 
// * @author Jon.King 2006-6-2
// * 
// */
//import java.util.Properties;
//
//import javax.mail.Address;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.event.ConnectionEvent;
//import javax.mail.event.ConnectionListener;
//import javax.mail.event.TransportAdapter;
//import javax.mail.event.TransportEvent;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//public class SendEventDemo {
//    public static void main(String args[]) {
//        int argc = args.length;
//
//        // Check for valid number of parameters
//        if (argc != 2) {
//            System.out.println("Syntax :");
//            System.out.println("java SendEventMailDemo smtphost to_address");
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
//                    "nobody@nowhere.com", "SendEventMailDemo");
//            message.setFrom(fromAddr);
//
//            // Get a transport instance
//            Transport transport = mySession.getTransport(toAddr);
//
//            // Create an anonymous inner class for connection listener
//            transport.addConnectionListener(new ConnectionListener() {
//                public void opened(ConnectionEvent e) {
//                    System.out.println("connection opened");
//                }
//
//                public void disconnected(ConnectionEvent e) {
//                    System.out.println("connection disconnected");
//                }
//
//                public void closed(ConnectionEvent e) {
//                    System.out.println("connection closed");
//                }
//            });
//
//            // Create an anonymous inner class for transport listener
//            transport.addTransportListener(new TransportAdapter() {
//                public void messageDelivered(TransportEvent e) {
//                    System.out.println("Message delivered");
//                }
//
//                public void messageNotDelivered(TransportEvent e) {
//                    System.out.println("Message not delivered");
//                }
//            });
//
//            // Open the connection
//            transport.connect();
//
//            System.out.println("Attempting to send message");
//
//            // Send the message
//            Address[] msgAddr = { toAddr };
//            transport.sendMessage(message, msgAddr);
//
//            // Close the connection
//            transport.close();
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
