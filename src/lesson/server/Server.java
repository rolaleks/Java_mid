package lesson.server;

import lesson.client.BaseListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Server {

    public Server() {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(7777);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server has NOT been started");
            System.exit(1);
        }
        System.out.println("Server has been started");

        ClientListener clientListener = new ClientListener(serverSocket);
        Thread thread = new Thread(clientListener);
        thread.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {
                String str = reader.readLine();
                if (str.equalsIgnoreCase("exit")) {
                    clientListener.close();
                    break;
                } else {
                    if (clientListener.isConnected()) {
                        clientListener.send(str);
                    } else {
                        System.out.println("Client is not connected");
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
