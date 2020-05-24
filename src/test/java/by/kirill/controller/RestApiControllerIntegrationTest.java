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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

    private MvcResult getMvcResultWithoutContent(String str) throws Exception {
        return mvc.perform(get(str)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

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

    @Test
    public void testGetAllCars() throws Exception {
        List<Car> cars = carService.findAll();
        MvcResult mvcResult = getMvcResultWithoutContent("/api/car/get/all");
        assertEquals(objectMapper.writeValueAsString(cars), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetCarById() throws Exception {
        int id = 1;
        Car car = carService.findById(id).get();
        MvcResult mvcResult = getMvcResultWithoutContent("/api/car/get/byid/"+ id);
        assertEquals(objectMapper.writeValueAsString(car), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetCarByStatusId() throws Exception {
        int id = 1;
        List<Car> cars = carService.findAllByStatusId(id);
        MvcResult mvcResult = getMvcResultWithoutContent("/api/car/get/bystatusid/"+ id);
        assertEquals(objectMapper.writeValueAsString(cars), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testUpdateCar() throws Exception {
        int id = 2;
        CarDTO carDTO = new CarDTO("Volkswagen", 1);
        int startSize = carService.findAll().size();
        MvcResult mvcResult = mvc.perform(put("/api/car/update/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(carDTO))
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
        Car car = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Car.class);
        CarDTO resultDto = new CarDTO(car);
        carDTO.setId(resultDto.getId());
        assertEquals(resultDto, carDTO);
    }

    @Test
    public void testDeleteCar() throws Exception {
        int startSize = carService.findAll().size();
        MvcResult mvcResult = mvc.perform(delete("/api/car/delete/1")
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(carService.findAll().size(), startSize - 1);
    }

}
