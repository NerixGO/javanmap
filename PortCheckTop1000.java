import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PortCheckTop1000 {

    public void top1000(String ip) {
        System.out.println("\nPORT");

        try {
            List<Integer> ports = Files.readAllLines(Paths.get(System.getProperty("user.dir"), "Top1000Ports.txt")
                )
                .stream()
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());

            for (Integer port : ports) {
                try (Socket socket = new Socket()) {
                    socket.connect(new InetSocketAddress(ip, port), 200);
                    System.out.println(port);
                } catch (IOException e) {}
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}