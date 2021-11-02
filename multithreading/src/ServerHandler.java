import java.io.*;
import java.net.*;
import java.util.Random;


class ServerHandler extends Thread{

    Socket socket;
    BufferedReader in;
    PrintWriter out;

    public ServerHandler(Socket socket) throws IOException{
        this.socket = socket;
    }

    public static String getRandom(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
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
                    // sample sensor data: 120car&15km,80car&30km,50car&39km
                    String[] data = msg.split(",");
                    String bestRoute = this.getRandom(data);
                    out.println("best path: " + bestRoute);
                }
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
