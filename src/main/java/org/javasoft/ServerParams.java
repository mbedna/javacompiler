package org.javasoft;

public class ServerParams {

    private String serverName = "localhost";
    private int port = 7777;

    public String getServerName() {
        return serverName;
    }

    public int getPort() {
        return port;
    }

    public static int getPorcik(String arg) {
        int port = 7777;
        try { 
            port = Integer.parseInt(arg);
        } catch (RuntimeException e) {
            System.out.println("Using default port " + port);
        }
        return port;
    }   
}
