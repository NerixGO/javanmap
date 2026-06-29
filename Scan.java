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

public class Scan {

    public void checkPort(String ip, int port, String dataDir) {
        
        if (port == -1) {
            PortCheckTop1000 ports = new PortCheckTop1000();
            ports.top1000(ip, dataDir);
        }

        System.out.println("\nPORT");

        try (Socket socket = new Socket()) {
    
            socket.connect(new InetSocketAddress(ip, port), 300);
            System.out.println(port + "/tcp");
        } catch (IOException e) {}

        try (DatagramSocket socket = new DatagramSocket()) {

            socket.setSoTimeout(300);

            byte[] data = "probe".getBytes();

            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(ip), port);
            socket.send(packet);

            byte[] buffer = new byte[1024];

            DatagramPacket response = new DatagramPacket(buffer, buffer.length);

            socket.receive(response);

            System.out.println(port + "/udp open");
        } catch (SocketTimeoutException e) {}
        catch (IOException e) {}
    }
}