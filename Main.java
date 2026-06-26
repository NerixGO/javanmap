public class Main {
    public static void main (String[] args) {

        ScanOptions opt = ScanOptions.checkArgs(args);
        Scan scan = new Scan();

        if (opt.ip == null) {
            System.out.println("Error: no target IP provided");
            return;
        }
        PingCheck check = new PingCheck(opt.ip);
        PortCheckTop1000 ports = new PortCheckTop1000();
        
        long startTime = System.currentTimeMillis();
        
        // Starting main scan
        
        boolean alive = check.ping(opt.ip);
        if (!alive) {
            long endTime = System.currentTimeMillis();
            int updown = check.getUpdown();
            System.out.print("\nJavanmap done: " + opt.QuantityIps + " IP address (" + updown + " hosts up) scanned in " + (double)(endTime - startTime)/1000 + " seconds.");
            System.exit(1);
        }
        
        if (opt.port != -1) {
            scan.checkPort(opt.ip, opt.port);
        } else {
            ports.top1000(opt.ip);
        }

        long endTime = System.currentTimeMillis();

        int updown = check.getUpdown();
        System.out.print("\nJavanmap done: " + opt.QuantityIps + " IP address (" + updown + " hosts up) scanned in " + (double)(endTime - startTime)/1000 + " seconds.");
    }
}