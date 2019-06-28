import java.io.*;
import java.net.*;
import java.util.*;

public class TimeClient {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java TimeClient <group> <port>");
            System.exit(1);
        }

        String groupIp = args[0];
        int portNumber = Integer.parseInt(args[1]);

        MulticastSocket socket = new MulticastSocket(portNumber);
        InetAddress address = InetAddress.getByName(groupIp);

        socket.joinGroup(address);

        System.out.println("Waiting for server...");

        DatagramPacket packet;

        for (int i = 0; i < 5; i++) {
            byte[] buffer = new byte[256];
            packet = new DatagramPacket(buffer, buffer.length);

            socket.receive(packet);

            String time = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Current time: " + time);
        }

        socket.leaveGroup(address);
        socket.close();
    }
}
