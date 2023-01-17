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
            String username = setUsername();
            out.println(username);
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

    public String setUsername(){
        String username = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean temp = false;
        while(temp == false){
            System.out.println("What is your username?");
            try {
                username = br.readLine();
                System.out.println("Your username is " + username + " is this correct (Y/N)");
                String inputString = br.readLine().toLowerCase();
                if(inputString.equals("y")){
                    temp = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return username;
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

    public  String readIt(){
        String resp;
        try {
            resp = in.readLine();
        } catch (IOException e) {
            resp = "did not get response";
            e.printStackTrace();
        }
        return resp;
    }

    public static void main(String[] args) {
        chatClient client = new chatClient();
        client.startConnection("127.0.0.1", 5000);
        System.out.println(client.sendMessage("Hello World"));
        /*client.startConnection("127.0.0.1", 5000);
        String resp = client.sendMessage("Hello World");
        System.out.println(resp);*/
    }

}
