package by.kirill.controller;

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
    CarService carService;

    @Autowired
    StatusService statusService;

    @GetMapping("/cars")
    public List<?> getCars() {
        return carService.findAll();
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<?> getCar(@PathVariable Integer id) {
        Optional<?> car = carService.findById(id);
            return new ResponseEntity<>(car.get(), HttpStatus.OK);
    }

    @PostMapping(path = "/createcar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newCar(@RequestBody CarDTO carDTO) {
        return new ResponseEntity<>(carService.create(carDTO), HttpStatus.OK);
    }
}
