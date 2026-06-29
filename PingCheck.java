import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class PingCheck {

    private int updown = 0;

    public int getUpdown() {
        return updown;
    }

    public boolean ping(String ip) {

        try {
            InetAddress address = InetAddress.getByName(ip);

            if (address.isReachable(2000)) {
                System.out.println("Javanmap scan report for " + address.getHostName() + " (" + address.getHostAddress() +")");
                System.out.println("Host is up: ");
                updown++;
                return true;
            } else {
                return false;
            }

        } catch (IOException e) {
            System.out.println("Note: Host seems down");
            return false;
        }
    }
}
