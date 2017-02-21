//package com.jinpj.web.javamail;
//
///**
// * <p>
// * ProviderList.java
// * </p>
// * 
// * @author Jon.King 2006-6-2
// * 
// */
//import javax.mail.Provider;
//import javax.mail.Session;
//
//public class ProviderList {
//    public static void main(String args[]) throws Exception {
//        // Get a session, with default system properties
//        Session mySession = Session.getDefaultInstance(System.getProperties(),
//                null);
//
//        // Get a list of available providers
//        Provider[] providerList = mySession.getProviders();
//
//        // Look at each provider
//        for (int i = 0; i < providerList.length; i++) {
//            // Print out protocol name
//            System.out.println("Protocol : " + providerList[i].getProtocol());
//
//            // Print type (store or transport)
//            if (providerList[i].getType() == Provider.Type.STORE)
//                System.out.println("Provider type : STORE");
//            else
//                System.out.println("Provider type : TRANSPORT");
//
//            // Print out vendor name
//            System.out.println("Vendor : " + providerList[i].getVendor());
//            System.out.println("--");
//        }
//    }
//}
