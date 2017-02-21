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
 * 方案一：采用单线程进行处理
 * 
 */

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class PortScannerSingleThread {
    public static void main(String[] args) {
        String host = "127.0.0.1"; // 第一个参数，目标主机。
        int beginport = 1; // 第二个参数，开始端口。
        int endport = 65535; // 第三个参数，结束端口。
        try {
            if (beginport <= 0 || endport >= 65536 || beginport > endport) {
                throw new Exception("Port is illegal");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        for (int i = beginport; i <= endport; i++) {
            try {
                Socket s = new Socket(host, i);
                System.out.println("The port " + s.getPort() + " is opened at " + host);
            } catch (UnknownHostException ex) {
                System.err.println(ex);
                break;
            } catch (IOException ex) {
                System.out.println("The port " + i + " is closed at " + host);
            }
        }
    }
}
