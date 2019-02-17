package tpavels.spring.civ.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TestTransaction;
import tpavels.spring.civ.model.*;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CivilizationServiceIntegrationTest {

    @Autowired
    private CivilizationService civilizationService;

    @Autowired
    private CityService cityService;

    @Autowired
    private UnitService unitService;

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
    @Transactional
    public void test_createCiv_data() {
        Civilization civ = createTestCiv("create");
        Long newCivId = civilizationService.createCivilization(civ);
        Civilization created = civilizationService.getCivilization(newCivId);
        assertEquals(civ.getName(),created.getName());
        assertEquals(civ.getLeader(), created.getLeader());
        assertEquals(civ.getCities(),created.getCities());
        assertEquals(newCivId, created.getId());
    }


    @Test(expected = EntityNotFoundException.class)
    public void test_deleteCiv_empty() {
        civilizationService.deleteCivilization(99999L);
    }

    @Test
    public void test_deleteCiv_successfully() {
        Civilization civ = createTestCiv("delete");
        Long newCivId = civilizationService.createCivilization(civ);
        civilizationService.deleteCivilization(newCivId);
        cityService.getCity("testCity");
    }

    @Test
    public void test_deleteUnit() {
        Civilization civ = createTestCiv("delete");
        civilizationService.createCivilization(civ);
        civ.getUnits().clear();
        unitService.getUnit("testUnit");
    }

    @Test
    @Transactional
    public void test_addCity() {
        Civilization testCiv = createEmptyTestCiv("test");
        assertTrue(testCiv.getCities().isEmpty());

        City testCity = createCity("test");
        testCiv.addCity(testCity);
        testCity.setCivilization(testCiv);

        Long civilizationId = civilizationService.createCivilization(testCiv);
        TestTransaction.flagForCommit();
        TestTransaction.end();

        TestTransaction.start();
        Civilization loadedCiv = civilizationService.getCivilization(civilizationId);

        assertFalse(loadedCiv.getCities().isEmpty());
        assertEquals(1, loadedCiv.getCities().size());
        assertEquals("testCity", loadedCiv.getCities().get(0).getName());
        TestTransaction.end();
    }

    private Civilization createEmptyTestCiv(String prefix) {
        Civilization civilization = new Civilization();
        civilization.setName(prefix+"Civ");
        civilization.setLeader(prefix+"Leader");
        civilization.setGold(123L);
        return civilization;
    }


    private Civilization createTestCiv(String prefix) {
        Civilization civilization = new Civilization();
        civilization.setName(prefix+"Civ");
        civilization.setLeader(prefix+"Leader");
        civilization.setGold(123L);
        civilization.addCity(createCity(prefix));
        civilization.addUnit(createUnit(prefix));
        return civilization;
    }

    private City createCity(String prefix) {
        City city = new City();
        city.setName(prefix+"City");
        city.setLocation(new Location(77L,77L));
        city.setCapital(false);
        return city;
    }

    private CivUnit createUnit(String prefix) {
        Unit unit = new Unit();
        unit.setName(prefix+"Unit");
        unit.setCategory(new UnitCategory(prefix+"Cat"));
        unit.setMaintenanceCost(11);

        CivUnit civUnit = new CivUnit();
        civUnit.setHealth(99);
        civUnit.setRank(2);
        civUnit.setUnit(unit);
        civUnit.setLocation(new Location(11L, 55L));
        return civUnit;
    }
}