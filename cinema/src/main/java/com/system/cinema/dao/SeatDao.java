package com.system.cinema.dao;

import com.system.cinema.exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SeatDao {

    private static SeatDao dao = null;
    private Connection conn;
    private PreparedStatement preparedStatement = null;

    private SeatDao() {
        this.conn = ConnectionDao.getConn();
    }

    public static SeatDao getDao() {
        if (dao == null) {
            synchronized (SeatDao.class) {
                if (dao == null) {
                    dao = new SeatDao();
                }
            }
        }
        return dao;
    }

    public HashMap<String, List<Integer>> getBookedSeats(int movieID) throws DaoException {
        HashMap<String, List<Integer>> bookedSeats = new HashMap<>();
        try {
            preparedStatement = conn.prepareStatement("SELECT `column`, `row` FROM bookedSeat WHERE movieID IS ?;");
            preparedStatement.setInt(1, movieID);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                String column = result.getString(1);
                Integer row = result.getInt(2);
                bookedSeats.put(column, Collections.singletonList(row));
            }
            result.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException(this.getClass().getName() + " class caused a problem");
        }
        return bookedSeats;
    }


    public static void main(String[] args) throws DaoException {
        SeatDao dao = SeatDao.getDao();
        dao.getBookedSeats(1).forEach((a, b) -> System.out.println(a + " " + b.get(0)));
    }
}
