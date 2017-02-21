package jinhe.lt.web.tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DaytimeServer {
    public static final int SERVICE_PORT = 1331;

    public static void main(String[] args){
        try{
            ServerSocket server = new ServerSocket(SERVICE_PORT);
            
            System.out.println("Daytime server started!");
            
            for(;;){
                Socket nextClient = server.accept();
                
                System.out.println("Received request from " + nextClient.getInetAddress() + ":" + nextClient.getPort());
                
                //PrintStream out = new PrintStream(nextClient.getOutputStream());
                PrintWriter out = new PrintWriter(nextClient.getOutputStream());
                out.print(new Date());
                out.flush();
                out.close();               
            }
        }catch(BindException e){
            System.out.println("Service already running in port!" + e);
        }catch(IOException e){
            System.out.println("Error : " + e);
        }
    }
}

