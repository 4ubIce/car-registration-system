package by.kirill.service.impl;

import by.kirill.controller.handler.exceptions.CarNotFoundException;
import by.kirill.controller.handler.exceptions.StatusIncorrectException;
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

        if (!isParamsCorrect(carDTO)) {
            throw new IllegalArgumentException();
        }

        Car car = new Car(carDTO.getId(), carDTO.getModel(),
                            statusService.findById(carDTO.getStatusId()).get());

        return carRepository.save(car);
    }

    @Override
    public Car update(CarDTO carDTO, Integer id) throws CarNotFoundException, StatusIncorrectException {

        carDTO.setId(id);
        Optional<Car> carToUpdateOpt = carRepository.findById(carDTO.getId());
        if (!carToUpdateOpt.isPresent()) {
            throw  new CarNotFoundException();
        }

        Car carToUpdate = carToUpdateOpt.get();

        if (isModelCorrect(carDTO)) {
            carToUpdate.setModel(carDTO.getModel());
        }

        if (isStatusCorrect(carDTO, carToUpdate)) {
            Optional<Status> newStatus = statusService.findById(carDTO.getStatusId());
            newStatus.ifPresent(carToUpdate::setStatus);
        }

        return carRepository.save(carToUpdate);
    }

    private boolean isModelCorrect(CarDTO carDTO) {

        return carDTO.getModel() != null;

    }

    private boolean isStatusCorrect(CarDTO carDTO, Car carToUpdate) throws StatusIncorrectException {

        Integer newStatusId = carDTO.getStatusId();
        Status defaultStatus = statusService.findByName("available");

        if (newStatusId == null) {
            return false;
        }

        Status status = carToUpdate.getStatus();
        Integer oldStatusId = status.getId();

        if (newStatusId == oldStatusId) {
            return false;
        }

        if (newStatusId != defaultStatus.getId()
                && oldStatusId != defaultStatus.getId()) {
            throw new StatusIncorrectException();
        }

        return true;

    }

    private boolean isParamsCorrect(CarDTO carDTO) {
        try {
            Status defaultStatus = statusService.findByName("available");
            if (carDTO.getId() != null
                    || carDTO.getModel().isEmpty()
                    || carDTO.getStatusId() != defaultStatus.getId()) {
                return false;
            }
            return statusService.existsById(carDTO.getStatusId());
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
