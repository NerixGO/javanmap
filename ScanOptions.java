public class ScanOptions {

    String ip;
    int QuantityIps = 0;
    int port = -1;
        
    public ScanOptions() {
    }

    public static ScanOptions checkArgs(String[] args) {
        ScanOptions opt = new ScanOptions();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            switch (arg) {
                case "-p":
                case "--port":
                    if (i + 1 < args.length) {
                        opt.port = Integer.parseInt(args[++i]);
                    }
                    break;

                default:
                    if (arg.matches("^\\d+\\.\\d+\\.\\d+\\.\\d+$")) {
                        opt.ip = arg;
                        opt.QuantityIps++;
                    }
            }
        }
        return opt;
    }
}