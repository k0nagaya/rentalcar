package com.rentalcar.rest;

import com.rentalcar.RentalcarApplication;
import com.rentalcar.exception.BusinessLogicException;
import com.rentalcar.service.CarServiceImpl;
import com.rentalcar.service.ReservationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = RentalcarApplication.class)
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = {
        CarServiceImpl.class,
        ReservationServiceImpl.class,
        ReservationWebServiceImpl.class
})
public class UniversalWebServiceTest {

    @Autowired
    private CarWebService carWebService;

    @Autowired
    private ReservationWebService reservationWebService;

    @Test
    public void test() throws BusinessLogicException {
        carWebService.add(CarDTO.builder().make("Toyota").model("Prius").build());
        CarDTO car = carWebService.add(CarDTO.builder().make("Porsche").model("Carrera 911").build());

        LocalDateTime time = LocalDateTime.now().plusMinutes(5);
        reservationWebService.reserveCar(ReservationDTO
                .builder().car(car).fromTime(time).toTime(time.plusMinutes(20)).build());
        List<ReservationDTO> reservations = reservationWebService.allReservations();

        Assertions.assertEquals("C2", car.getId());
        Assertions.assertEquals(1, reservations.size());
        Assertions.assertEquals(car, reservations.get(0).getCar());
    }
}
