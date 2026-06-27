import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PingCheck {
    
    public String ip;
    public int updown = 0;

    public PingCheck(String ip) {
        this.ip = ip;
    }

    public int getUpdown() {
        return updown;
    }
    public boolean ping(String ip) {

        try {
            InetAddress address = InetAddress.getByName(ip);

            boolean reachable = address.isReachable(2000);

            if(reachable) {
                System.out.println("Javanmap scan report for " + address.getHostName() + " (" + address.getHostAddress() +")");
                System.out.println("Host is up.");
                updown++;
                return true;
            } else {
                System.out.println("Note: Host seems down.");
                return false;
            }
        } catch (UnknownHostException e) {
            System.out.println("Error: unknown host.");
            return false;
        } catch (IOException e) {
            System.out.println("Note: Host unreachable. It may be blocking ICMP probes.");
            return false;
        }
    }
}