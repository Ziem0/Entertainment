package com.system.cinema.dao;

import com.system.cinema.exceptions.DaoException;
import com.system.cinema.modules.Seat;

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

    public void save(int movieID, Seat seat) throws DaoException {
        String column = seat.getColumn();
        int row = seat.getRow();

        try {
            preparedStatement = conn.prepareStatement("INSERT INTO bookedSeat(movieID, column, row) VALUES(?,?,?);");
            preparedStatement.setInt(1, movieID);
            preparedStatement.setString(2, column);
            preparedStatement.setInt(3, row);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException(this.getClass().getName() + " class caused a problem");
        }
    }

    public void remove(int movieID, Seat seat) throws DaoException {
        String column = seat.getColumn();
        int row = seat.getRow();
        try {
            preparedStatement = conn.prepareStatement("DELETE FROM bookedSeat WHERE `movieID` IS ? AND `row` IS ? AND `column` IS ?;");
            preparedStatement.setInt(1, movieID);
            preparedStatement.setInt(2, row);
            preparedStatement.setString(3, column);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException(this.getClass().getName() + " class caused a problem");
        }
    }
}
