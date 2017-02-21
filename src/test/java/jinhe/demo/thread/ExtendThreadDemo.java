/* ==================================================================   
 * Created [2006-6-1 10:50:57] by jinpujun 
 * ==================================================================  
 * LT 
 * ================================================================== 
 * Copyright (c) jinpj, 2006-2007 
 * ================================================================== 
 */

package jinhe.lt.thread;

/**
 * <p>
 * ExtendThreadDemo.java
 * </p>
 * 
 * @author 金普俊 2006-6-1
 * 
 */

public class ExtendThreadDemo extends Thread {

    int threadNumber;

    public ExtendThreadDemo(int i) {
        threadNumber = i;
    }

    public void run() {
        System.out.println("I am thread number " + threadNumber);
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException interruptedexception) {
        }
        System.out.println(threadNumber + " is finished!");
    }

    public static void main(String args[]) {
        System.out.println("Creating thread 1");
        ExtendThreadDemo extendthreaddemo = new ExtendThreadDemo(1);
        System.out.println("Creating thread 2");
        ExtendThreadDemo extendthreaddemo1 = new ExtendThreadDemo(2);
        extendthreaddemo.start();
        extendthreaddemo1.start();
    }
}
