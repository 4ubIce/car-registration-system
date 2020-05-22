package by.kirill.service.api;

import by.kirill.controller.handler.exceptions.CarNotFoundException;
import by.kirill.controller.handler.exceptions.StatusIncorrectException;
import by.kirill.controller.handler.exceptions.StatusNotFoundException;
import by.kirill.entity.Car;
import by.kirill.entity.dto.CarDTO;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> findAll();

    List<Car> findAllByStatusId(Integer id);

    void deleteById(Integer id);

    boolean existsById(Integer id) ;

    Optional<Car> findById(Integer id);

    Car create(CarDTO carDTO);

    Car update(CarDTO carDTO, Integer id) throws CarNotFoundException, StatusIncorrectException;
}
