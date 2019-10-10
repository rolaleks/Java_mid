package ru.geekbrains.chat.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private static Connection connection;
    private static Statement stmt;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:mainDB.db");
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addUser(String login, String pass, String nick) {
        try {
            String query = "INSERT INTO main (login, password, nickname) VALUES (?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, login);
            ps.setInt(2, pass.hashCode());
            ps.setString(3, nick);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String pass) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT nickname, password FROM main WHERE login = '" + login + "'");
            int myHash = pass.hashCode();
            if (rs.next()) {
                String nick = rs.getString(1);
                int dbHash = rs.getInt(2);
                if (myHash == dbHash) {
                    return nick;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean addToBlack(String nick, String blockedNick) {
        try {
            //Тк мы не храним ID юзеров в классах, чтобы не делать несколько запросомв, ищем их id и вставляев в таблицу одим запросом
            String query = "INSERT INTO black_list (user_id, blocked_user_id) VALUES ((SELECT id from main where nickname = ?), (SELECT id from main where nickname = ?));";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nick);
            ps.setString(2, blockedNick);
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }

    public static boolean removeFromBlack(String nick, String blockedNick) {
        try {
            //Тк мы не храним ID юзеров в классах, чтобы не делать несколько запросомв, ищем их id и удаляем из таблицу одим запросом
            String query = "DELETE FROM black_list WHERE user_id IN (SElECT id from main where nickname = ?) and blocked_user_id in (SElECT id from main where nickname = ?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nick);
            ps.setString(2, blockedNick);
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static List<String> getBlocked(String nick) {
        List<String> blackList = new ArrayList<>();

        try {
            String query = "SELECT blocked_user.* from main as blocked_user INNER JOIN black_list as bl on bl.blocked_user_id = blocked_user.id INNER JOIN main as user on bl.user_id = user.id WHERE user.nickname = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nick);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                blackList.add(rs.getString("nickname"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blackList;
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
