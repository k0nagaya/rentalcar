package com.rentalcar.service;

import com.rentalcar.model.Car;
import com.rentalcar.model.Reservation;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {

    /**
     * Reserves given car for a given time.
     *
     * @param from from time
     * @param to to time
     * @param car car
     * @return created reservation
     */
    Reservation reserveCar(LocalDateTime from, LocalDateTime to, Car car);

    /**
     * Returns all reservations.
     *
     * @return list of reservations
     */
    @GetMapping
    List<Reservation> allReservations();
}
