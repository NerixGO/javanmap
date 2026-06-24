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

        long startTime = System.currentTimeMillis();
        try {
            InetAddress address = InetAddress.getByName(ip);
            
            if (address.isReachable(3000)) {
                System.out.println("Javanmap scan report for " + ip + "Host is up.");
                updown++;
            }
        } catch (UnknownHostException e) {
            long endTime = System.currentTimeMillis();
            System.out.println("Note: Host seems down. If it is really up, but blocking our ping probes, try -Pn");
            System.out.print("Javanmap done: 1 IP address (" + updown + " hosts up) scanned in " + (endTime - startTime) + " milliseconds.");
            return false;
        } catch (IOException e) {
            long endTime = System.currentTimeMillis();
            System.out.println("Note: Host seems down. If it is really up, but blocking our ping probes, try -Pn");
            System.out.print("Javanmap done: 1 IP address (" + updown + " hosts up) scanned in " + (endTime - startTime) + " milliseconds.");
            return false;
        };
        return true;
    }
}