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


    public static void main(String[] args) throws DaoException {
        MovieDao dao = MovieDao.getDao();
        Movie m = dao.getMovie(2);
        System.out.println(m.getTitle());
        System.out.println(m.getHall().calculateAvailableSeats());
        m.getHall().showHall();
    }

}

