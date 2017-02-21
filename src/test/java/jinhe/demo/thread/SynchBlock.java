/* ==================================================================   
 * Created [2006-6-1 11:20:06] by jinpujun 
 * ==================================================================  
 * LT 
 * ================================================================== 
 * Copyright (c) jinpj, 2006-2007 
 * ================================================================== 
 */

package jinhe.lt.thread;

/**
 * <p>
 * SynchBlock.java
 * </p>
 * 
 * @author 金普俊 2006-6-1
 * 
 */

public class SynchBlock implements Runnable {
    StringBuffer buffer;

    int counter;

    public SynchBlock() {
        buffer = new StringBuffer();
        counter = 1;
    }

    public void run() {

        synchronized (buffer) {
            System.out.print("Starting synchronized block ");
            int tempVariable = counter++;

            // Create message to add to buffer, including linefeed
            String message = "Count value is : " + tempVariable
                    + System.getProperty("line.separator");

            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
            }

            buffer.append(message);
            System.out.println("... ending synchronized block");
        }
    }

    public static void main(String args[]) throws Exception {
        // Create a new runnable instance
        SynchBlock block = new SynchBlock();

        Thread t1 = new Thread(block);
        Thread t2 = new Thread(block);
        Thread t3 = new Thread(block);
        Thread t4 = new Thread(block);
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Wait for all three threads to finish
        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println(block.buffer);
    }
}
