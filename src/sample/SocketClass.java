package sample;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
public class SocketClass {
    public static Socket socket;
    public static BufferedReader in = null;
    public static PrintWriter out = null;

    public static void createSocket(String address, int port) throws IOException {
        socket = new Socket(address, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public static void send(String message) {

        out.println(message);

    }

    public static String sendAndGet(String message) throws IOException {

        send(message);

        String s = in.readLine();

        return s.trim();

    }

}