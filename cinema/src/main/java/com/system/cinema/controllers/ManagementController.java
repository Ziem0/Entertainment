package com.system.cinema.controllers;

import com.system.cinema.dao.WorkerDao;
import com.system.cinema.enums.Menu4All;
import com.system.cinema.exceptions.DaoException;
import com.system.cinema.modules.Management;
import com.system.cinema.modules.Worker;

import java.util.Scanner;

public class ManagementController {

    // edit worker
    // view worker

    private WorkerDao dao = WorkerDao.getDao();

    public static void main(String[] args) throws DaoException {
        Management man = new Management();
        ManagementController controller = new ManagementController();
        boolean isRunning = true;
        Scanner sc = new Scanner(System.in);

        while (isRunning) {
            Menu4All.MANAGEMENT.printMenu();
            int userChoice = sc.nextInt();

            switch (userChoice) {
                case 1:
                    man.createWorker(controller.addWorker());
                    break;
                case 2:
                    Scanner sc2 = new Scanner(System.in);
                    System.out.println("Enter worker ID: ");
                    int choice = sc2.nextInt();
                    controller.listAllWorkers();
                    man.removeWorker(choice);
                    break;
                case 3:
                    controller.listAllWorkers();
                    break;
                case 4:
                    break;
                case 5:
                    isRunning = false;
            }
        }
    }

    private Worker addWorker() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = sc.nextLine();
        System.out.println("Enter login: ");
        String login = sc.nextLine();
        System.out.println("Enter password: ");
        String password = sc.nextLine();
        return new Worker(name, login, password);
    }

    private void listAllWorkers() throws DaoException {
        for (Worker w : dao.getWorkers()) {
            System.out.printf("id:%d  name:%s", w.getId(),w.getName());
        }
    }

}
