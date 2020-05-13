package by.kirill.service.api;

import by.kirill.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> findAll();

    List<Car> findAllByStatusId(Integer id);

    void deleteById(Integer id);

    boolean existsById(Integer id) ;

    Optional<Car> findById(Integer id);
}
