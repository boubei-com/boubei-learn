//package jinhe.lt.web.javamail;
//
///**
// * <p>
// * SendAttachment.java
// * </p>
// * 
// * @author Jon.King 2006-6-2
// * 
// */
//import java.io.File;
//import java.util.Properties;
//
//import javax.activation.DataHandler;
//import javax.activation.FileDataSource;
//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//
//public class SendAttachment {
//    public static void main(String args[]) {
//        int argc = args.length;
//
//        // Check for valid number of parameters
//        if (argc != 3) {
//            System.out.println("Syntax :");
//            System.out
//                    .println("java SendAttachment smtphost to_address filepath");
//            return;
//        }
//
//        String host = args[0];
//        String to = args[1];
//        String file = args[2];
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
//            System.out.println("Composing message");
//
//            // Create a message to send, specifying our session
//            Message message = new MimeMessage(mySession);
//            message.setSubject("File attachment file");
//
//            // Create an InternetAddress, for specifying recipient
//            InternetAddress toAddr = new InternetAddress(to);
//            message.setRecipient(Message.RecipientType.TO, toAddr);
//
//            // Use the same sender address as recipient
//            message.setFrom(toAddr);
//
//            // Construct a multipart message
//            Multipart part = new MimeMultipart();
//
//            // Create the message body
//            BodyPart msgBody = new MimeBodyPart();
//            msgBody.setText("There is a file attached to this message....");
//
//            // Create the message attachment
//            BodyPart attachment = new MimeBodyPart();
//
//            // Use a file data source for reading the file
//            FileDataSource fileDataSource = new FileDataSource(file);
//
//            // Set the appropriate data handler
//            attachment.setDataHandler(new DataHandler(fileDataSource));
//
//            // Set the filename of the file (don't include path info)
//            if (file.indexOf(File.separator) != -1) {
//                String fileName = file.substring(file
//                        .lastIndexOf(File.separator) + 1, file.length());
//                attachment.setFileName(fileName);
//            } else {
//                attachment.setFileName(file);
//            }
//
//            System.out.println("Adding attachments");
//
//            // Add msg body and attachment to multipart message
//            part.addBodyPart(msgBody);
//            part.addBodyPart(attachment);
//
//            // Set our multipart message as the msg contents
//            message.setContent(part);
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
