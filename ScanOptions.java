import java.net.InetAddress;
import java.net.UnknownHostException;

public class ScanOptions {

    String ip;
    String dataDir;
    int QuantityIps = 0;
    int port = -1;

    public static ScanOptions checkArgs(String[] args) {

        ScanOptions opt = new ScanOptions();

        for (int i = 0; i < args.length; i++) {

            String arg = args[i];

             if (arg.startsWith("--data-dir=")) {
                opt.dataDir = arg.substring("--data-dir=".length());
                continue;
            }
            
            switch (arg) {
                case "-p":
                case "--port":
                    if (i + 1 < args.length) {
                        try {
                            opt.port = Integer.parseInt(args[++i]);
                        } catch (NumberFormatException e) {
                            System.out.println("Error: invalid port number: " + args[i]);
                            System.exit(1);
                        }
                    }
                    continue;

                default:
                    try {
                        InetAddress address = InetAddress.getByName(arg);
                        opt.ip = address.getHostAddress();
                        opt.QuantityIps++;
                    } catch (UnknownHostException e) {
                    }
            }
        }
        return opt;
    }
}