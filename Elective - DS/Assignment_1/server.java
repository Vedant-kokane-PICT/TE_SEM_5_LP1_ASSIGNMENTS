import java.io.*;
import java.net.*;
import java.util.*;

public class server{
    public static void main(String[] args){
        try{
            ServerSocket ss = new ServerSocket(6666);
            Socket s = ss.accept();
            DataInputStream in = new DataInputStream(s.getInputStream());
            String line = "";
            System.out.println("Connection is Successfully established");
            while(!line.equals("over")){
                try{
                    line = in.readUTF();
                    System.out.println(line);
                }
                catch(IOException i){
                    System.out.println(i);
                }
            }
            System.out.println("Connection is Successfully established");
            ss.close();
        }
        catch(Exception e){
             System.out.println(e);
        }
    }
}
