import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.SocketTimeoutException;

public class Scan {

    public void check(String ip, int port) {

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