import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Scan {

    private final ConcurrentLinkedQueue<PortResult> results = new ConcurrentLinkedQueue<>();

    public record PortResult(int port, String protocol) {}

    public void check(String ip, int port) {

        try (Socket socket = new Socket()) {
    
            socket.connect(new InetSocketAddress(ip, port), 300);
            results.add(new PortResult(port, "tcp"));

        } catch (IOException e) {}

        try (DatagramSocket socket = new DatagramSocket()) {

            socket.setSoTimeout(300);

            byte[] data = "probe".getBytes();

            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(ip), port);
            socket.send(packet);

            byte[] buffer = new byte[1024];

            DatagramPacket response = new DatagramPacket(buffer, buffer.length);

            socket.receive(response);

            results.add(new PortResult(port, "udp"));

        } catch (SocketTimeoutException e) {}
        catch (IOException e) {}
    }

    public List<PortResult> getSortedResults() {
        List<PortResult> list = new ArrayList<>(results);
        Collections.sort(list, (a, b) -> {
            if (a.port() == b.port()) {
                return a.protocol().compareTo(b.protocol());
            }
            return Integer.compare(a.port(), b.port());
        });
        return list;
    }

    public void printOpenPorts() {
        List<PortResult> sorted = getSortedResults();
        
        if (sorted.isEmpty()) {
            return;
        }

        System.out.println("\nPORT");
        for (PortResult r : sorted) {
            System.out.println(r.port() + "/" + r.protocol());
        }
    }

    public int getOpenPortsCount() {
        return results.size();
    }
}