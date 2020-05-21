package by.kirill.service.impl;

import by.kirill.dao.CarRepository;
import by.kirill.entity.dto.CarDTO;
import by.kirill.service.api.CarService;
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
    public void testM() {
        CarDTO carDTO = new CarDTO("Mersedes", 1);
        //ReflectionTestUtils.invokeMethod we get access to private method of class
        assertTrue(ReflectionTestUtils.invokeMethod(carServiceImpl,"isParamsCorrect", carDTO));
    }
}
