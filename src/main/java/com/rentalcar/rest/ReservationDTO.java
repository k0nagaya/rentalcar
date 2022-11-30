package com.rentalcar.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Another unnecessary DTO.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationDTO {
    private Long id;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;
    private CarDTO car;
}
