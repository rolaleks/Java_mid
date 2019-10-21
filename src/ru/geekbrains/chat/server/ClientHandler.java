package ru.geekbrains.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private String nick;
    private Student user;

    List<String> blackList;

    public String getNick() {
        return nick;
    }

    public ClientHandler(Server server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/auth")) { // /auth login72 pass72
                            String[] tokens = str.split(" ");
                            String newNick = DBService.getNickByLoginAndPass(tokens[1], tokens[2]);
                            if (newNick != null) {
                                if (!server.isNickBusy(newNick)) {
                                    this.user = Student.findOneByNick(newNick);
                                    sendMsg("/authok");
                                    nick = newNick;
                                    server.subscribe(this);
                                    server.sendMessagesList(this);
                                    break;
                                } else {
                                    sendMsg("Учетная запись уже используется");
                                }
                            } else {
                                sendMsg("Неверный логин/пароль");
                            }
                        }
                    }

                    this.blackList = DBService.getBlocked(nick);

                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/")) {
                            if (str.equals("/end")) {
                                out.writeUTF("/serverclosed");
                                break;
                            }
                            if (str.equals("/getmessages")) {

                               server.sendMessagesList(this);
                                break;
                            }
                            if (str.startsWith("/w ")) {
                                String[] tokens = str.split(" ", 3);
                                server.sendPersonalMsg(this, tokens[1], tokens[2]);
                            }
                            if (str.startsWith("/blacklist ")) {
                                String[] tokens = str.split(" ");
                                if (checkBlackList(tokens[1])) {
                                    sendMsg("Пользователь " + tokens[1] + " уже добавлен в черный список");
                                    continue;
                                }
                                if (DBService.addToBlack(this.nick, tokens[1])) {
                                    blackList.add(tokens[1]);
                                    sendMsg("Вы добавили пользователя " + tokens[1] + " в черный список");
                                } else {
                                    sendMsg("Неудачная попытка добавления пользователя " + tokens[1] + " в черный список");
                                }
                            }

                            if (str.startsWith("/blacklistremove ")) {
                                String[] tokens = str.split(" ");
                                if (DBService.removeFromBlack(this.nick, tokens[1])) {
                                    blackList.remove(tokens[1]);
                                    sendMsg("Вы удалили пользователя " + tokens[1] + " из черный список");
                                } else {
                                    sendMsg("Неудачная попытка удаления пользователя " + tokens[1] + " из черный список");
                                }
                            }
                        } else {
                            Message m = new Message();
                            m.setMessage(str);
                            m.setUserId(this.getUser().getId());
                            m.save();
                            server.broadcastMsg(this, nick + ": " + str);
                        }
                        System.out.println("Client: " + str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    server.unsubscribe(this);
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkBlackList(String nick) {
        return blackList.contains(nick);
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Student getUser() {
        return this.user;
    }
}
