import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main (String[] args) {

        ScanOptions opt = ScanOptions.checkArgs(args);
        
        if (opt.ip == null) {
            System.out.println("Error: no target IP provided");
            return;
        }

        PingCheck check = new PingCheck(opt.ip);
        Scan scan = new Scan();

        
        long startTime = System.currentTimeMillis();
        
        // Starting main scan
        
        boolean alive = check.ping(opt.ip);
        if (!alive) {
            long endTime = System.currentTimeMillis();
            int updown = check.getUpdown();
            System.out.print("\nJavanmap done: " + opt.QuantityIps + " IP address (" + updown + " hosts up) scanned in " + (double)(endTime - startTime)/1000.0 + " seconds.\n");
            System.exit(1);
        }
        
        ExecutorService executor = Executors.newFixedThreadPool(100);
        if (opt.port != -1) {
            executor.submit(() -> scan.checkPort(opt.ip, opt.port, opt.dataDir));
        } else {
            PortCheckTop1000 ports = new PortCheckTop1000();
            executor.submit(() -> ports.top1000(opt.ip, opt.dataDir));
        }

        executor.shutdown();

        long endTime = System.currentTimeMillis();

        int updown = check.getUpdown();
        System.out.print("\nJavanmap done: " + opt.QuantityIps + " IP address (" + updown + " hosts up) scanned in " + (double)(endTime - startTime)/1000.0 + " seconds.\n");
    }
}