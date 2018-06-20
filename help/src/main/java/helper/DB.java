package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

    private static final String DB_URL = "jdbc:sqlite:help/src/main/resources/start.db";
    private static Connection conn = null;

    private DB() {
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
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        DB.getConn();
        DB.create();
        DB.insert();
        DB.close();

    }





//
//    private static String DRIVER = "org.sqlite.JDBC";
//    private static final String DB_URL = "jdbc:sqlite:help/src/main/resources/start.db";
//
//    private Connection conn;
//    private Statement stat;
//
//    public DB() {
//        try {
//            Class.forName(DB.DRIVER);
//        } catch (ClassNotFoundException e) {
//            System.out.println("Invalid driver");
//        }
//
//        try {
//            this.conn = DriverManager.getConnection(DB.DB_URL);
//            this.stat = this.conn.createStatement();
//            this.conn.setAutoCommit(false);
//        } catch (SQLException e) {
//            System.out.println("invalid url");
//        }
//    }
//
//    public void createT() {
//        String command = "create table person(name txt, age int);";
//        try {
//            this.stat.execute(command);
//        } catch (SQLException e) {
//            System.out.println("cannot create");
//        }
//    }
//
//    public void insert() {
//        String command = "insert into person values('ziemo',33)";
//        try {
//            this.stat.executeUpdate(command);
//        } catch (SQLException e) {
//            System.out.println("cannot upgrade");
//        }
//    }
//
//    public void close() {
//        try {
//            this.conn.commit();
//            this.stat.close();
//            this.conn.close();
//        } catch (SQLException e) {
//            System.out.println("cannot close");
//        }
//    }
//
//    public static void main(String[] args) {
//        DB db = new DB();
//        db.createT();
//        db.insert();
//        db.close();
//    }
}
