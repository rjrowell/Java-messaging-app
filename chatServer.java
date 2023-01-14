import java.net.*;
import java.io.*;

public class chatServer{
    private ServerSocket serverSocket;
    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server Started");
            while(true){
                new clientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void stop(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        chatServer server = new chatServer();
        server.start(5000);
    }
}
