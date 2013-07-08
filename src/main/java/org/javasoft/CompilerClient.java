package org.javasoft;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.DataInputStream;

public class CompilerClient {

    public static void main(String[] args) {
        CompilerClient client = new CompilerClient();
        client.send(args);
    }
  
    private void send(Object args) {
        ServerParams params = new ServerParams();
        String serverName = params.getServerName();
        int port = params.getPort();
        System.out.println("Connecting to " + serverName + " on port " + port);
        try {
            Socket client = new Socket(serverName, port);
            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
 //           out.writeObject("Hello from " + client.getLocalSocketAddress());
            out.writeObject(args);
            DataInputStream in = new DataInputStream(client.getInputStream());
            System.out.println(in.readUTF());
            client.close();
        } catch(IOException e) {
           e.printStackTrace();
        }
    }

    public void sendQuit() {
       send("quit"); 
    }
}
