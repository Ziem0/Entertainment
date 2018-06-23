package com.what.check.last;

import java.sql.SQLException;

public class App {

    public static void main(String[] args) throws SQLException {
        Worker w1 = new Worker("zApli", 100);
        Worker w2 = new Worker("zApli2", 200);

        Dao.migrate();
        WorkerDao workerDao = WorkerDao.getWorkerDao();
//        workerDao.save(w1);
//        workerDao.save(w2);
        for (Worker w : workerDao.workerList()) {
            System.out.println(w);
        }
        System.out.println(workerDao.getWorker("ziemo", 32));
        Dao.closeConn();
    }
}
