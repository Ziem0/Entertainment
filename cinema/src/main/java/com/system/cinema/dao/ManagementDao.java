package com.system.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagementDao {

    private static ManagementDao dao = null;
    private final Connection conn;
    private PreparedStatement preparedStatement = null;

    private ManagementDao() {
        conn = ConnectionDao.getConn();
    }

    public static ManagementDao getDao() {
        if (dao == null) {
            synchronized (ManagementDao.class) {
                if (dao == null) {
                    dao = new ManagementDao();
                }
            }
        }
        return dao;
    }

    public boolean getAccess(String login, String password) throws SQLException {
        preparedStatement = conn.prepareStatement("select * from management where login is ? and password is ?");
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        ResultSet result = preparedStatement.executeQuery();
        return result.next();
    }
}
