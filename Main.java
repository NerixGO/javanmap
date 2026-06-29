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
        
        check.ping(opt.ip);
        
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
                
                for (int port : ports) {
                    executor.submit(() -> scan.check(opt.ip, port));
                }
                
            } catch (IOException e) {
                System.out.println("Error: cannot read Top1000Ports.txt");
                return;
            } 
        } else if (opt.allPorts) {
            Executors.newFixedThreadPool(200);
            for (int i = 0; i < 65536; i++) {
                int port = i;
                executor.submit(() -> scan.check(opt.ip, port));
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

        int openPorts = scan.getOpenPortsCount();

        if (openPorts > 0) {
            scan.printOpenPorts();
        }
        
        int updown = check.getUpdown();
        
        System.out.print("\nJavanmap done: " + updown + " IP address (" + updown + " hosts up) scanned in " + (double) (endTime - startTime) / 1000.0 + " seconds.\n");
    }
}