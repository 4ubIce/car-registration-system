package by.kirill.service.impl;

import by.kirill.dao.CarRepository;
import by.kirill.entity.Car;
import by.kirill.entity.Status;
import by.kirill.entity.dto.CarDTO;
import by.kirill.service.api.CarService;
import by.kirill.service.api.StatusService;
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

    @Autowired
    private StatusService statusService;

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

    @Override
    public Car create(CarDTO carDTO) {

        Status defStatus;

        defStatus = statusService.findByName("available");

        if (!isParamsCorrect(carDTO)) {
            throw new IllegalArgumentException();
        }

        //default status is "available"
        if (carDTO.getStatusId() != defStatus.getId()) {
            return null;
        }
        Car car = new Car(carDTO.getId(), carDTO.getModel(), defStatus);

        return carRepository.save(car);
    }

    @Override
    public Car update(CarDTO taskDTO, Integer id) {
        return null;
    }

    private boolean isParamsCorrect(CarDTO carDTO) {
        try {
            if (carDTO.getId() != null
                    || carDTO.getModel().isEmpty()) {
                return false;
            }
            return statusService.existsById(carDTO.getStatusId());
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
