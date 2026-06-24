public class Main {
    public static void main (String[] args) {
        String ip = args[0];
        PingCheck check = new PingCheck(ip);
        PortCheckTop1000 ports = new PortCheckTop1000(ip);
        
        long startTime = System.currentTimeMillis();

        System.out.println("Javanmap 0.1 ( https://github.com/NerixGO/javanmap )");
        
        boolean yn1 = check.ping(ip);
        if (!yn1) {
            System.exit(1);
        }

        ports.top1000(ip);

        long endTime = System.currentTimeMillis();

        System.out.print("\nJavanmap done: 1 IP address (" + yn1 + " hosts up) scanned in " + (endTime - startTime) + " milliseconds.");
    }
}