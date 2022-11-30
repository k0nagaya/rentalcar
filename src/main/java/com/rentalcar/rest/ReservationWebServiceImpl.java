package com.rentalcar.rest;

import com.rentalcar.exception.BusinessLogicException;
import com.rentalcar.model.Car;
import com.rentalcar.model.Reservation;
import com.rentalcar.service.CarService;
import com.rentalcar.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservation")
public class ReservationWebServiceImpl implements ReservationWebService {

    private final ReservationService service;

    private final CarService carService;

    private final CarMapper carMapper;

    @Autowired
    public ReservationWebServiceImpl(ReservationService service, CarService carService) {
        this.service = service;
        this.carService = carService;
        this.carMapper = new CarMapper();
    }

    @Override
    public ReservationDTO reserveCar(ReservationDTO params) throws BusinessLogicException {
        CarDTO carParam = params.getCar();
        LocalDateTime from = params.getFromTime();
        LocalDateTime to = params.getToTime();
        if (carParam == null || from == null || to == null) {
            throw new BusinessLogicException("Empty expected param. Car: " + carParam
                    + ", fromTime: " + from + ", toTime: " + to);
        }

        Car car = carService.findFreeCar(from, to, carParam.getMake(), carParam.getModel());
        if (car == null) {
            return null;
        }
        return mapEntity(service.reserveCar(from, to, car));
    }

    @Override
    public List<ReservationDTO> allReservations() {
        return service.allReservations()
                .stream()
                .map(this::mapEntity)
                .collect(Collectors.toList());
    }

    private ReservationDTO mapEntity(Reservation reservation) {
        return ReservationDTO.builder()
                .id(reservation.getId())
                .fromTime(reservation.getFromTime())
                .toTime(reservation.getToTime())
                .car(carMapper.mapEntity(reservation.getCar()))
                .build();
    }
}
