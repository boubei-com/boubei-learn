/* ==================================================================   
 * Created [2007-1-8 17:41:33] by Administrator 
 * ==================================================================  
 * LT 
 * ================================================================== 
 * Copyright (c) jinpj, 2006-2007 
 * ================================================================== 
 */

package jinhe.lt.thread.portsanner;

/**
 * <p>
 * PortScannerSingleThread.java
 * </p>
 * 
 * @author 金普俊 2007-1-8
 * 
 * 方案二：通过多线程的方法来进行处理
 * 
 */

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class PortScannerMultiThread {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int beginport = 1;
        int endport = 65535;
        try{
            if(beginport <= 0 || endport >= 65536 || beginport > endport){
                throw new Exception("Port is illegal");
            }
        }catch(Exception e){
            System.out.println("Usage: java PortScannerSingleThread host beginport endport");
            System.exit(0);
        }
        

        for (int i = beginport; i <= endport; i++) {
            PortProcessor pp = new PortProcessor(host,i);      //一个端口创建一个线程
            pp.start();
        }
    }
}

class PortProcessor extends Thread{
    String host;
    int port;
    
    PortProcessor(String host, int port){
        this.host = host;
        this.port = port;
    }
    
    public void run(){
        try{
            Socket s = new Socket(host,port);
            System.out.println("The port " + s.getPort() + " is opened at " + host);
        }catch(UnknownHostException ex){
            System.err.println(ex);
        }catch(IOException ioe){
            System.out.println("The port " + port + " is closed at " + host);
        }
    }
}

