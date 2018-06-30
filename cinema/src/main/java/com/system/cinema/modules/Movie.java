package com.system.cinema.modules;

import com.system.cinema.dao.MovieDao;
import com.system.cinema.exceptions.DaoException;

import java.time.LocalDate;
import java.time.LocalTime;

public class Movie {

    private Integer id;
    private String title;
    private String description;
    private Hall hall;
    private LocalDate date;
    private LocalTime hour;


    public Movie(Integer id, String title, String description, Hall hall, LocalDate localDate, LocalTime localTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.hall = hall;
        this.date = localDate;
        this.hour = localTime;
    }

    public Movie(String title, String description, Hall hall, LocalDate localDate, LocalTime localTime) {
        this.title = title;
        this.description = description;
        this.hall = hall;
        this.date = localDate;
        this.hour = localTime;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Hall getHall() {
        return hall;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void bookSeat(String column, int row) throws DaoException {
        this.getHall().bookSeat(column, row, this.id);
    }

    public void unBookSeat(String column, int row) throws DaoException {
        this.getHall().unBookSeat(column, row, this.id);
    }



    public static void main(String[] args) throws DaoException {
        MovieDao dao = MovieDao.getDao();
        Movie m = dao.getMovie(2);
        m.bookSeat("A", 1);
        m.bookSeat("B", 1);
//        m.unBookSeat("A",1);
//        m.unBookSeat("B",1);
        System.out.println(m.getTitle());
        System.out.println(m.getHall().calculateAvailableSeats());
        m.getHall().showHall();
    }

}

