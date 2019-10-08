package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private MainServ serv;

    private String nick;

    public ClientHandler(MainServ serv, Socket socket) {
        try {
            this.serv = serv;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/auth")) {
                                String[] tokens = str.split(" ");
                                String currentNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);

                                if (currentNick != null) {
                                    if (serv.findClient(currentNick) != null) {
                                        sendMsg(currentNick + " Уже авторизован");
                                        continue;
                                    }
                                    sendMsg("/authok");
                                    nick = currentNick;
                                    serv.subscribe(ClientHandler.this);
                                    break;
                                } else {
                                    sendMsg("неверный логин/пароль");
                                }
                            }
                        }

                        while (true) {
                            String str = in.readUTF();
                            if (str.equalsIgnoreCase("/end")) {
                                sendMsg("/clientClose");
                                break;
                            }
                            Matcher m = Pattern.compile("^/w\\s([a-zA-Z0-9]+?)\\s(.+?)$").matcher(str);
                            if (m.find()) {
                                String nick = m.group(1);
                                String msg = m.group(2);
                                if (!serv.sentToClient(nick, ClientHandler.this.nick + ": " + msg)) {
                                    sendMsg("Сообщение не доставлено");
                                }
                                continue;
                            }
                            serv.broadcastMsg(nick + ": " + str);
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        serv.unsubscribe(ClientHandler.this);
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasNick(String nick) {

        return this.nick.equalsIgnoreCase(nick);
    }
}
