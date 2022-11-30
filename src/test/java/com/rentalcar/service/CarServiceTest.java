package com.rentalcar.service;

import com.rentalcar.model.Car;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.when;

/**
 * Test sample. Not fully covered.
 */
public class CarServiceTest {

    private CarService service;

    @Mock
    private CarRepository repository;

    @BeforeEach
    public void setup() {
        repository = Mockito.mock(CarRepository.class);

        service = new CarServiceImpl(repository);
    }

    @ParameterizedTest
    @MethodSource("addParams")
    public void testAdd(String make, String model) {
        Car expected = Car.builder().id("C1").make(make).model(model).build();
        Car param = Car.builder().make(make).model(model).build();
        when(repository.save(param)).thenReturn(expected);

        Car actual = service.add(param);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> addParams() {
        return Stream.of(
                Arguments.arguments("honda", "civic"),
                Arguments.arguments("bmw", "x5"),
                Arguments.arguments("a", "b")
        );
    }

    @Test
    public void testAddFail() {
        assertThrowsExactly(IllegalArgumentException.class,
                () -> service.add(Car.builder().id("C10").make("X").model("Y").build()));
    }

    @ParameterizedTest
    @MethodSource("updateParams")
    public void testUpdate(String id, String oldMake, String oldModel) {
        String newMake = oldMake + "-V2";
        String newModel = oldModel + "-V2";

        Car oldCar = Car.builder().id(id).make(oldMake).model(oldModel).build();
        when(repository.getReferenceById(id)).thenReturn(oldCar);

        Car newCar = Car.builder().id(id).make(newMake).model(newModel).build();
        when(repository.save(newCar)).thenReturn(newCar);

        Car actual = service.update(newCar);
        assertEquals(newCar, actual);
    }

    private static Stream<Arguments> updateParams() {
        return Stream.of(
                Arguments.arguments("C1", "honda", "civic"),
                Arguments.arguments("C2", "skoda", "octavia"),
                Arguments.arguments("C3", "t", "x")
        );
    }

    @Test
    public void testUpdateFailCarNotFound() {
        when(repository.getReferenceById("D1")).thenThrow(EntityNotFoundException.class);
        assertThrowsExactly(EntityNotFoundException.class, () -> service.update(Car.builder().id("D1").build()));
    }

    @Test
    public void testRemove() {
        service.remove("C1");
        Mockito.verify(repository).deleteById("C1");
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void testRemoveEmptyParam(String id) {
         service.remove(id);
    }

    @Test
    public void testListAll() {
        Car car1 = Car.builder().id("C1").make("mitsubishi").model("lancer").build();
        Car car2 = Car.builder().id("C2").make("subaru").model("impreza").build();
        when(repository.findAll()).thenReturn(List.of(car1, car2));

        List<Car> actual = service.listAll();
        assertEquals(List.of(car1, car2), actual);
    }
}
