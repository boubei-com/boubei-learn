package jinhe.lt.web.udp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PacketSendDemo {
    public static void main(String[] args) {
        String hostname = "127.0.0.1";

        try {
            System.out.println("Bilding to a local port");

            DatagramSocket socket = new DatagramSocket();
            System.out.println("Bound to local port " + socket.getLocalPort());

            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            PrintStream pout = new PrintStream(bout);
            pout.print("hello,young man!");

            byte[] barray = bout.toByteArray();

            DatagramPacket packet = new DatagramPacket(barray, barray.length);

            System.out.println("Looking up hostname " + hostname);

            InetAddress remoto_addr = InetAddress.getByName(hostname);

            System.out.println("hostname resolved as " + remoto_addr.getHostAddress());

            packet.setAddress(remoto_addr);
            packet.setPort(2000);

            socket.send(packet);

            System.out.println("Packet send!");
        } catch (UnknownHostException e) {
            System.err.println("Can't find host " + hostname);
        } catch (IOException e) {
            System.err.println("Error - " + e.toString());
        }
    }
}
