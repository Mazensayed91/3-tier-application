import java.io.*;
import java.net.*;

class ComputerHandler extends Thread{

    Socket socket;
    Socket serverSocket;
    BufferedReader in;
    PrintWriter out;
    BufferedReader in2;
    PrintWriter out2;

    public ComputerHandler(Socket socket, Socket serverSocket) throws IOException{
        this.socket = socket;
        this.serverSocket = serverSocket;

    }

    public void run(){
        try{
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);

            in2 = new BufferedReader(new InputStreamReader(this.serverSocket.getInputStream()));
            out2 = new PrintWriter(this.serverSocket.getOutputStream(), true);
            boolean conversationWithSensor = true;

            while(true){
                if(conversationWithSensor){
                    String msg = in.readLine();
                    System.out.println(msg);

                    // the driver asks for the best route so computer asks him for start & end points
                    if(msg.contains("route")){
                        out.println("gimme start & end points");
                    }
                    // driver sends start and end doints
                    else if(msg.contains("point")){
                        out2.println(msg);
                        conversationWithSensor = false;
                    }
                    // driver sends sensor readings
                    else if(msg.contains("car")){
                        out2.println(msg);
                        conversationWithSensor = false;
                    }
                    // dirver chooses to end the communication
                    else if(msg.contains("0")){
                        break;
                    }
                }else{
                    String msg = in2.readLine();
                    // server asks for sensors readings
                    if(msg.contains("readings")){
                        out.println(msg);
                        conversationWithSensor = true;
                    }
                    // server sends best route
                    else if(msg.contains("best")){
                        out.println(msg);
                        break;
                    }
                }
            }
            out.println("Done!");

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
