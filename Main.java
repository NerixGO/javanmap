import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
    public static void main (String[] args) {

        ScanOptions opt = ScanOptions.checkArgs(args);
        
        if (opt.ip == null) {
            System.out.println("Error: no target IP provided");
            return;
        }

        PingCheck check = new PingCheck();
        Scan scan = new Scan();

        
        long startTime = System.currentTimeMillis();
        
        // Starting main scan
        
        boolean alive = check.ping(opt.ip);

        if (!alive) {
            long endTime = System.currentTimeMillis();

            System.out.println("\nPORT");
            System.out.println("No open ports (host unreachable)");

            System.out.print("\nJavanmap done: 0 IP address (0 hosts up) scanned in " + (double)(endTime - startTime) / 1000.0 + " seconds.\n");
            return;
        } else {
            System.out.println("\nPORT");

            int threads = Runtime.getRuntime().availableProcessors() * 50;
            ExecutorService executor = Executors.newFixedThreadPool(threads);

            if (opt.port == -1) {

                Path portsFile = Paths.get(opt.dataDir, "Top1000Ports.txt");
        
            try {
                List<Integer> ports = Files.readAllLines(portsFile)
                        .stream()
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
            
                ports.sort(Integer::compareTo);

                for (int port : ports) {
                    int currentPort = port;
                    executor.submit(() -> scan.check(opt.ip, currentPort));
                }
            
                } catch (IOException e) {
                    System.out.println("Error: cannot read Top1000Ports.txt");
                } 
            } else {
                executor.submit(() -> scan.check(opt.ip, opt.port));
            }

            executor.shutdown();

            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            long endTime = System.currentTimeMillis();

            int updown = check.getUpdown();

            System.out.print("\nJavanmap done: " + updown + " IP address (" + updown + " hosts up) scanned in " + (double) (endTime - startTime) / 1000.0 + " seconds.\n");
        }
    }
}