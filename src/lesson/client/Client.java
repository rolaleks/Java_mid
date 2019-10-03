package lesson.client;

import java.io.*;
import java.net.*;

public class Client {


    public Client(String address, int port) {

        ServerListener serverListener = null;
        Thread thread = null;
        try {

            Socket socket = new Socket(address, port);
            serverListener = new ServerListener(socket);
            thread = new Thread(serverListener);
            thread.start();

        } catch (IOException e) {
            System.out.println("Client has NOT been started, please run SERVER first");
            System.exit(1);
        }
        System.out.println("Client has been started");



        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {
                String str = reader.readLine();
                if (str.equalsIgnoreCase("exit")) {
                    serverListener.close();
                    break;
                } else {
                    if (serverListener.isConnected()) {
                        serverListener.send(str);
                    } else {
                        break;
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
