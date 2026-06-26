import java.io.IOException;
import java.net.Socket;

public class Scan {

    public String ip;
    public int port;

    public void checkPort(String ip, int port) {
        try {
            Socket socket = new Socket(ip, port);
    
            System.out.println("\nPORT");
            System.out.println(port);

            socket.close();
        } catch (IOException e) {
        }


    }
}