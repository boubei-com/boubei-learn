package jinhe.lt.web.udp;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class EchoClient {

    public static final int SERVICE_PROT = 1007; //UDP port
    public static final int BUF_SIZE = 4096;// Max size of packet
    public static String hostName = "localhost";
    
    public static void main(String[] args){
        InetAddress addr = null;

        try {
            addr = InetAddress.getByName(hostName);
        } catch (UnknownHostException e) {
            System.err.println("Unable to resolve host");
            return;
        }
        
        try {
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(2000);//过了两秒就抛出异常
            
            for(int i = 1; i <= 10; i++){
                String message = "Packet number " + i + " !";
                char[] cArray = message.toCharArray();
                byte[] sendBuf = new byte[cArray.length];
                
                for(int offset = 0; offset < cArray.length; offset++){
                    sendBuf[offset] = (byte)cArray[offset];
                }
                DatagramPacket sendPacket = new DatagramPacket(sendBuf, cArray.length, addr, SERVICE_PROT);
                
                System.out.println("Sending packet to " + hostName);
                socket.send(sendPacket);
                
                byte[] receiveBuf = new byte[BUF_SIZE];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuf, BUF_SIZE);
                
                boolean timeout = false;

                try{
                    socket.receive(receivePacket);
                }catch (InterruptedIOException e) {
                    timeout = true;
                }
                if(!timeout){
                    System.out.println("Packet received!");
                    System.out.println("Details : " + receivePacket.getAddress());
                    
                    ByteArrayInputStream bin = new ByteArrayInputStream(receivePacket.getData());

                    BufferedReader reader = new BufferedReader(new InputStreamReader(bin));
                    
                    String data = null;
                    while ((data = reader.readLine()) != null) {                  
                        System.out.println(data);
                    }
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e) {
                        System.err.println("Error - " + e.toString());
                    }
                }else{
                    System.out.println("packet lost!");
                }
                
            }
        }catch (IOException e) {
            System.err.println("Error - " + e.toString());
        }

    }
}

