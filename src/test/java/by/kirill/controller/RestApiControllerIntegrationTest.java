package by.kirill.controller;


import by.kirill.entity.Car;
import by.kirill.entity.dto.CarDTO;
import by.kirill.service.api.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:/database/schema.sql", "classpath:/database/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class RestApiControllerIntegrationTest {

    //ObjectMapper uses for converting object to json string and back to object
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CarService carService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testCreateCar() throws Exception {
        CarDTO carDTO = new CarDTO("Volkswagen", 1);
        int startSize = carService.findAll().size();
        MvcResult mvcResult = mvc.perform(post("/api/car/create/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(carDTO)) //convert object carDTO to json string
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
        Car car = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Car.class); // convert json string to object car
        CarDTO resultDto = new CarDTO(car);
        carDTO.setId(resultDto.getId());
        assertEquals(carService.findAll().size(), startSize + 1);
        assertEquals(resultDto, carDTO);
    }
}
