package ru.geekbrains.chat.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Message extends Record {

    private int id;
    private int user_id;
    private String message;
    private String created_at;
    private String nickname;


    public static String getTableName() {
        return "massage";
    }

    protected boolean insert() {

        int id = DBService.executeInsert("INSERT INTO " + this.getTableName() + " (user_id,message)" +
                "VALUES(?,?)", this.user_id, this.message);

        boolean success = id != 0;
        if (success) {
            this.isNewRecord = false;
            this.id = id;
        }
        return success;
    }

    protected boolean update() {

        int result = DBService.executeUpdate("UPDATE " + this.getTableName() + " SET user_id = ?, message = ?" +
                "WHERE id = ?", this.user_id, this.message, this.id);

        return result == 1;
    }

    public boolean delete() {

        int result = DBService.executeUpdate("DELETE FROM " + this.getTableName() + " WHERE id = ?", this.id);
        return result == 1;
    }

    public static Message findOne(Integer id) {

        ResultSet resultSet = DBService.executeQuery("SELECT * FROM " + getTableName() + " WHERE id = ?", id);
        try {
            if (resultSet.next()) {
                Message message = new Message();
                message.prepareModel(resultSet);


                return message;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return null;
    }

    public static ArrayList<Message> findAll(int user_id, int limit) {

        ResultSet resultSet = DBService.executeQuery(
                "SELECT message.*, user.nickname FROM " + getTableName() + " as message " +
                        "LEFT JOIN " + Student.getTableName() + " as user on user.id = message.user_id " +
                        "LEFT JOIN black_list as blocked_user on blocked_user.user_id = ? and blocked_user.blocked_user_id = user.id " +
                        " WHERE blocked_user.blocked_user_id IS NULL" +
                        " ORDER BY message.created_at ASC LIMIT ?"
                , user_id, limit);

        ArrayList<Message> messages = new ArrayList<>();
        try {

            while (resultSet.next()) {
                Message message = new Message();
                message.prepareModel(resultSet);
                message.nickname = resultSet.getString("nickname");


                messages.add(message);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return messages;
    }

    public void prepareModel(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.message = resultSet.getString("message");
        this.user_id = resultSet.getInt("user_id");
        this.created_at = resultSet.getString("created_at");
        this.isNewRecord = false;
    }

    public static void createTable() {
        DBService.executeUpdate("CREATE TABLE IF NOT EXISTS " + getTableName() + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER NOT NULL," +
                "message TEXT," +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY(user_id) REFERENCES " + Student.getTableName() + "(id)" +
                ");");
    }


    public static void dropTable() {
        DBService.executeUpdate("DROP TABLE IF EXISTS " + getTableName());
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public int getId() {
        return id;
    }


    public String getNickname() {
        return nickname;
    }
}

