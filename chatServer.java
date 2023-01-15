import java.net.*;
import java.util.HashSet;
import java.util.Set;
import java.io.*;

public class chatServer{
    private ServerSocket serverSocket;
    private Set<clientHandler> setOfClients = new HashSet<>();
    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server Started");
            while(true){
                clientHandler currHandler = new clientHandler(serverSocket.accept(),this);
                setOfClients.add(currHandler);
                currHandler.start();
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

    public void broadcast(String message){
        for(clientHandler aClient: setOfClients){
            System.out.println(aClient);
            aClient.giveMessage(message);
        }
    }
    public static void main(String[] args) {
        chatServer server = new chatServer();
        server.start(5000);
    }
}
