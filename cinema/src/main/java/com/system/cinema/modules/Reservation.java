package com.system.cinema.modules;

import com.system.cinema.dao.ReservationDao;
import com.system.cinema.exceptions.DaoException;

public class Reservation {

    // payment

    // reservation controller -> send email
    //                        -> print ticket
    // reservation controller -> movieDao customerDao

    private ReservationDao dao = ReservationDao.getDao();
    private int id;
    private String customerName;
    private String movieTitle;
    private int hallNumber;
    private String column;
    private int row;
    private String status;

    public Reservation(int id, String customerName, String movieTitle, int hallNumber, String column, int row) {
        this.id = id;
        this.customerName = customerName;
        this.movieTitle = movieTitle;
        this.hallNumber = hallNumber;
        this.column = column;
        this.row = row;
        this.status = "new";
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public int getHallNumber() {
        return hallNumber;
    }

    public String getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("reservation num:%7d\nmovie:%7s\nhall num:%7d\ncolumn:%7s\nrow:%7d\nowner:%7s"
                , id, movieTitle, hallNumber, column, row, customerName);
    }

    public void save(int movieID, int customerID) throws DaoException {
        dao.save(this, movieID, customerID);
    }

    public void remove() throws DaoException {
        dao.remove(this.id);
    }

    public void restartStatus() {
        this.status = "new";
    }

    public boolean checkout() {
        if (this.status.equalsIgnoreCase("new")) {
            this.status = "checked";
            return true;
        }
        return false;
    }

    public boolean pay() {
        if (this.status.equalsIgnoreCase("checked")) {
            this.status = "paid";
            return true;
        }
        return false;
    }
}
