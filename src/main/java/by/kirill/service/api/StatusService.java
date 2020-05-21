package by.kirill.service.api;

import by.kirill.controller.handler.exceptions.StatusNotFoundException;
import by.kirill.controller.handler.exceptions.StatusNotUniqueException;
import by.kirill.controller.handler.exceptions.StatusNotUpdatebleException;
import by.kirill.entity.Status;

import java.util.List;
import java.util.Optional;

public interface StatusService {

    List<Status> findAll();

    void deleteById(Integer theId) throws StatusNotUpdatebleException;

    boolean existsById(Integer id) ;

    Optional<Status> findById(Integer id);

    List<Status> findByName(String name);

    Status create(Status status)  throws StatusNotUniqueException;

    Status update(Status status, Integer id) throws StatusNotFoundException
            , StatusNotUpdatebleException, StatusNotUniqueException;

}
