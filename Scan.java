import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Scan {

    public void checkPort(String ip, int port) {
        
        System.out.println("\nPORT");

        try (Socket socket = new Socket()) {
    
            socket.connect(new InetSocketAddress(ip, port), 300);
            System.out.println(port);

        } catch (IOException e) {
        }
    }
}