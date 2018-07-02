package com.system.cinema.modules;

public abstract class AbstractProcess {

    public void process(Reservation reservation) {
        stepBefore();
        action(reservation);
        stepAfter();
    }

    public void stepBefore() {
        System.out.println("System is loading..\n");
    }

    public void stepAfter() {
        System.out.println("Saving data..\n");
    }

    abstract void action(Reservation reservation);
}
