package jinhe.lt.web.udp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class PacketReceiveDemo {
    public static void main(String[] args) {
        try {
            System.out.println("Bilding to local port 2000");

            DatagramSocket socket = new DatagramSocket(2000);

            System.out.println("Bound to local port" + socket.getLocalPort());

            DatagramPacket packet = new DatagramPacket(new byte[256], 256);

            for(int i = 0; i < 1000; i++){
                socket.receive(packet);//读操作是阻塞操作，所以在包到达之前，服务器将一直等待

                InetAddress remoto_addr = packet.getAddress();
                System.out.println("Sent by: " + remoto_addr.getHostAddress());
                System.out.println("sent from: " + packet.getPort());

                ByteArrayInputStream bin = new ByteArrayInputStream(packet.getData());

                for (int j = 0; j < packet.getLength(); j++) {
                    int data = bin.read();

                    if (data == -1)
                        break;
                    else
                        System.out.print((char) data);
                }
                System.out.print("\n\n");
            }           
            socket.close();
        } catch (IOException e) {
            System.err.println("Erroe - " + e);
        }
    }
}
