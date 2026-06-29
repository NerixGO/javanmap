import java.net.InetAddress;
import java.net.UnknownHostException;

public class ScanOptions {

    String ip;
    String dataDir;
    int port = -1;
    boolean allPorts = false;

    public static ScanOptions checkArgs(String[] args) {

        ScanOptions opt = new ScanOptions();

        for (int i = 0; i < args.length; i++) {

            String arg = args[i];

            if (arg.startsWith("--data-dir=")) {
                opt.dataDir = arg.substring("--data-dir=".length());
                continue;
            }
            
            if (arg.matches("^-p\\d+$|^--port\\d+$")) {
                String value = arg.replaceAll("\\D+", "");
                try {
                    opt.port = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    System.out.println("Error: invalid port number: " + value);
                    System.exit(1);
                }
            }

            switch (arg) {

                case "-p":
                case "--port":
                    String value = null;
                    
                    if (i + 1 < args.length) {
                        value = args[++i];
                    }
                    
                    if (value == null) {
                        System.out.println("Error: port not specified");
                        System.exit(1);
                    }

                    try {
                        opt.port = Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: invalid port number: " + value);
                        System.exit(1);
                    }
                    continue;

                case "-p-":
                case "--all-ports":
                    opt.allPorts = true;
                    continue;
                    
                default:

                    try {
                        String cleaned = arg.replace("http://", "").replace("https://", "");
                        InetAddress address = InetAddress.getByName(cleaned);
                        opt.ip = address.getHostAddress();
                    } catch (UnknownHostException e) {
                            System.out.println("Error: invalid host: " + arg);
                            System.exit(1);
                    }
            }
        }
        return opt;
    }
}