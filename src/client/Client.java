package client;



import java.io.*;
import java.net.*;

public class Client {


    public Client(String address, int port) {

        ServerListener serverListener = null;
        try {

            Socket socket = new Socket(address, port);
            serverListener = new ServerListener(socket);

        } catch (IOException e) {
            System.out.println("Client has NOT been started, please run SERVER first");
            System.exit(1);
        }
        System.out.println("Client has been started");

        SerializableClass s = new SerializableClass(1, 5);

        serverListener.send(s);
        serverListener.close();
    }
}
