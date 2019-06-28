import java.io.*;
import java.net.*;
import java.util.*;

public class TimeClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();

        byte[] buffer = new byte[256];
        InetAddress address = InetAddress.getByName("127.0.0.1");
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 4444);

        socket.send(packet);

        packet = new DatagramPacket(buffer, buffer.length);

        socket.receive(packet);

        String time = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Current time: " + time);

        socket.close();
    }
}
