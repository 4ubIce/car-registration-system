package by.kirill.controller;

import by.kirill.controller.handler.exceptions.CarNotFoundException;
import by.kirill.controller.handler.exceptions.StatusIncorrectException;
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

    @GetMapping("/cars")
    public List<?> getCars() {
        return carService.findAll();
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<?> getCar(@PathVariable Integer id) throws CarNotFoundException {
        Optional<?> car = carService.findById(id);
        if (car.isPresent()) {
            return new ResponseEntity<>(car.get(), HttpStatus.OK);
        } else{
            throw new CarNotFoundException();
        }
    }

    @PostMapping(path = "/createcar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newCar(@RequestBody CarDTO carDTO) {
        return new ResponseEntity<>(carService.create(carDTO), HttpStatus.OK);
    }

    @PutMapping(path = "/updatecar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCar(@RequestBody CarDTO carDTO, @PathVariable Integer id)
            throws CarNotFoundException, StatusIncorrectException {
        return new ResponseEntity<>(carService.update(carDTO, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/deletecar/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Integer id) {
            carService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }
}
