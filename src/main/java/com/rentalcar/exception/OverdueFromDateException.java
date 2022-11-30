package com.rentalcar.exception;

public class OverdueFromDateException extends BusinessLogicException {
    public OverdueFromDateException() {
        super("Start of a reservation should be after current time");
    }
}
