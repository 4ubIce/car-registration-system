package by.kirill.service.impl;

import by.kirill.dao.CarRepository;
import by.kirill.entity.Car;
import by.kirill.entity.dto.CarDTO;
import by.kirill.service.api.StatusService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CarServicePrivateMethodTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private StatusService statusService;

    @Autowired
    private CarServiceImpl carServiceImpl;

    @Before
    public void setup() {
         this.carServiceImpl = new CarServiceImpl(carRepository, statusService);
    }

    @Test
    public void isParamsCorrect_TrueWithCorrectParams() {
        CarDTO carDTO = new CarDTO("Mersedes", 1);
        //ReflectionTestUtils.invokeMethod get access to private method of class
        assertTrue(ReflectionTestUtils.invokeMethod(carServiceImpl,"isParamsCorrect", carDTO));
    }

    @Test
    public void isParamsCorrect_FalseWithIdNotNull() {
        CarDTO carDTO = new CarDTO(9,"Mersedes", 1);
        assertFalse(ReflectionTestUtils.invokeMethod(carServiceImpl,"isParamsCorrect", carDTO));
    }

    @Test
    public void isParamsCorrect_FalseWithModelNullAndEmpty() {
        CarDTO carDTOModelNull = new CarDTO(null, 1);
        CarDTO carDTOModelEmpty = new CarDTO("", 1);
        assertFalse(ReflectionTestUtils.invokeMethod(carServiceImpl,"isParamsCorrect", carDTOModelNull));
        assertFalse(ReflectionTestUtils.invokeMethod(carServiceImpl,"isParamsCorrect", carDTOModelEmpty));
    }

    @Test
    public void isParamsCorrect_FalseWithStatusNotAvailable() {
        CarDTO carDTO = new CarDTO("Mersedes", 2);
        assertFalse(ReflectionTestUtils.invokeMethod(carServiceImpl,"isParamsCorrect", carDTO));
    }

    @Test
    public void isParamsCorrect_FalseWithStatusNotExist() {
        CarDTO carDTO = new CarDTO("Mersedes", 4);
        assertFalse(ReflectionTestUtils.invokeMethod(carServiceImpl,"isParamsCorrect", carDTO));
    }

    @Test
    public void isModelCorrect_TrueWithCorrectParams() {
        CarDTO carDTO = new CarDTO("Mersedes", 1);
        assertTrue(ReflectionTestUtils.invokeMethod(carServiceImpl,"isModelCorrect", carDTO));
    }

    @Test
    public void isModelCorrect_FalseWithModelNullAndEmpty() {
        CarDTO carDTOModelNull = new CarDTO(null, 1);
        CarDTO carDTOModelEmpty = new CarDTO("", 1);
        assertFalse(ReflectionTestUtils.invokeMethod(carServiceImpl,"isModelCorrect", carDTOModelNull));
        assertFalse(ReflectionTestUtils.invokeMethod(carServiceImpl,"isModelCorrect", carDTOModelEmpty));
    }

    @Test
    public void isStatusCorrect_TrueWithCorrectParams() {
        CarDTO carDTO = new CarDTO(1,"Mersedes", 2);
        Car carToUpdate = carServiceImpl.findById(carDTO.getId()).get();
        assertTrue(ReflectionTestUtils.invokeMethod(carServiceImpl,"isStatusCorrect", carDTO, carToUpdate));
    }

    @Test
    public void isStatusCorrect_FalseWithSimilarStatusId() {
        CarDTO carDTO = new CarDTO(1,"Mersedes", 1);
        Car carToUpdate = carServiceImpl.findById(carDTO.getId()).get();
        assertFalse(ReflectionTestUtils.invokeMethod(carServiceImpl,"isStatusCorrect", carDTO, carToUpdate));
    }

}
