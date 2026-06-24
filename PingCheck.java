import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PingCheck {
    
    public String ip;

    public PingCheck(String ip) {
        this.ip = ip;
    }

    public boolean ping(String ip) {
        int updown = 0;

        try {
            InetAddress address = InetAddress.getByName(ip);
            
            if (address.isReachable(3000)) {
                System.out.println("Javanmap scan report for " + ip + "\nHost is up.");
                updown++;
            }
        } catch (UnknownHostException e) {
            System.out.println("Note: Host seems down. If it is really up, but blocking our ping probes, try -Pn");
            return false;
        } catch (IOException e) {
            System.out.println("Note: Host seems down. If it is really up, but blocking our ping probes, try -Pn");
            return false;
        };
        return true;
    }
}