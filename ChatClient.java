import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 1234;

        try {
            Socket socket = new Socket(serverAddress, port);
            System.out.println("Connected to chat server");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader keyboard = new BufferedReader(
                    new InputStreamReader(System.in));

            // Thread to read messages from server
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Send messages to server
            String input;
            while ((input = keyboard.readLine()) != null) {
                out.println(input);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
