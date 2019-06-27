import java.net.*;
import java.io.*;

public class EchoServerThread extends Thread {
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
