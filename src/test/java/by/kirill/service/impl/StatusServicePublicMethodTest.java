package by.kirill.service.impl;

import by.kirill.controller.handler.exceptions.StatusNotFoundException;
import by.kirill.controller.handler.exceptions.StatusNotUniqueException;
import by.kirill.controller.handler.exceptions.StatusNotUpdatebleException;
import by.kirill.entity.Status;
import by.kirill.service.api.StatusService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:/database/schema.sql", "classpath:/database/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class StatusServicePublicMethodTest {

    @Autowired
    private StatusService statusService;

    @Test
    public void statusService_findAll_notEmpty() {
        assertFalse(statusService.findAll().isEmpty());
    }

    @Test
    public void statusService_findById__notNull(){
        assertNotNull(statusService.findById(1).orElse(null));
    }

    @Test
    public void statusService_create_sizeIncreasedWhithRightParams() throws StatusNotUniqueException {
        int size = statusService.findAll().size();
        Status status = new Status("repair");
        statusService.create(status);
        assertEquals(size + 1, statusService.findAll().size());
    }

    @Test
    public void statusService_create_sizeNotIncreasedWhithWrongParamsAndThrowException() {
        int size = statusService.findAll().size();
        Status status = new Status("sold");
        assertThrows(StatusNotUniqueException.class,() -> statusService.create(status));
        assertEquals(size, statusService.findAll().size());
    }

    @Test
    public void statusService_update_throwExceptionWithWrongParams() {
        assertThrows(StatusNotFoundException.class, () -> statusService.update(
                new Status("test status"), 8));
        assertThrows(StatusNotUpdatebleException.class, () -> statusService.update(
                new Status("test status"), 1));
        assertThrows(StatusNotUniqueException.class, () -> statusService.update(
                new Status("sold"), 3));
    }

    @Test
    public void statusService_delete_sizeDecreasedWhithRightParams() throws StatusNotUpdatebleException {
        try {
            statusService.create(new Status("test status"));
        } catch (StatusNotUniqueException e) {
            e.printStackTrace();
        }
        int size = statusService.findAll().size();
        statusService.deleteById(statusService.findByName("test status").get(0).getId());
        assertEquals(size - 1, statusService.findAll().size());
    }

    @Test
    public void statusService_delete_throwExceptionWithWrongParams() {
        assertThrows(StatusNotUpdatebleException.class, () -> statusService.deleteById(
                statusService.findByName("available").get(0).getId()));
    }

}
