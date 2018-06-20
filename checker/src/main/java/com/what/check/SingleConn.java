package com.what.check;

import org.flywaydb.core.Flyway;
import org.sqlite.core.DB;

import javax.swing.plaf.nimbus.State;
import java.io.File;
import java.sql.*;
import java.util.Scanner;

public class SingleConn {

    private static final String DB_URL = "jdbc:sqlite:checker/src/main/resources/start.db";
    private static Connection conn = null;

    private SingleConn() {
    }

    public static Connection getConn() {
        if (conn == null) {
            synchronized (SingleConn.class) {
                if (conn == null) {
                    startConnection();
                }
            }
        }
        return conn;
    }

    private static void startConnection() {
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println("invalid url");
        }
    }

    public static void create() {
        String command = "create table if not exists person(name txt, age int);";
        try {
            Statement stat = conn.createStatement();
            stat.execute(command);
        } catch (SQLException e) {
            System.out.println("cannot create statement");
        }
    }

    public static void insert() {
        String command = "insert into person values('ziemo',33);";
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate(command);
        } catch (SQLException e) {
            System.out.println("cannot create statement");
        }
    }

    public static void update() {
        String command = "update person set name='Ziemowit' where name='ziemo'";
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert2() {
        Scanner sc = new Scanner(System.in);
        String command = "insert into person values(?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(command);
            preparedStatement.setString(1, sc.next());
            preparedStatement.setInt(2, sc.nextInt());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getAll() {
        String query = "select * from person";
        select(query);
    }

    private static void select(String query) {
        try {
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(query);
            ResultSetMetaData meta = result.getMetaData();
            int attrV = meta.getColumnCount();
            for (int i = 1; i <= attrV; i++) {
                System.out.printf("%-10s",meta.getColumnLabel(i));
            }
            while (result.next()) {
                System.out.printf("\n%-10s %-10s",result.getString(1),result.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dbMigration() {
        Flyway fw = new Flyway();
        fw.setDataSource(DB_URL, "ziemo", null);
        fw.migrate();
    }

    public static void main(String[] args) {
        dbMigration();
//        getConn();
//        create();
//        insert();
//        update();
//        getAll();
        close();
    }
}

// sql cwiczenia
