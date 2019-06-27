import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        int portNumber = 4444;

        try (
             ServerSocket serverSocket = new ServerSocket(portNumber);
             Socket clientSocket = serverSocket.accept();
             PrintWriter clientOut = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String reply;
            while ((reply = clientIn.readLine()) != null) {
                clientOut.println(reply);
            }
        } catch (IOException e) {
            System.out.println("An error has occured.");
        }
    }
}
