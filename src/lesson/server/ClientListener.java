package lesson.server;

import lesson.client.BaseListener;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientListener extends BaseListener implements Runnable {

    private ServerSocket serverSocket;

    public ClientListener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {

        try {
            socket = serverSocket.accept();
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            while (true) {
                String str = in.readUTF();
                System.out.println(str);
            }
        } catch (IOException e) {
            System.out.println("Server has been closed");
            System.exit(1);
        } finally {
            close();
        }
    }
}
