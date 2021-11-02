import java.io.*;
import java.net.*;
import java.util.Random;

public class Sensor {

    BufferedReader in;
    PrintWriter out;
    Random rand;

    public Sensor(){
        this.rand = new Random();;
    }

    void communicateWithComputer() throws Exception{

        // send requests on port 9806
        Socket soc = new Socket("127.0.0.1", 9806);
        in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        out = new PrintWriter(soc.getOutputStream(), true);
        out.println("What is the best route?");
        while(true){
            String msg = in.readLine();
            System.out.println(msg);
            // Computer asks for start & end points
            if(msg.contains("points")){
                // Sent start & end points to computer
                String lat = String.valueOf(rand.nextFloat() + 30);
                String lng = String.valueOf(rand.nextFloat() + 30);

                out.println("point:"+lat+","+lng);
            }
            // Computer asks sensor readings
            else if(msg.contains("readings")) {
                // Sent sensor readings to computer
                out.println("120car&15km,80car&30km,50car&39km");
            }
            // Client gets best route from computer
            else{
                System.out.println("Best route: " + msg);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Sensor sensorClient = new Sensor();
        sensorClient.communicateWithComputer();
    }
}