import java.net.*;
import java.io.*;

public class chatServer{
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) {
        try(ServerSocket serverSocket = new ServerSocket(port);) {
            System.out.println("Server Started");
            Socket clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String greeting = in.readLine();
            if("hello server".equals(greeting)){
                out.println("hello client");
            }else{
                out.println("unrecognised greeting");
            }
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void stop(){
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
    }

    public static void main(String[] args) {
        chatServer server = new chatServer();
        server.start(5000);
    }
}
