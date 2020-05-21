package by.kirill.service.impl;

import by.kirill.service.api.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;

@SpringBootTest
@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:/database/schema.sql", "classpath:/database/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class CarServicePublicMethodTest {

    @Autowired
    private CarService carService;

    @Test
    public void carService_findAll_notEmpty() {
        assertFalse(carService.findAll().isEmpty());
    }


}
