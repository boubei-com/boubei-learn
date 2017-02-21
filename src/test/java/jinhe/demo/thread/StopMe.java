/* ==================================================================   
 * Created [2006-6-1 11:17:04] by jinpujun 
 * ==================================================================  
 * LT 
 * ================================================================== 
 * Copyright (c) jinpj, 2006-2007 
 * ================================================================== 
 */

package jinhe.lt.thread;

/**
 * <p>
 * StopMe.java
 * </p>
 * 
 * @author 金普俊 2006-6-1
 * 
 */

public class StopMe extends Thread {
    // Run method is executed when thread first started
    public void run() {
        int count = 1;

        System.out.println("I can count. Watch me go!");

        for (;;) {
            // Print count and increment it
            System.out.print(count++ + " ");

            // Sleep for half a second
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
            }
        }
    }

    // Main method to create and start threads
    public static void main(String args[]) throws java.io.IOException {
        // Create and start counting thread
        Thread counter = new StopMe();
        counter.start();

        // Prompt user and wait for input
        System.out.println("Press any enter to stop the thread counting");
        System.in.read();

        // Interrupt the thread
        //counter.stop();
    }
}
