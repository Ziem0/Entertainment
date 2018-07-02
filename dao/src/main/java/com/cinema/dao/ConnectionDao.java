package com.cinema.dao;

import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDao {

    private static Connection conn = null;
    private static final String DB_URL = "jdbc:sqlite:dao/src/main/resources/cinemaData.db";

    private ConnectionDao() {
    }

    public static Connection getConn() {
        if (conn == null) {
            synchronized (ConnectionDao.class) {
                if (conn == null) {
                    createConnection();
                }
            }
        }
        return conn;
    }

    private static void createConnection() {
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println("ConnectionDao.createConnection error");
        }
    }

    public static void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("ConnectionDao.close error");
        }
    }

    public static void migrate() {
        Flyway fw = new Flyway();
        fw.setDataSource(DB_URL, null, null);
        fw.clean();
        fw.migrate();
    }

    public static void main(String[] args) {
        conn = getConn();
        migrate();
        close();c
    }
}
