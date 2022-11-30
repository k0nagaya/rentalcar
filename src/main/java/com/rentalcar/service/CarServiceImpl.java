package com.rentalcar.service;

import com.rentalcar.exception.BusinessLogicException;
import com.rentalcar.exception.FarReservationException;
import com.rentalcar.exception.OverdueFromDateException;
import com.rentalcar.exception.TooLongDurationException;
import com.rentalcar.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository repository;

    @Autowired
    public CarServiceImpl(CarRepository repository) {
        this.repository = repository;
    }

    @Override
    public Car add(Car car) {
        if (car.getId() != null) {
            throw new IllegalArgumentException("New car ID must be null. Given object:  " + car);
        }
        return repository.save(car);
    }

    @Override
    public Car update(Car car) {
        Car oldCar = repository.getReferenceById(car.getId());
        oldCar.setMake(car.getMake());
        oldCar.setModel(car.getModel());
        return repository.save(oldCar);
    }

    @Override
    public void remove(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Car> listAll() {
        return repository.findAll();
    }

    @Override
    public Car findFreeCar(LocalDateTime from, LocalDateTime to, String make, String model)
            throws BusinessLogicException {
        long duration = ChronoUnit.MINUTES.between(from, to);
        if (duration > 120) {
            throw new TooLongDurationException();
        }
        if (from.minusDays(1).isAfter(LocalDateTime.now())) {
            throw new FarReservationException();
        }
        if (from.isBefore(LocalDateTime.now()) || from.isAfter(to)) {
            throw new OverdueFromDateException();
        }

        List<Car> found = repository.findFreeCars(make, model, from, from.plusMinutes(duration));
        if (found.isEmpty()) {
            return null;
        }
        return found.get(0);
    }
}
