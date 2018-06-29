package com.system.cinema.dao;

import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDao {

    private static final String DB_URL = "jdbc:sqlite:cinema/src/main/resources/database.db";
    private static Connection conn = null;

    private ConnectionDao() {
    }

    public static Connection getConn() {
        if (conn == null) {
            synchronized (ConnectionDao.class) {
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
            System.out.println("Incorrect DB URL");
        }
    }

    public static void close() {
        try {
            getConn().close();
        } catch (SQLException e) {
            System.out.println("Incorrect close connection");
        }
    }

    public static void migrate() {
        Flyway fw = new Flyway();
        fw.setDataSource(DB_URL, null, null);
        fw.migrate();
    }

}
