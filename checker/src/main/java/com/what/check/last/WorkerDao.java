package com.what.check.last;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerDao {

    private static WorkerDao workerDao = null;
    private final Connection connection;
    private PreparedStatement preparedStatement;

    private WorkerDao() {
        this.connection = Dao.getConn();
    }

    public static WorkerDao getWorkerDao() {
        if (workerDao == null) {
            synchronized (WorkerDao.class) {
                if (workerDao == null) {
                    workerDao = new WorkerDao();
                }
            }
        }
        return workerDao;
    }

    public void save(Worker worker) {
        String query = "insert into worker(name, age) values(?,?);";
        String name = worker.getName();
        Integer age = worker.getAge();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Worker> workerList() throws SQLException {
        List<Worker> all = new ArrayList<>();
        String query = "select * from worker;";
        Statement st = connection.createStatement();
        ResultSet result = st.executeQuery(query);
        while (result.next()) {
            all.add(createWorker(result));
        }
        return all;
    }

    public Worker getWorker(String name, int age) {
        Worker worker = null;
        String query = "select * from worker where name = ? and age = ?;";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                worker = createWorker(result);
            }
            result.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return worker;
    }

    private Worker createWorker(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String name = result.getString("name");
        int age = result.getInt("age");
        return new Worker(id, name, age);
    }
}
