package jinhe.lt.web.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class EchoServer {

    public static final int SERVICE_PROT = 1007; //UDP port
    public static final int BUF_SIZE = 4096;// Max size of packet
    private DatagramSocket socket;
    
    public EchoServer(){
        try{
            socket = new DatagramSocket(SERVICE_PROT);
            System.out.println("Server active on port " + socket.getLocalPort());
        }catch(Exception e){
            System.err.println("Unable to bind port");
            System.exit(-1);
        }
    }
    
    public void serviceClients(){
        byte[] buffer = new byte[BUF_SIZE];
        while(true){
            DatagramPacket packet = new DatagramPacket(buffer, BUF_SIZE);            
            try {
                socket.receive(packet);
                
                System.out.println("Packet receive from " + packet.getAddress() + ":" + packet.getPort() +  
                        "of length" + packet.getLength());
                
                socket.send(packet); //回复给客户端
            } catch (IOException e) {
                System.err.println("Error : " + e);
            }
        }
    }
    
    public static void main(String[] args){
        EchoServer server = new EchoServer();
        server.serviceClients();
    }
}

