package com.system.cinema.dao;

import com.entertainment.cipher.models.CesarEnigma;
import com.system.cinema.exceptions.DaoException;
import com.system.cinema.modules.Worker;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class WorkerDao {

    private static WorkerDao dao = null;
    private Connection conn;
    private PreparedStatement preparedStatement = null;

    private WorkerDao() {
        this.conn = ConnectionDao.getConn();
    }

    public static WorkerDao getDao() {
        if (dao == null) {
            synchronized (WorkerDao.class) {
                if (dao == null) {
                    dao = new WorkerDao();
                }
            }
        }
        return dao;
    }

    public Worker getWorker(String loginE, String passwordE) throws DaoException{
        Worker worker = null;
        try {
            preparedStatement = conn.prepareStatement("select * from worker where login is ? and password is ?");
            preparedStatement.setString(1, loginE);
            preparedStatement.setString(2, new CesarEnigma().encipher(passwordE));
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                worker = this.createWorker(result);
            }
            result.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException(this.getClass().getName() + "caused a problem!");
        }
        return worker;
    }

    public Worker getWorker(String id) throws DaoException{
        Worker worker = null;
        try {
            preparedStatement = conn.prepareStatement("select * from worker where id is ?");
            preparedStatement.setString(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                worker = this.createWorker(result);
            }
            result.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException(this.getClass().getName() + "class caused a problem!");
        }
        return worker;
    }

    public List<Worker> getWorkers() throws DaoException {
        List<Worker> workers = new LinkedList<>();
        try {
            String query = "select * from worker";
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(query);
            while (result.next()) {
                workers.add(createWorker(result));
            }
            result.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException(this.getClass().getName() + "caused a problem!");
        }
        return workers;
    }

    private Worker createWorker(ResultSet result) throws SQLException {
        int id = result.getInt(1);
        String name = result.getString(2);
        String login = result.getString(3);
        String password = result.getString(4);;
        return new Worker(id, name, login, password);
    }

    public void save(Worker worker) throws DaoException{
        String name = worker.getName();
        String login = worker.getLogin();
        String password = new CesarEnigma().encipher(worker.getPassword());
        try {
            preparedStatement = conn.prepareStatement("insert into worker(name, login, password) values(?,?,?)");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,login);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException(this.getClass().getName() + "class caused a problem");
        }
    }

    public void update(Worker worker) throws DaoException {
        int id = worker.getId();
        String name = worker.getName();
        String login = worker.getLogin();
        String password = new CesarEnigma().encipher(worker.getPassword());
        try {
            preparedStatement = conn.prepareStatement("update worker set name = ?, login = ?, password = ? where id = ?");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,login);
            preparedStatement.setString(3, password);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException(this.getClass().getName() + "class caused a problem");
        }
    }

    public void remove(int id) throws DaoException {
        try {
            preparedStatement = conn.prepareStatement("DELETE FROM worker WHERE id IS ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException(this.getClass().getName() + " class caused problem");
        }
    }
}
