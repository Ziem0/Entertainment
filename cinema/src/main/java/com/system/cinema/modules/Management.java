package com.system.cinema.modules;

import com.system.cinema.dao.ManagementDao;
import com.system.cinema.dao.WorkerDao;
import com.system.cinema.exceptions.DaoException;

import java.util.List;

public class Management {

    private WorkerDao worker;
    private ManagementDao management;
    private List<Worker> workers;
    private WorkerDao workerDao = WorkerDao.getDao();

    public Worker getWorker(String id) throws DaoException {
        return workerDao.getWorker(id);
    }

    public List<Worker> getWorkers() throws DaoException {
        return workerDao.getWorkers();
    }

    public void createWorker(Worker worker) throws DaoException {
        workerDao.save(worker);
    }

    public void editWorker(Worker worker) throws DaoException {
        workerDao.update(worker);
    }

    public void workersAsList() throws DaoException {
        workerDao.getWorkers().forEach(System.out::println);
    }

    public void removeWorker(int id) throws DaoException {
        workerDao.remove(id);
    }

    //update worker --> controller
    //delete worker --> controller --> list Worker and remove by ID
    //view worker  --> controller
}
