package com.rentalcar.exception;

public class FarReservationException extends BusinessLogicException {

    public FarReservationException() {
        super("A car couldn't be rented over 24 hours ahead");
    }
}
