package by.kirill.service.impl;

import by.kirill.controller.handler.exceptions.CarNotFoundException;
import by.kirill.entity.dto.CarDTO;
import by.kirill.service.api.CarService;
import by.kirill.service.api.StatusService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:/database/schema.sql", "classpath:/database/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class CarServicePublicMethodTest {

    @Autowired
    private CarService carService;

    @Autowired
    private StatusService statusService;

    @Test
    public void carService_findAll_notEmpty() {
        assertFalse(carService.findAll().isEmpty());
    }

    @Test
    public void carService_findById__notNull(){
        assertNotNull(carService.findById(1).orElse(null));
    }

    @Test
    public void carService_findByStatusId_notEmpty() {
        assertFalse(carService.findAllByStatusId(1).isEmpty());
    }

    @Test
    public void carService_create_sizeIncreasedWhithRightParams() {
        int size = carService.findAll().size();
        carService.create(new CarDTO("Test car", 1));
        assertEquals(size + 1, carService.findAll().size());
    }

    @Test
    public void carService_create_sizeNotIncreasedWhithWrongParamsAndThrowException() {
        int size = carService.findAll().size();
        assertThrows(IllegalArgumentException.class,() -> carService.create(
                new CarDTO("Test car", 2)));
        assertThrows(IllegalArgumentException.class,() -> carService.create(
                new CarDTO("", 1)));
        assertEquals(size, carService.findAll().size());
    }

    @Test
    public void carService_update_throwExceptionWithWrongCarId() {
        assertThrows(CarNotFoundException.class, () -> carService.update(
                new CarDTO("Volvo", 1), 8));
    }

    @Test
    public void carService_delete_sizeDecreasedWhithRightParams() {
        int size = carService.findAll().size();
        carService.deleteById(2);
        assertEquals(size - 1, carService.findAll().size());
    }

}
