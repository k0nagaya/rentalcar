package com.rentalcar.rest;

import com.rentalcar.exception.BusinessLogicException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * REST-controller for {@link com.rentalcar.model.Reservation} methods.
 */
@RequestMapping("/default")
public interface ReservationWebService {

    /**
     * Creates a car reservation for given params. The reservation can be taken up to 24 hours ahead and the duration
     * can take up to 2 hours
     *
     * @param params expected from-to time, mark and model of a car
     * @return reservation or null if for given period no car found.
     * @throws BusinessLogicException
     *      in case of absent parameters or violated time requirements
     */
    @PostMapping("/reserve")
    ReservationDTO reserveCar(@RequestBody ReservationDTO params) throws BusinessLogicException;

    /**
     * Shows all reservations.
     *
     * @return list of reservations
     */
    @GetMapping
    List<ReservationDTO> allReservations();
}
