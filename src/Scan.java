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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Scan {

    private final ConcurrentLinkedQueue<PortResult> results = new ConcurrentLinkedQueue<>();
    private final ConcurrentHashMap<Integer, Boolean> seen = new ConcurrentHashMap<>();

    public static class PortResult{

        public final int port;
        public final String protocol;

        public PortResult(int port, String protocol) {
            this.port = port;
            this.protocol = protocol;
        }
    }

    public void check(String ip, int port) {

        // TCP
        try (Socket socket = new Socket()) {
            
            socket.connect(new InetSocketAddress(ip, port), 300);
            
            if (seen.putIfAbsent(port * 10 + 1, true) == null) {
                results.add(new PortResult(port, "tcp"));
            }

        } catch (IOException ignored) {}

        // UDP
        try (DatagramSocket socket = new DatagramSocket()) {

            socket.setSoTimeout(300);

            byte[] data = "probe".getBytes();

            DatagramPacket packet = new DatagramPacket(
                    data,
                    data.length,
                    InetAddress.getByName(ip),
                    port
            );

            socket.send(packet);

            byte[] buffer = new byte[1024];

            DatagramPacket response = new DatagramPacket(buffer, buffer.length);

            socket.receive(response);

            if (seen.putIfAbsent(port * 10 + 2, true) == null) {
                results.add(new PortResult(port, "udp"));
            }

        } catch (SocketTimeoutException ignored) {
        } catch (IOException ignored) {}
    }

    public List<PortResult> getSortedResults() {
        List<PortResult> list = new ArrayList<>(results);

        Collections.sort(list, (PortResult a, PortResult b) -> {
            if (a.port == b.port) {
                return a.protocol.compareTo(b.protocol);
            }
            return Integer.compare(a.port, b.port);
        });
        return list;
    }

    public void printOpenPorts() {
        List<PortResult> sorted = getSortedResults();
        
        if (sorted.isEmpty()) return;

        System.out.println("\nPORT");
        
        for (PortResult r : sorted) {
            System.out.println(r.port + "/" + r.protocol);
        }
    }

    public int getOpenPortsCount() {
        return results.size();
    }
}