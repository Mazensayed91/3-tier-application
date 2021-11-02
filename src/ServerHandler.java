import java.io.*;
import java.net.*;

class ServerHandler extends Thread{

    Socket socket;
    BufferedReader in;
    PrintWriter out;

    public ServerHandler(Socket socket) throws IOException{
        this.socket = socket;
    }

    public void run(){
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            while(true){
                String msg = in.readLine();
                System.out.println(msg);
                System.out.println(msg.contains("car"));

                // the driver asks for the best route so computer asks him for start & end points
                if(msg.contains("point")){
                    out.println("get sensors readings!");
                }
                // dirver chooses to end the communication
                else if(msg.equals("0")){
                    break;
                }
                // computer node sends sensor readings
                else if(msg.contains("car")){
                    System.out.println("hhhhh");
                    out.println("best t7t el control");
                }
            }
            out.println("Done!");

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
