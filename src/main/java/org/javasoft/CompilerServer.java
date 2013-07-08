package org.javasoft;

import java.net.Socket;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.DataOutputStream;
import java.util.Arrays;

public class CompilerServer extends Thread {
    private ServerSocket serverSocket;
    private Compiler compiler;

    public CompilerServer(int port) throws IOException {
       serverSocket = new ServerSocket(port);
       compiler = new Compiler();
    }
 
    public static void main(String[] args) {
        ServerParams params = new ServerParams();
        try {
           Thread t = new CompilerServer(params.getPort());
           t.start();
        } catch(IOException e) {
           e.printStackTrace();
        }
    }

    public void run() {
        String output = "";
        while(true) {
           try {
              System.out.println("Compiler waiting on port " + serverSocket.getLocalPort() + "...");
              Socket server = serverSocket.accept();
              System.out.println("Just connected to " + server.getRemoteSocketAddress());
              ObjectInputStream in = new ObjectInputStream(server.getInputStream());
              Object obj = in.readObject();
          
              if (obj instanceof String) {
                  if (((String)obj).equalsIgnoreCase("quit")) {
                      System.out.println("Bye bye.");
                      output = "Bye bye.";
                      writeResponse(server, output);
                      break;
                  }
              }

              System.out.println(Arrays.toString((String[])obj));
	          output = compiler.compile(new CompilerParams((String[]) obj));
              writeResponse(server, output);
              server.close();
           } catch(SocketTimeoutException s) {
              System.out.println("Socket timed out!");
              break;
           } catch(IOException e) {
              e.printStackTrace();
              break;
           } catch(ClassNotFoundException e) {
              e.printStackTrace();
              break;
           }
        }
    }

    private void writeResponse(Socket server, String output) throws IOException {
        DataOutputStream out = new DataOutputStream(server.getOutputStream());
        //    out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!");
        out.writeUTF(output);
    }
}
