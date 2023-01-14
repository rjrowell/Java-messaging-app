import java.net.*;
import java.io.*;
public class clientHandler extends Thread{
    private PrintWriter out;
    private BufferedReader in;
    private Socket clientSocket;

    public clientHandler(Socket socket){
        this.clientSocket = socket;
    }

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
                out.println(inputLine);
            }

            out.close();
            in.close();
            clientSocket.close();
        } catch (Exception e) {
            
        }
    }
}
