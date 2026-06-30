import java.io.IOException;
import java.net.InetAddress;

public class PingCheck {

    public boolean ping(String ip) {

        try {
            InetAddress address = InetAddress.getByName(ip);

            if (address.isReachable(2000)) {
                System.out.println("Javanmap scan report for " + address.getHostName() + " (" + address.getHostAddress() + ")");
                System.out.println("Host is up");
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