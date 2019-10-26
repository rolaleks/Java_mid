package client;


import java.io.*;
import java.net.Socket;

public class ServerListener extends BaseListener{


    public ServerListener(Socket socket) {

        this.socket = socket;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
