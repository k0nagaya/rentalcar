package com.rentalcar.rest;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Completely unnecessary DTO, just to show the approach.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CarDTO {
    String id;

    @NotBlank(message = "Make field should not be empty")
    private String make;

    @NotBlank(message = "Model field should not be empty")
    private String model;
}
