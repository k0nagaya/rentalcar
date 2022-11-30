package com.rentalcar.rest;

import com.rentalcar.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/car")
public class CarWebServiceImpl implements CarWebService {
    private final CarService service;

    private final CarMapper mapper;

    @Autowired
    public CarWebServiceImpl(CarService service) {
        this.service = service;
        this.mapper = new CarMapper();
    }

    @Override
    public CarDTO add(@Valid CarDTO car) {
        return mapper.mapEntity(service.add(mapper.mapDto(car)));
    }

    @Override
    public CarDTO update(@Valid CarDTO car) {
        return mapper.mapEntity(service.update(mapper.mapDto(car)));
    }

    @Override
    public void remove(String id) {
        service.remove(id);
    }

    @Override
    public List<CarDTO> getAll() {
        return service.listAll()
                .stream()
                .map(mapper::mapEntity)
                .collect(Collectors.toList());
    }
}
