/* ==================================================================   
 * Created [2006-6-1 10:58:29] by jinpujun 
 * ==================================================================  
 * LT 
 * ================================================================== 
 * Copyright (c) jinpj, 2006-2007 
 * ================================================================== 
 */

package com.boubei.learn.jk.thread;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

/**
 * <p>
 * PipeDemo.java
 * </p>
 * 
 * @author 金普俊 2006-6-1
 * 
 */

public class PipeDemo extends Thread {

    PipedOutputStream output;

    public PipeDemo(PipedOutputStream pipedoutputstream) {
        output = pipedoutputstream;
    }

    public static void main(String args[]) {
        try {
            PipedOutputStream outstream = new PipedOutputStream();
            PipedInputStream instream = new PipedInputStream(outstream);
            PipeDemo pipedemo = new PipeDemo(outstream);
            pipedemo.start();
            for (int i = instream.read(); i != -1; i = instream.read()) {
                System.out.print((char) i);
            }
            
            outstream.close();
            instream.close();
        } 
        catch (Exception exception) {
            System.err.println("Pipe error " + exception);
        } 
    }

    public void run() {
        try {
            PrintStream printstream = new PrintStream(output);
            printstream.println("Hello from another thread, via pipes!");
            printstream.close();
        } 
        catch (Exception exception) { }
    }
}
