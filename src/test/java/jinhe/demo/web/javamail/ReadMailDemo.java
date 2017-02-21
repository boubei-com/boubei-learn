///* ==================================================================   
// * Created [2006-6-2 16:51:25] by jinpujun 
// * ==================================================================  
// * LT 
// * ================================================================== 
// * Copyright (c) jinpj, 2006-2007 
// * ================================================================== 
// */
//
//package com.jinpj.web.javamail;
//
//import javax.mail.Address;
//import javax.mail.Folder;
//import javax.mail.Message;
//import javax..mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Store;
//
//public class ReadMailDemo {
//    public static void main(String args[]) {
//        int argc = args.length;
//
//        // Check for valid number of parameters
//        if (argc != 4) {
//            System.out.println("Syntax :");
//            System.out.println("java ReadMailDemo protocol host username password");
//            return;
//        }
//
//        String protocol = args[0];
//        String host = args[1];
//        String username = args[2];
//        String password = args[3];
//
//        try {
//            // Get a session, with default system properties
//            Session mySession = Session.getDefaultInstance(System
//                    .getProperties(), null);
//
//            // Get a specific mail store, such as imap/pop3/news
//            Store myStore = mySession.getStore(protocol);
//            myStore.connect(host, username, password);
//
//            // Request the INBOX folder for this mail store
//            Folder myFolder = myStore.getFolder("INBOX");
//
//            System.out.println("Accessing mail account now");
//
//            // Open in READ_ONLY mode
//            myFolder.open(Folder.READ_ONLY);
//
//            int messagecount = myFolder.getMessageCount();
//            System.out.println(myFolder.getFullName() + " has " + messagecount
//                    + " messages.");
//
//            Message[] message = myFolder.getMessages(1, messagecount);
//
//            for (int i = 0; i < message.length; i++) {
//                Address[] fromAddr = message[i].getFrom();
//                System.out.println(fromAddr[0] + ":" + message[i].getSubject());
//            }
//
//            // Close messages, don't expunge
//            myFolder.close(false);
//            // Close connection to store
//            myStore.close();
//        } catch (MessagingException me) {
//            System.err.println("Messaging failure : " + me);
//        } catch (Exception ex) {
//            System.err.println("Failure : " + ex);
//        }
//    }
//}
