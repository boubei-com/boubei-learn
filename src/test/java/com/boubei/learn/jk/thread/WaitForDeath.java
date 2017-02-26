/* ==================================================================   
 * Created [2006-6-1 11:24:23] by jinpujun 
 * ==================================================================  
 * LT 
 * ================================================================== 
 * Copyright (c) jinpj, 2006-2007 
 * ================================================================== 
 */

package com.boubei.learn.jk.thread;

/**
 * <p>
 * WaitForDeath.java
 * </p>
 * 
 * @author 金普俊 2006-6-1
 * 
 */

public class WaitForDeath extends Thread {
    // Run method is executed when thread first started
    public void run() {
        System.out.println("This thread feels a little ill....");

        // Sleep for five seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }
    }

    // Main method to create and start threads
    public static void main(String args[])
            throws java.lang.InterruptedException {
        // Create and start dying thread
        Thread dying = new WaitForDeath();
        dying.start();

        // Prompt user and wait for input
        System.out.println("Waiting for thread death");

        // Wait till death
        dying.join();

        System.out.println("Thread has died");
    }
}
