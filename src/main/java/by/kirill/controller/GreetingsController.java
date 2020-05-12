package by.kirill.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @GetMapping("/helloworld")
    public HelloResponse hello(@RequestParam(value = "name", defaultValue = "World") String name) {

        HelloResponse res = new HelloResponse();
        res.message = String.format("Hello %s!", name);
        return res;

    }

    class HelloResponse {
        public String message = "";
    }

}

