package com.rentalcar.rest;

import com.rentalcar.model.Car;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * REST-controller for {@link Car} methods
 */
@RequestMapping("/default")
public interface CarWebService {

    /**
     * Adds a new car.
     *
     * @param car car to add. Must have no ID
     * @return added car
     */
    @PostMapping("/add")
    CarDTO add(@RequestBody CarDTO car);

    /**
     * Updates given car.
     *
     * @param car entity to update
     * @return updated car
     */
    @PutMapping("/update")
    CarDTO update(@RequestBody CarDTO car);

    /**
     * Removes the car with given ID.
     *
     * @param id id of the car to delete
     */
    @DeleteMapping("/remove/{id}")
    void remove(@PathVariable String id);

    /**
     * Obtains list of all cars.
     *
     * @return list of cars
     */
    @GetMapping("/")
    List<CarDTO> getAll();
}
