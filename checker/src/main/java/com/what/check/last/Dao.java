package com.what.check.last;

import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {

    private static Connection conn = null;
    private static final String DB_URL = "jdbc:sqlite:checker/src/main/resources/last.db";

    private Dao() {
    }

    public static Connection getConn() {
        if (conn == null) {
            synchronized (Dao.class) {
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
            e.printStackTrace();
        }
    }

    public static void closeConn() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void migrate() {
        Flyway fw = new Flyway();
        fw.setDataSource(DB_URL, null, null);
        fw.migrate();
    }
}

