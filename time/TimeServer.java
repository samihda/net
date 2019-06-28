import java.io.*;
import java.net.*;
import java.util.*;

class TimeServerThread extends Thread {
    private MulticastSocket socket = null;
    private int port;
    private String group;

    public TimeServerThread(int port, String group) throws IOException {
        this(port, group, "TimeServerThread");
    }

    public TimeServerThread(int port, String group, String name) throws IOException {
        super(name);
        this.port = port;
        this.group = group;
        socket = new MulticastSocket(port);
    }

    public void run() {
        System.out.println("Listening...");

        while (true) {
            try {
                byte[] buffer = new Date().toString().getBytes();
                InetAddress address = InetAddress.getByName(group);

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);

                System.out.println("Broadcasted at " + new String(buffer));
                socket.send(packet);

                try {
                    sleep(5000);
                } catch (InterruptedException e) {}
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        // socket.close();
    }
}

public class TimeServer {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java TimeServer <group> <port>");
            System.exit(1);
        }

        String groupIp = args[0]; // 224.0.0.0 - 239.255.255.255
        int portNumber = Integer.parseInt(args[1]);
        new TimeServerThread(portNumber, groupIp).start();
    }
}
