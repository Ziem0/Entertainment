package com.system.cinema.modules;

public class CheckoutProcess extends AbstractProcess {

    @Override
    void action(Reservation reservation) {
        System.out.println(reservation.checkout() ? "done!\n" : "Impossible to perform payment!\n");
    }
}
