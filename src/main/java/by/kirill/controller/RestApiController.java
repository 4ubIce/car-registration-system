package by.kirill.controller;

import by.kirill.service.api.CarService;
import by.kirill.service.api.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestApiController {

    @Autowired
    CarService carService;

    @Autowired
    StatusService statusService;

    @GetMapping("/cars")
    public List<?> getCars() {
        return carService.findAll();
    }
}
