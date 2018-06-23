package com.what.check;

import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDao {

    private static Connection conn = null;
    private static final String DB_URL = "jdbc:sqlite:checker/src/main/resources/mentors.db";

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
            System.out.println("incorrect db url");
        }
    }

    public static void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("incorrect close");
        }
    }

    public static void migrate() {
        Flyway fw = new Flyway();
        fw.setDataSource(DB_URL, null, null);
        fw.migrate();
    }
}
