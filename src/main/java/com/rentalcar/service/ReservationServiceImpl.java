package com.rentalcar.service;

import com.rentalcar.model.Car;
import com.rentalcar.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository repository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Reservation reserveCar(LocalDateTime from, LocalDateTime to, Car car) {
        Reservation reservation = Reservation.builder()
                .fromTime(from)
                .toTime(to)
                .car(car)
                .build();
        return repository.save(reservation);
    }

    @Override
    public List<Reservation> allReservations() {
        return repository.findAll();
    }
}
