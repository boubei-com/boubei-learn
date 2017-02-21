package jinhe.lt.web.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/** 
 * <p> DayTimeClient.java </p> 
 * 
 * 
 * TCP通过创建Socket套接字连接，UDP创建数据报DatagramPacket
 *
 */
public class DaytimeClient {
    public static final int SERVICE_PORT = 1331;
    public static String hostName = "localhost";
    
    public static void main(String[] args){
        try{
            Socket daytime = new Socket(hostName, SERVICE_PORT);
            System.out.println("Connetion established!");
            
            daytime.setSoTimeout(2000);
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(daytime.getInputStream()));
            
            System.out.println("Results : " + reader.readLine());
            
            daytime.close();
        }catch(IOException e){
            System.out.println("Error" + e);
        }
        
    }

}

