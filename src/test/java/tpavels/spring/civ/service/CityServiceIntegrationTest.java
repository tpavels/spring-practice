package tpavels.spring.civ.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tpavels.spring.civ.model.City;
import tpavels.spring.civ.model.Location;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityServiceIntegrationTest {

    @Autowired
    private CityService cityService;

    @Test(expected = EntityNotFoundException.class)
    public void test_getCity_empty() {
        cityService.getCity(99999L);
    }

    @Test
    public void test_getCity_real() {
        City testCity = cityService.getCity("London");
        assertEquals("London", testCity.getName());
    }


    @Test(expected = ConstraintViolationException.class)
    public void test_createCity_empty() {
        Long city = cityService.createCity(new City());
        cityService.getCity(city);
    }

    @Test
    @Transactional
    public void test_createCity_data() {
        City testCity = createTestCity();
        Long city = cityService.createCity(testCity);
        City created = cityService.getCity(city);
        assertEquals(testCity.getName(),created.getName());
        assertEquals(testCity.getLocation(), created.getLocation());
        assertEquals(testCity.getBuildings(),created.getBuildings());
        assertEquals(city, created.getCityId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void test_deleteCity_empty() {
        cityService.deleteCity(99999L);
    }

    @Test
    public void test_deleteCity_successfully() {
        City testCity = createTestCity();
        Long city = cityService.createCity(testCity);
        cityService.deleteCity(city);
    }

    private City createTestCity() {
        Location location = new Location(10L, 5L);

        City city = new City();
        city.setName("test");
        city.setIsCapital(true);
        city.setLocation(location);
        city.setBuildings(null);
        return city;
    }


}