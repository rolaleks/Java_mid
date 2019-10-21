package ru.geekbrains.chat.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Student extends Record {


    private int id;
    private String login;
    private String password;
    private String nickname;

    public static String getTableName() {
        return "main";
    }

    public boolean save() {
        if (this.isNewRecord) {
            return insert();
        } else {
            return update();
        }
    }

    protected boolean insert() {

        int id = DBService.executeInsert("INSERT INTO " + this.getTableName() + " (login,password ,nickname)" +
                "VALUES(?,?,?)", this.login, this.password, this.nickname);

        boolean success = id != 0;
        if (success) {
            this.isNewRecord = false;
            this.id = id;
        }
        return success;
    }

    protected boolean update() {

        int result = DBService.executeUpdate("UPDATE " + this.getTableName() + " SET login = ?, password = ? ,nickname =?" +
                "WHERE id = ?", this.login, this.password, this.nickname, this.id);

        return result == 1;
    }

    protected boolean delete() {

        int result = DBService.executeUpdate("DELETE FROM " + this.getTableName() + " WHERE id = ?", this.id);
        return result == 1;
    }

    public static Student findOne(Integer id) {

        ResultSet resultSet = DBService.executeQuery("SELECT * FROM " + getTableName() + " WHERE id = ? LIMIT 1", id);
        try {
            if (resultSet.next()) {
                Student student = new Student();
                student.prepareModel(resultSet);


                return student;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return null;
    }

    public static Student findOneByNick(String nick) {

        ResultSet resultSet = DBService.executeQuery("SELECT * FROM " + getTableName() + " WHERE nickname = ? LIMIT 1", nick);
        try {
            if (resultSet.next()) {
                Student student = new Student();
                student.prepareModel(resultSet);

                return student;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return null;
    }


    public static ArrayList<Student> findAll() {

        ResultSet resultSet = DBService.executeQuery("SELECT * FROM " + getTableName());

        ArrayList<Student> students = new ArrayList<>();
        try {

            while (resultSet.next()) {
                Student student = new Student();
                student.prepareModel(resultSet);


                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return students;
    }



    public void prepareModel(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.login = resultSet.getString("login");
        this.nickname = resultSet.getString("nickname");
        this.password = resultSet.getString("password");
        this.isNewRecord = false;
    }

    public static void createTable() {
        DBService.executeUpdate("CREATE TABLE IF NOT EXISTS " + getTableName() + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "login TEXT UNIQUE," +
                "password TEXT," +
                "nickname TEXT UNIQUE" +
                ");");
    }

    public static void dropTable() {
        DBService.executeUpdate("DROP TABLE IF EXISTS " + getTableName());
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }
}
