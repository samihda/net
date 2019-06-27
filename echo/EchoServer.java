import java.net.*;
import java.io.*;

class EchoServerThread extends Thread {
    private Socket socket = null;

    public EchoServerThread(Socket socket) {
        super("EchoThread");
        this.socket = socket;
    }

    public void run() {
        try (
             PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         ) {
            socketOut.println("Welcome!");

            String reply;

            while ((reply = socketIn.readLine()) != null) {
                socketOut.println(reply);
            }

            socket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

public class EchoServer {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try (
             ServerSocket serverSocket = new ServerSocket(portNumber);
        ) {
            System.out.println("Listening...");

            while (true) {
                new EchoServerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
