import java.net.*;
import java.io.*;
public class clientHandler extends Thread{
    private String username;
    private PrintWriter out;
    private BufferedReader in;
    private Socket clientSocket;
    private chatServer server;

    public clientHandler(Socket socket,chatServer server){
        this.clientSocket = socket;
        this.server = server;
    }

    public void giveMessage(String message){//this is the method to print message from server to client
        out.println(message);
    }

   
    public void run(){
        try {
            out = new PrintWriter(clientSocket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            username = in.readLine();
            out.println("SERVER CONNECTION STARTED");
            String inputLine;
            while((inputLine = in.readLine()) != null){
                if(".".equals(inputLine)){
                    out.println("bye");
                    break;
                }
                server.broadcast(username + ": " + inputLine);
            }

            out.close();
            in.close();
            clientSocket.close();
        } catch (Exception e) {
            
        }
    }
}
