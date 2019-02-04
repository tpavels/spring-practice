package tpavels.spring.civ.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tpavels.spring.civ.model.City;
import tpavels.spring.civ.model.Civilization;
import tpavels.spring.civ.model.Location;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CivilizationServiceIntegrationTest {

    @Autowired
    private CivilizationService civilizationService;

    @Test(expected = EntityNotFoundException.class)
    public void test_getCiv_notExist() {
        civilizationService.getCivilization(99999L);
    }

    @Test
    public void test_getCiv_exist() {
        Civilization civ = civilizationService.getCivilization("American");
        assertEquals("American", civ.getName());
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_createCiv_empty() {
        Long civ = civilizationService.createCivilization(new Civilization());
        civilizationService.getCivilization(civ);
    }

    @Test
    public void test_createCiv_data() {
        Civilization civ = createTestCiv();
        Long newCivId = civilizationService.createCivilization(civ);
        Civilization created = civilizationService.getCivilization(newCivId);
        assertEquals(civ.getName(),created.getName());
        assertEquals(civ.getLeader(), created.getLeader());
        assertEquals(civ.getCities(),created.getCities());
        assertEquals(newCivId, created.getCivilizationId());
    }

    private Civilization createTestCiv() {
        return Civilization.builder()
                .name("testCiv")
                .leader("testLeader")
                .gold(123L)
                .cities(Arrays.asList(City.builder()
                        .name("testCity")
                        .location(new Location(77L,77L))
                        .build())
                ).build();
    }

    @Test(expected = EntityNotFoundException.class)
    public void test_deleteCiv_empty() {
        civilizationService.deleteCivilization(99999L);
    }

    @Test
    public void test_deleteCiv_successfully() {
        Civilization civ = createTestCiv();
        Long newCivId = civilizationService.createCivilization(civ);
        civilizationService.deleteCivilization(newCivId);
    }

}