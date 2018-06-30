package com.system.cinema.dao;

import com.system.cinema.exceptions.DaoException;
import com.system.cinema.modules.Hall;
import com.system.cinema.modules.Movie;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class MovieDao {

    private static MovieDao dao = null;
    private Connection conn;
    private PreparedStatement preparedStatement = null;

    private MovieDao() {
        this.conn = ConnectionDao.getConn();
    }

    public static MovieDao getDao() {
        if (dao == null) {
            synchronized (MovieDao.class) {
                if (dao == null) {
                    dao = new MovieDao();
                }
            }
        }
        return dao;
    }

    public Movie getMovie(int idE) throws DaoException {
        Movie movie = null;
        try {
            preparedStatement = conn.prepareStatement("select movie.*, hall.* from movie join hall on movie.hallID = hall.id where movie.id = ?;");
            preparedStatement.setInt(1, idE);
            ResultSet result = preparedStatement.executeQuery();
            movie = this.createMovie(result);
            result.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException(this.getClass().getName() + " class caused a problem");
        }
        return movie;
    }

    public List<Movie> getMovies() throws DaoException {
        List<Movie> movieList = new LinkedList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery("select movie.*, hall.* from movie join hall on movie.hallID = hall.id;");
            while (result.next()) {
                movieList.add(createMovie(result));
            }
            result.close();
            st.close();
        } catch (SQLException e) {
            throw new DaoException(this.getClass().getName() + " class caused a problem");
        }
        return movieList;
    }

    private Movie createMovie(ResultSet result) throws SQLException, DaoException {
        int id = result.getInt(1);
        String title = result.getString(2);
        String description = result.getString(3);
        String[] date = result.getString(5).split("-");
        String[] time = result.getString(6).split("-");
        LocalDate localDate = LocalDate.of(Integer.valueOf(date[0]),Integer.valueOf(date[1]),Integer.valueOf(date[2]));
        LocalTime localTime = LocalTime.of(Integer.valueOf(time[0]),Integer.valueOf(time[1]));
        Hall hall = new Hall(result.getInt(7), result.getInt(8), result.getInt(9));
        hall.updateBookedSeats(id);
        return new Movie(id, title, description, hall, localDate, localTime );
    }

    public void save(Movie movie) throws DaoException {
        String title = movie.getTitle();
        String description = movie.getDescription();
        int hallID = movie.getHall().getId();
        String date = movie.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String hour = movie.getHour().format(DateTimeFormatter.ofPattern("H-m"));
        try {
            preparedStatement = conn.prepareStatement("insert into movie(title, description, hallID, date, hour) values(?,?,?,?,?)");
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,description);
            preparedStatement.setInt(3,hallID);
            preparedStatement.setString(4,date);
            preparedStatement.setString(5, hour);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException(this.getClass().getName() + " class caused a problem");
        }

    }

    public void update(Movie movie) throws DaoException {
        String title = movie.getTitle();
        String description = movie.getDescription();
        int hallID = movie.getHall().getId();
        String date = movie.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String hour = movie.getHour().format(DateTimeFormatter.ofPattern("HH-mm"));
        try {
            preparedStatement = conn.prepareStatement("UPDATE movie SET title=?, description=?, hallID=?,date=?, hour=?");
            preparedStatement.setString(1,title);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, hallID);
            preparedStatement.setString(4, date);
            preparedStatement.setString(5, hour);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException(this.getClass().getName() + " class caused problem");
        }
    }

    public void remove(int id) throws DaoException {
        try {
            preparedStatement = conn.prepareStatement("DELETE FROM movie WHERE id IS ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException(this.getClass().getName() + " class caused problem");
        }
    }
}
