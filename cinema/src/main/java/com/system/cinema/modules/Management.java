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

    public Worker getWorker(String name) throws DaoException {
        return workerDao.getWorker(name);
    }

    public List<Worker> getWorkers() throws DaoException {
        return workerDao.getWorkers();
    }

    // createWorker
    // editWorker
    // viewWorker




}
