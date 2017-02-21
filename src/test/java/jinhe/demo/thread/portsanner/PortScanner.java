/* ==================================================================   
 * Created [2007-1-8 18:02:53] by Administrator 
 * ==================================================================  
 * LT 
 * ================================================================== 
 * Copyright (c) jinpj, 2006-2007 
 * ================================================================== 
*/

package jinhe.lt.thread.portsanner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/** 
 * <p> PortScanner.java </p> 
 * 
 * @author 金普俊 2007-1-8
 *
 * 第一种实现线程池的方法是：创建一个”池“，在”池“中增加要处理的数据对象，
 * 然后创建一定数量的线程，这些线程对”池“中的对象进行处理。
 * 当”池“是空的时候，每个线程处于等待状态；
 * 当往”池“里添加一个对象，通知所有等待的线程来处理（当然一个对象只能有一个线程来处理）。
 */
@SuppressWarnings("unchecked")
public class PortScanner {
    private List entries = Collections.synchronizedList(new LinkedList());  //这个”池“比较特别
    int numofthreads;
    static int port;
    int beginport;
    int endport;
    InetAddress remote = null;
    
    public boolean isFinished(){
        if(port >= endport){
            return true;
        }else{
            return false;
        }
    }
    
    PortScanner(InetAddress addr, int beginport, int endport, int numofthreads){
        this.remote = addr;
        this.beginport = beginport;
        this.endport = endport;
        this.numofthreads = numofthreads;    
    }
    
    public void processMethod(){
        for(int i = 0; i < numofthreads; i++){          //创建一定数量的线程并运行
            Thread t = new PortThread(remote, entries, this);
            t.start();
        }
        
        port = beginport;
        
        while(true){
            if(entries.size() > numofthreads){
                try{
                    Thread.sleep(1000);      //”池“中的内容太多的话就sleep
                }catch(InterruptedException ex){
                    
                }
                continue;
            }
            
            synchronized(entries){
                if(port > endport) break;
                entries.add(0, new Integer(port));  //往”池“里添加对象，需要使用int对应的Integer类
                entries.notifyAll();
                port++;
            }
        }
    }
    
    public static void main(String[] args) {
        String host = null;
        int beginport = 1;
        int endport = 65535;
        int nThreads = 1000;
        try{
            if(beginport <= 0 || endport >= 65536 || beginport > endport){
                throw new Exception("Port is illegal");
            }
        }catch(Exception e){
            System.out.println("Usage: java PortScannerSingleThread host beginport endport nThreads");
            System.exit(0);
        }
        
        try{
            PortScanner scanner = new PortScanner(InetAddress.getByName(host), beginport, endport, nThreads);
            scanner.processMethod();
        }catch(UnknownHostException ex){
        }    
    }
}

@SuppressWarnings("unchecked")
class PortThread extends Thread{
    private InetAddress remote;
    private List entries;
    PortScanner scanner;
    
    PortThread(InetAddress add, List entries, PortScanner scanner){
        this.remote = add;
        this.entries = entries;
        this.scanner = scanner;
    }
            
    public void run(){
        Integer entry;
        while(true){
            synchronized(entries){
                while(entries.size() == 0){
                    if(scanner.isFinished()) return;
                    try{
                        entries.wait();           //”池“里没内容就只能等了
                    }catch(InterruptedException ex){
                    }
                }
                entry = (Integer)entries.remove(entries.size()-1);  //把”池“里的东西拿出来进行处理
            }
            
            Socket s = null;
            
            try{
                s = new Socket(remote, entry.intValue());
                System.out.println("The port of " + entry.toString() + " of the remote " + remote +" is opened.");
            
            }catch(IOException e){
                System.out.println("The port " + entry.toString() + " is closed at " + remote);
            }finally{
                try{
                    if(s != null) s.close();
                }catch(IOException e){
                    
                }
            }
        }
    }
}


