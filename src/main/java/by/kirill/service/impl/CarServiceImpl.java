package by.kirill.service.impl;

import by.kirill.dao.CarRepository;
import by.kirill.entity.Car;
import by.kirill.service.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> findAllByStatusId(Integer id) {
        return carRepository.findAllByStatusId(id);
    }

    @Override
    public void deleteById(Integer id) {
        carRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return carRepository.existsById(id);
    }

    @Override
    public Optional<Car> findById(Integer id) {
        return carRepository.findById(id);
    }
}
