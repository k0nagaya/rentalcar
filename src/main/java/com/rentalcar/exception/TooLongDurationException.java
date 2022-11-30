package com.rentalcar.exception;

public class TooLongDurationException extends BusinessLogicException {

    public TooLongDurationException() {
        super("A car could be rented just for 2 hours");
    }
}
