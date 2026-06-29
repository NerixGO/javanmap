import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PortCheckTop1000 {

    public void top1000(String ip, String dataDir) {

        System.out.println("\nPORT");
        Path portsFile = Paths.get(dataDir, "Top1000Ports.txt");

        List<Integer> ports;
        
        try {
            ports = Files.readAllLines(portsFile)
                    .stream()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            
        } catch (IOException e) {
            System.out.println("Error: cannot read Top1000Ports.txt");
            return;
        }

        for (int port : ports) {
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(ip, port), 100);
                System.out.println(port + "/tcp");
            } catch (IOException e) {}

            try (DatagramSocket socket = new DatagramSocket()) {

                socket.setSoTimeout(100);

                byte[] data = "probe".getBytes();

                DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(ip), port);
                socket.send(packet);

                byte[] buffer = new byte[1024];

                DatagramPacket response = new DatagramPacket(buffer, buffer.length);

                socket.receive(response);

                System.out.println(port + "/udp open (responce)");
            } catch (SocketTimeoutException e) {}
            catch (IOException e) {}
        }
    }
}