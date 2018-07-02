package com.system.cinema.modules;

public class PaymentProcess extends AbstractProcess {

    @Override
    void action(Reservation reservation) {
        System.out.println(reservation.pay() ? "checked" : "impossible to perform checking!\n");
    }
}
