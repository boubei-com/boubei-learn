/* ==================================================================   
 * Created [2006-6-1 11:13:12] by jinpujun 
 * ==================================================================  
 * LT 
 * ================================================================== 
 * Copyright (c) jinpj, 2006-2007 
 * ================================================================== 
 */

package jinhe.lt.thread;

/**
 * <p>
 * SleepyHead.java
 * </p>
 * 
 * @author 金普俊 2006-6-1
 * 
 */

import java.io.IOException;

public class SleepyHead extends Thread {

    public SleepyHead() {
    }

    public void run() {
        System.out.println("I feel sleepy. Wake me in eight hours");
        try {
            Thread.sleep(0x1b77400L);
            System.out.println("That was a nice nap");
        } catch (InterruptedException interruptedexception) {
            System.err.println("Just five more minutes....");
        }
    }

    public static void main(String args[]) throws IOException {
        SleepyHead sleepyhead = new SleepyHead();
        sleepyhead.start();
        //Thread.sleep(100);
        System.out.println("Press enter to interrupt the thread");
        System.in.read();
        sleepyhead.interrupt();
    }
}
