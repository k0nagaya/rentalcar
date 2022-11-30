package com.rentalcar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Car {

    @Id
    @GeneratedValue(generator = "car-generator")
    @GenericGenerator(name = "car-generator", strategy = "com.rentalcar.model.CarIdGenerator")
    private String id;

    @NotBlank(message = "Make field should not be empty")
    private String make;

    @NotBlank(message = "Model field should not be empty")
    private String model;

    @OneToMany(orphanRemoval = true, mappedBy = "car", fetch = FetchType.LAZY)
    private List<Reservation> reservations;

    // avoiding SO caused by bidirectional relation
    @Override
    public String toString() {
        String result = "id: " + id
                + ", make: " + make
                + ", model: " + model;
        if (reservations != null && !reservations.isEmpty()) {
            result += ", reservations: " + reservations.stream().map(Reservation::getId);
        }

        return result;
    }
}
