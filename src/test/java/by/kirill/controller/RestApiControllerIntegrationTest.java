package by.kirill.controller;


import by.kirill.entity.Car;
import by.kirill.entity.dto.CarDTO;
import by.kirill.service.api.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:/database/schema.sql", "classpath:/database/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class RestApiControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CarService carService;

    private MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testCreateCar() throws Exception {
        CarDTO carDTO = new CarDTO("Volkswagen", 1);
        int startSize = carService.findAll().size();
        MvcResult mvcResult = mvc.perform(post("/api/createcar/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(carDTO))
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
        Car car = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Car.class);
        CarDTO resultDto = new CarDTO(car);
        carDTO.setId(resultDto.getId());
        assertEquals(carService.findAll().size(), startSize + 1);
        assertEquals(resultDto, carDTO);
    }
}
