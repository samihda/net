import java.net.*;
import java.util.*;
import static java.lang.System.out;

public class Ls {
    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> ifaces =
            NetworkInterface.getNetworkInterfaces();

        for (NetworkInterface iface: Collections.list(ifaces)) {
            displayInfo(iface);
        }
    }

    private static void displayInfo(NetworkInterface iface) throws SocketException {
        Enumeration<InetAddress> addresses = iface.getInetAddresses();

        out.printf("%s\n", iface.getName());

        for (InetAddress address: Collections.list(addresses)) {
            out.printf("Address: %s\n", address);
        }

        if (iface.isUp()) {
            out.printf("Status: UP\n");
        }
    }
}
