/* ==================================================================   
 * Created [2006-6-1 11:28:59] by jinpujun 
 * ==================================================================  
 * LT 
 * ================================================================== 
 * Copyright (c) jinpj, 2006-2007 
 * ================================================================== 
 */

package com.boubei.learn.jk.thread;

/**
 * <p>
 * WaitNotify.java
 * </p>
 * 
 * @author 金普俊 2006-6-1
 * 
 */

public class WaitNotify extends Thread {
    public static void main(String args[]) throws Exception {
        Thread notificationThread = new WaitNotify();
        notificationThread.start();

        // Wait for the notification thread to trigger event
        synchronized (notificationThread) {
            notificationThread.wait();
        }

        // Notify user that the wait() method has returned
        System.out.println("The wait is over");
    }

    public void run() {
        System.out.println("Hit enter to stop waiting thread");

        try {
            System.in.read();
        } catch (java.io.IOException ioe) {
            // no code req'd
        }

        // Notify any threads waiting on this thread
        synchronized (this) {
            this.notifyAll();
        }
    }
}
