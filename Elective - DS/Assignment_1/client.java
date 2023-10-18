// Vedant Kokane
// 31430

import java.io.*;
import java.net.*;
import java.util.*;
import java.io.DataOutputStream;


public class client{
    public static void main(String[] args){
        try{
            Scanner inp = new Scanner (System. in) ;
            Socket s = new Socket ("localhost" , 6666);
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            String line = "";
            while (!line.equals("over")){
                try{
                    line = inp.nextLine();
                    out.writeUTF(line);
                }
                catch(IOException i){
                    System.out.println(i);
                }
            }
            out.flush();
            out.close();
            s.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
