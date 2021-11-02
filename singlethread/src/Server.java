import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) throws Exception{
        System.out.println("Server is waiting for computers...");
        ServerSocket ss = new ServerSocket(9800);
        Socket client = ss.accept();
        System.out.println("Connection with computer Established");
        ServerHandler handler = new ServerHandler(client);
        handler.start();
    }
}

