/* ==================================================================   
 * Created [2006-6-1 10:53:52] by jinpujun 
 * ==================================================================  
 * LT 
 * ================================================================== 
 * Copyright (c) jinpj, 2006-2007 
 * ================================================================== 
 */

package com.boubei.learn.jk.thread;

/**
 * <p>
 * GroupDemo.java
 * </p>
 * 
 * @author 金普俊 2006-6-1
 * 
 */

public class GroupDemo implements Runnable {

    public GroupDemo() {
    }

    public static void main(String args[]) throws Exception {
        ThreadGroup threadgroup = new ThreadGroup("parent");
        ThreadGroup threadgroup1 = new ThreadGroup(threadgroup, "subgroup");
        Thread thread = new Thread(threadgroup, new GroupDemo());
        thread.start();
        Thread thread1 = new Thread(threadgroup, new GroupDemo());
        thread1.start();
        Thread thread2 = new Thread(threadgroup1, new GroupDemo());
        thread2.start();
        threadgroup.list();
        System.out.println("Press enter to continue");
        System.in.read();
        System.exit(0);
    }

    public void run() {
        do
            Thread.yield();
        while (true);
    }
}
