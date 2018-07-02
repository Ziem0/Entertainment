package com.system.cinema.dao;

import com.system.cinema.exceptions.DaoException;
import com.system.cinema.modules.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDao {

    private static ReservationDao dao = null;
    private Connection conn;
    private PreparedStatement preparedStatement;

    private ReservationDao() {
        this.conn = ConnectionDao.getConn();
    }

    public static ReservationDao getDao() {
        if (dao == null) {
            synchronized (ReservationDao.class) {
                if (dao == null) {
                    dao = new ReservationDao();
                }
            }
        }
        return dao;
    }

    public Reservation getReservationByName(String customerName, String column, int row) throws DaoException{
        Reservation reservation = null;
        try {
            preparedStatement = conn.prepareStatement("select reservation.id, customer.name,movie.title,hall.id " +
                    "from reservation " +
                    "join customer on reservation.customerID=customer.id " +
                    "join movie on reservation.movieID=movie.id " +
                    "where customer.name = ?;");
            preparedStatement.setString(1, customerName);
            ResultSet result = preparedStatement.executeQuery();
            createReservation(result, column, row);
            result.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException(this.getClass().getName() + " class caused a problem");
        }
        return reservation;
    }

    private Reservation createReservation(ResultSet result, String column, int row) throws SQLException {
        int reservationID = result.getInt(1);
        String customerName = result.getString(2);
        String movieTitle = result.getString(3);
        int hallID = result.getInt(4);
        return new Reservation(reservationID, customerName, movieTitle, hallID, column, row);
    }

    public void save(Reservation reservation, int movieID, int customerID) throws DaoException{
        try {
            preparedStatement = conn.prepareStatement("INSERT INTO reservation(`movieID`, `customerID`) VALUES(?,?);");
            preparedStatement.setInt(1,movieID);
            preparedStatement.setInt(2,customerID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException(this.getClass().getName() + " class caused a problem");
        }

    }

    public void remove(int idE) throws DaoException {
        try {
            preparedStatement = conn.prepareStatement("DELETE FROM reservation WHERE id = ?;");
            preparedStatement.setInt(1, idE);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException(this.getClass().getName() + " class caused a problem");
        }

    }
}
