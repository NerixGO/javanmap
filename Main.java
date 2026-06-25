public class Main {
    public static void main (String[] args) {
        String version = args[0];
        String ip = args[1];
        PingCheck check = new PingCheck(ip);
        PortCheckTop1000 ports = new PortCheckTop1000();
        
        long startTime = System.currentTimeMillis();

        System.out.println("Javanmap " + version + " ( https://github.com/NerixGO/javanmap )");
        
        boolean yn1 = check.ping(ip);
        if (!yn1) {
            long endTime = System.currentTimeMillis();
            int updown = check.getUpdown();
            System.out.print("Javanmap done: 1 IP address (" + updown + " hosts up) scanned in " + (double)(endTime - startTime)/1000 + " seconds.");
            System.exit(1);
        }

        ports.top1000(ip);

        long endTime = System.currentTimeMillis();

        int updown = check.getUpdown();
        System.out.print("Javanmap done: 1 IP address (" + updown + " hosts up) scanned in " + (double)(endTime - startTime)/1000 + " seconds.");
    }
}