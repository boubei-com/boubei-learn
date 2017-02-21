/* ==================================================================   
 * Created [2006-6-1 9:46:03] by jinpujun 
 * ==================================================================  
 * LT 
 * ================================================================== 
 * Copyright (c) jinpj, 2006-2007 
 * ================================================================== 
 */

package jinhe.lt.thread;

/**
 * <p>
 * Counter.java
 * </p>
 * 
 * @author 金普俊 2006-6-1
 * 
 */

public class Counter {

    private int countValue;

    public Counter() {
        countValue = 0;
    }

    public Counter(int start) {
        countValue = start;
    }

    // Synchronized method to increase counter
    public synchronized void increaseCount() {
        int count = countValue;

        // Simulate slow data processing and modification
        // Removing the synchronized keyword will demonstrate
        // how inaccurate concurrent access can be. Adjusting
        // this value may be necessary on faster machines.
        try {
            Thread.sleep(5);
        } catch (InterruptedException ie) {
        }
        count = count + 1;

        countValue = count;
    }

    // Synchronized method to return counter value
    public synchronized int getCount() {
        return countValue;
    }

}
