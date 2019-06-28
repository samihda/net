import java.io.*;
import java.net.*;
import java.util.*;

public class TimeClient {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java TimeClient <host> <port>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        DatagramSocket socket = new DatagramSocket();

        byte[] buffer = new byte[256];
        InetAddress address = InetAddress.getByName(hostName);
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, portNumber);

        socket.send(packet);

        packet = new DatagramPacket(buffer, buffer.length);

        socket.receive(packet);

        String time = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Current time: " + time);

        socket.close();
    }
}
