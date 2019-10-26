package server;

import client.BaseListener;
import client.SerializableClass;

import java.io.*;
import java.net.ServerSocket;

public class ClientListener extends BaseListener implements Runnable {

    private ServerSocket serverSocket;

    public ClientListener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {

        try {

            socket = serverSocket.accept();
            in = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            SerializableClass s = (SerializableClass)in.readObject();
            s.info();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Server has been closed");
            System.exit(1);
        } finally {
            close();
        }
    }
}
