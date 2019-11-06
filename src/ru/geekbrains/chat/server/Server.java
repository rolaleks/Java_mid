package ru.geekbrains.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.*;

public class Server {
    private Vector<ClientHandler> clients;
    private static final Logger logger;

    static {
        logger = Logger.getLogger("");
        logger.setLevel(Level.ALL);
        try {
            Handler handler = new FileHandler("server_log.log", true);
            handler.setLevel(Level.ALL);
            handler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                    return record.getLevel() + "\t" + record.getMessage() + "\t" + sdf + "\n";
                }
            });
            logger.addHandler(handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Server() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;
        try {
            AuthService.connect();
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен. Ожидаем клиентов...");
            logger.log(Level.INFO, "Сервер запущен");
            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                logger.log(Level.INFO, "Клиент подключился");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка сервера: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Ошибка закрытия сокета: " + e.getMessage());
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Ошибка закрытия сервера: " + e.getMessage());
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    public void sendPersonalMsg(ClientHandler from, String nickTo, String msg) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nickTo)) {
                logger.log(Level.FINE, from.getNick() + " отправил персональное сообщение " + nickTo + ": " + msg);
                o.sendMsg("from " + from.getNick() + ": " + msg);
                from.sendMsg("to " + nickTo + ": " + msg);
                return;
            }
        }
        from.sendMsg("Клиент с ником " + nickTo + " не найден в чате");
    }

    public void broadcastMsg(ClientHandler from, String msg) {
        logger.log(Level.FINE, from.getNick() + " отправил сообщение всем: " + msg);
        for (ClientHandler o : clients) {
            if (!o.checkBlackList(from.getNick())) {
                o.sendMsg(msg);
            }
        }
    }

    public boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public void broadcastClientsList() {
        StringBuilder sb = new StringBuilder();
        sb.append("/clientslist ");
        for (ClientHandler o : clients) {
            sb.append(o.getNick() + " ");
        }
        String out = sb.toString();
        for (ClientHandler o : clients) {
            o.sendMsg(out);
        }
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
        logger.log(Level.INFO, "Клиент вошел в чат");
        broadcastClientsList();
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
        logger.log(Level.INFO, "Клиент вышел из чата");
        broadcastClientsList();
    }

    public void log(String msg) {
        logger.log(Level.FINE, msg);
    }
}
