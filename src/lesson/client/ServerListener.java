package lesson.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener extends BaseListener implements Runnable {


    public ServerListener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
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
            System.out.println("Client has been closed");
            System.exit(1);
        } finally {
            close();
        }
    }
}
