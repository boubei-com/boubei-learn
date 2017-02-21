/* ==================================================================   
 * Created [2007-1-8 18:47:29] by Administrator 
 * ==================================================================  
 * LT 
 * ================================================================== 
 * Copyright (c) jinpj, 2006-2007 
 * ================================================================== 
 */

package jinhe.lt.thread.portsanner;

/**
 * <p>
 * PortScannerByThreadPool.java
 * </p>
 * 
 * @author 金普俊 2007-1-8
 * 
 */

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class PortScannerByThreadPool {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int beginport = 1;
        int endport = 65535;
        int nThreads = 1000;
        try {
            if (beginport <= 0 || endport >= 65536 || beginport > endport) {
                throw new Exception("Port is illegal");
            }
        } catch (Exception e) {
            System.out.println("Usage: java PortScannerSingleThread host beginport endport nThreads");
            System.exit(0);
        }

        ThreadPool tp = new ThreadPool(nThreads);

        for (int i = beginport; i <= endport; i++) {
            Scanner ps = new Scanner(host, i);
            tp.execute(ps);
        }
    }
}

class Scanner implements Runnable {
    String host;

    int port;

    Scanner(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() {
        Socket s = null;
        try {
            s = new Socket(InetAddress.getByName(host), port);
            System.out.println("The port of " + port + " is opened.");
        } catch (IOException ex) {
        } finally {
            try {
                if (s != null)
                    s.close();
            } catch (IOException e) {
            }
        }
    }
}
