import java.io.*;
import java.net.*;
import java.util.*;

class TimeServerThread extends Thread {
    private DatagramSocket socket = null;

    public TimeServerThread() throws IOException {
        this("TimeServerThread");
    }

    public TimeServerThread(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(4444);
    }

    public void run() {
        try {
            byte[] buffer = new byte[256];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            System.out.println("Listening...");
            socket.receive(packet);

            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            buffer = new Date().toString().getBytes();
            packet = new DatagramPacket(buffer, buffer.length, address, port);

            socket.send(packet);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        socket.close();
    }
}

public class TimeServer {
    public static void main(String[] args) throws IOException {
        new TimeServerThread().start();
    }
}
