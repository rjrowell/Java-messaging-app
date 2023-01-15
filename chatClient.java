import java.net.*;
import java.io.*;

public class chatClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip,int port){
        try {
            clientSocket = new Socket(ip,port);
            out = new PrintWriter(clientSocket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public String sendMessage(String msg) {
        out.println(msg);
        String resp;
        try {
            resp = in.readLine();
        } catch (IOException e) {
            resp = "did not get response";
            e.printStackTrace();
        }
        return resp;
    }

    public void stop(){
        try {
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
    }

    public static void main(String[] args) {
        chatClient client = new chatClient();
        client.startConnection("127.0.0.1", 5000);
        String resp = client.sendMessage("Hello World");
        System.out.println(resp);
    }

}
