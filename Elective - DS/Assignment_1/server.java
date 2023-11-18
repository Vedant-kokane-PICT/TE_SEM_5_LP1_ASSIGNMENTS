// Vedant Kokane
// 31430

import java.io.*;
import java.net.*;
import java.util.*;

public class server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(6666);
            System.out.println("Server Connection is ready");

            while (true) {
                Socket s = ss.accept();
                System.out.println("New client connected: " + s);
                Thread t = new ClientHandler(s);
                t.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String line = "";
            while (!line.equals("over")) {
                try {
                    line = in.readUTF();
                    System.out.println("Received from " + socket + ": " + line);
                } catch (IOException i) {
                    System.out.println(i);
                    break;  // Exiting the loop if an exception occurs
                }
            }
            System.out.println("Client disconnected: " + socket);
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
