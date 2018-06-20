package com.what.check;

import org.sqlite.core.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SingleConn {

    private static final String DB_URL = "jdbc:sqlite:checker/src/main/resources/start.db";
    private static Connection conn = null;

    private SingleConn() {
    }

    public static Connection getConn() {
        if (conn == null) {
            synchronized (DB.class) {
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

    public static void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getConn();
        create();
        insert();
        close();
    }
}

// dokonczyc metody select itd
// flyway
// sql cwiczenia
