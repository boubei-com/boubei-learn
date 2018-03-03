package com.boubei.learn.jk.thread;

/**
 * <p>
 * TestThread.java
 * </p>
 * 
 * @author Jon.King Created [2006-5-23 12:43:28]
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestThread extends Thread {
    private static Integer threadCounterLock; // 用于同步，防止数据被写乱

    private static int threadCount; // 本类的线程计数器

    static {
        threadCount = 0;
        threadCounterLock = new Integer(0);
    } 

    public TestThread() {
        super();
    }

    public synchronized static void incThreadCount() {
        threadCount++;
        System.out.println("thread count after enter: " + threadCount);
    }

    public synchronized static void decThreadCount() {
        threadCount--;
        System.out.println("thread count after leave: " + threadCount);
    }

    public void run() {
        synchronized (threadCounterLock) // 同步
        {
            threadCount++;
            System.out.println("thread count after enter: " + threadCount);
        }

        // incThreadCount(); //和上面的语句块是等价的

        final long nSleepMilliSecs = 1000; // 循环中的休眠时间

        long nCurrentTime = System.currentTimeMillis();
        long nEndTime = nCurrentTime + 30000; // 运行截止时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        try {
            while (nCurrentTime < nEndTime) {
                nCurrentTime = System.currentTimeMillis();
                System.out.println("Thread " + this.hashCode()
                        + ", current time: "
                        + simpleDateFormat.format(new Date(nCurrentTime)));

                try {
                    sleep(nSleepMilliSecs);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            synchronized (threadCounterLock) // 同步
            {
                threadCount--;
                System.out.println("thread count after leave: " + threadCount);
            }

            // decThreadCount(); //和上面的语句块是等价的
        }
    }

    public static void main(String[] args) {
        TestThread[] testThread = new TestThread[2];
        for (int i = 0; i < testThread.length; i++) {
            testThread[i] = new TestThread();
            testThread[i].start();
        }
    }
}
