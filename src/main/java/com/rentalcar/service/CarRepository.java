package com.rentalcar.service;

import com.rentalcar.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    @Query("select c from Car c " +
            "where c.make = :make and c.model = :model and " +
            "not exists (" +
            "   select r from Reservation r" +
            "   where r.car.id = c.id" +
            "   and (r.fromTime between :from and :to" +
            "   or r.toTime between :from and :to" +
            "   or :from between r.fromTime and r.toTime))")
    List<Car> findFreeCars(@Param("make") String make,
                           @Param("model") String model,
                           @Param("from") LocalDateTime from,
                           @Param("to") LocalDateTime to);
}
