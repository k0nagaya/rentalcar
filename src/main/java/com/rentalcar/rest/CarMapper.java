package com.rentalcar.rest;

import com.rentalcar.model.Car;

/**
 * Web-service mapper.
 * Could've used MapStruct, actually.
 */
public class CarMapper {
    public CarDTO mapEntity(Car car) {
        return CarDTO.builder()
                .id(car.getId())
                .make(car.getMake())
                .model(car.getModel())
                .build();
    }

    public Car mapDto(CarDTO dto) {
        return Car.builder()
                .id(dto.getId())
                .make(dto.getMake())
                .model(dto.getModel())
                .build();
    }
}
