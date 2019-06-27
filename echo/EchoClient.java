import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        try (
            Socket socket = new Socket("127.0.0.1", 4444);
            PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String response, request;

            while ((response = socketIn.readLine()) != null) {
                System.out.println("Server: " + response);

                request = stdIn.readLine();
                if (request != null) {
                    System.out.println("Client: " + request);
                    socketOut.println(request);
                }
            }
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
