package by.kirill.controller;

import by.kirill.controller.handler.exceptions.*;
import by.kirill.entity.Car;
import by.kirill.entity.Status;
import by.kirill.entity.dto.CarDTO;
import by.kirill.service.api.CarService;
import by.kirill.service.api.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestApiController {

    @Autowired
    private CarService carService;

    @Autowired
    private StatusService statusService;

    @GetMapping("/car/get/all")
    public List<?> getCars() {
        return carService.findAll();
    }

    @GetMapping("/car/get/byid/{id}")
    public ResponseEntity<?> getCar(@PathVariable Integer id) throws CarNotFoundException {
        Optional<?> car = carService.findById(id);
        if (car.isPresent()) {
            return new ResponseEntity<>(car.get(), HttpStatus.OK);
        } else{
            throw new CarNotFoundException();
        }
    }

    @GetMapping("/car/get/bystatusid/{id}")
    public ResponseEntity<?> getCarByStatusId(@PathVariable Integer id) throws CarNotFoundException, StatusNotFoundException {
        Optional<?> status = statusService.findById(id);
        if (status.isPresent()) {
            List<Car> cars = carService.findAllByStatusId(id);
            if (!cars.isEmpty()) {
                return new ResponseEntity<>(cars, HttpStatus.OK);
            } else{
                throw new CarNotFoundException();
            }
        } else{
            throw new StatusNotFoundException();
        }
    }

    @PostMapping(path = "/car/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newCar(@RequestBody CarDTO carDTO) {
        return new ResponseEntity<>(carService.create(carDTO), HttpStatus.OK);
    }

    @PutMapping(path = "/car/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCar(@RequestBody CarDTO carDTO, @PathVariable Integer id)
            throws CarNotFoundException, StatusIncorrectException {
        return new ResponseEntity<>(carService.update(carDTO, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/car/delete/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Integer id) {
            carService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/status/get/all")
    public List<?> getStatus() {
        return statusService.findAll();
    }

    @GetMapping("/status/get/byid/{id}")
    public ResponseEntity<?> getStatus(@PathVariable Integer id) throws StatusNotFoundException {
        Optional<?> status = statusService.findById(id);
        if (status.isPresent()) {
            return new ResponseEntity<>(status.get(), HttpStatus.OK);
        } else{
            throw new StatusNotFoundException();
        }
    }

    @PostMapping(path = "/status/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newStatus(@RequestBody Status status) throws StatusNotUniqueException {
        return new ResponseEntity<>(statusService.create(status), HttpStatus.OK);
    }

    @PutMapping(path = "/status/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStatus(@RequestBody Status status, @PathVariable Integer id)
            throws StatusNotFoundException, StatusNotUpdatebleException, StatusNotUniqueException {
        return new ResponseEntity<>(statusService.update(status, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/status/delete/{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable Integer id) throws StatusNotUpdatebleException {
        try {
            statusService.deleteById(id);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new StatusNotUpdatebleException("The status cannot be deleted." +
                    " Cars with this status were found");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
