public class Main {
    public static void main (String[] args) {
        String ip = args[0];
        PingCheck check = new PingCheck(ip);
        
        
        System.out.println("Javanmap 0.1 ( https://github.com/NerixGO/javanmap )");
        
        boolean yn1 = check.ping(ip);
        if (!yn1) {
            System.exit(1);
        }

        
    }
}