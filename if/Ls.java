import java.net.*;
import java.util.*;

public class Ls {
    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> ifaces =
            NetworkInterface.getNetworkInterfaces();

        for (NetworkInterface iface: Collections.list(ifaces)) {
            System.out.printf("Name: %s\n", iface.getName());
        }
    }
}
