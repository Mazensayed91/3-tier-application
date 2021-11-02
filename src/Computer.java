import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Computer {


    public static void main(String[] args) throws Exception{
        System.out.println("Computer is waiting for sensors...");
        ServerSocket ss = new ServerSocket(9806);
        Socket serverSocket= new Socket("127.0.0.1", 9800);
        while(true){
            Socket client = ss.accept();
            System.out.println("Connection Established");
            ComputerHandler handler = new ComputerHandler(client, serverSocket);
            handler.start();
        }
    }
}