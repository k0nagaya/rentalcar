package com.rentalcar.service;

import com.rentalcar.exception.BusinessLogicException;
import com.rentalcar.model.Car;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

/**
 * CRUD-service for {@link Car}.
 */
public interface CarService {

    /**
     * Adds a new car.
     *
     * @param car car to add. Must have no ID
     * @return added car
     */
    Car add(@NonNull Car car);

    /**
     * Updates given car.
     *
     * @param car entity to update
     * @return updated car
     */
    Car update(@NonNull Car car);

    /**
     * Removes the car with given ID.
     *
     * @param id ID
     */
    void remove(String id);

    /**
     * Obtains list of all cars.
     *
     * @return list of cars
     */
    List<Car> listAll();

    /**
     * Finds car free for given period.
     *
     * @param from start of the period
     * @param to end of the period
     * @param make make of a car
     * @param model model of a car
     * @return car or null if not found
     * @throws BusinessLogicException
     *      if any violations happened
     */
    Car findFreeCar(LocalDateTime from, LocalDateTime to, String make, String model) throws BusinessLogicException;
}
