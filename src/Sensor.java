import java.io.*;
import java.net.*;

public class Sensor {


    void communicateWithComputer() throws Exception{

        // send requests on port 9806
        Socket soc = new Socket(ipAddress, 9806);
        in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        out = new PrintWriter(soc.getOutputStream(), true);

        while(true){
            String msg = in.readLine();
            // Computer asks for start & end points
            if(msg.contains("points")){
                // Sent start & end points to computer
                out.println("point:31.3,30.9");
            }
            // Computer asks sensor readings
            else if(msg.equals("readings")) {
                // Sent sensor readings to computer
                out.println("120car&15km,80car&30km,50car&39km");
            }
            // Client gets best route from computer
            else{
                System.out.println("Best route: " + msg)
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Sensor sensorClient = new Sensor();
        sensorClient.communicateWithComputer();
    }
}