package com.what.check;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MentorDao {

    private static MentorDao dao = null;
    private final Connection conn;
    private PreparedStatement preparedStatement = null;

    private MentorDao() {
        this.conn = ConnectionDao.getConn();
    }

    public static MentorDao getDao() {
        if (dao == null) {
            synchronized (MentorDao.class) {
                if (dao == null) {
                    dao = new MentorDao();
                }
            }
        }
        return dao;
    }

    public Person getMentor(String name, int age) {
        Person person = null;
        String query = "select * from mentor where name = ? and age = ?;";

        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                person = createPerson(result);
            }

            result.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    private Person createPerson(ResultSet result) throws SQLException {
        String name = result.getString("name");
        int age = result.getInt("age");
        return new Person(name, age);
    }

    public void save(Person person) {
        String name = person.getName();
        int age = person.getAge();

        String command = "insert into mentor values(?,?);";

        try {
            preparedStatement = conn.prepareStatement(command);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
