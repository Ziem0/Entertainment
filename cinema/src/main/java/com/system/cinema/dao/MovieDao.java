package com.system.cinema.dao;

import com.system.cinema.exceptions.DaoException;
import com.system.cinema.modules.Hall;
import com.system.cinema.modules.Movie;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
        this.getMovies().forEach(a -> System.out.printf("\nid:%d title:%s\n", a.getId(), a.getTitle()));
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

    private Movie createMovie(ResultSet result) throws SQLException {
        int id = result.getInt(1);
        String title = result.getString(2);
        String description = result.getString(3);
        String[] date = result.getString(5).split("-");
        String[] time = result.getString(6).split("-");
        LocalDate localDate = LocalDate.of(Integer.valueOf(date[0]),Integer.valueOf(date[1]),Integer.valueOf(date[2]));
        LocalTime localTime = LocalTime.of(Integer.valueOf(time[0]),Integer.valueOf(time[1]));
        Hall hall = new Hall(result.getInt(7), result.getInt(8), result.getInt(9));

        return new Movie(id, title, description, hall, localDate, localTime );
    }



    //save update


    public static void main(String[] args) throws DaoException {
        dao = getDao();
        System.out.println(dao.getMovie(1).getTitle());
    }
}
