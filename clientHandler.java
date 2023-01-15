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

    /*private void setUsername(){
        try {
            boolean temp = false;
            while(temp == false){
                out.println("What is your username?");
                username = in.readLine();
                out.println("Your username is " + username + " is this correct (Y/N)");
                String inputString = in.readLine().toLowerCase();
                if(inputString == "y"){
                    temp = true;
                }
            }
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
   
    public void run(){
        try {
            out = new PrintWriter(clientSocket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            while((inputLine = in.readLine()) != null){
                if(".".equals(inputLine)){
                    out.println("bye");
                    break;
                }
                server.broadcast("Message recieved");
            }

            out.close();
            in.close();
            clientSocket.close();
        } catch (Exception e) {
            
        }
    }
}
