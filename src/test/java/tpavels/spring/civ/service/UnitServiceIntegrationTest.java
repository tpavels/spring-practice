package tpavels.spring.civ.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tpavels.spring.civ.model.Unit;
import tpavels.spring.civ.model.UnitCategory;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitServiceIntegrationTest {

    @Autowired
    private UnitService unitService;

    @Test(expected = EntityNotFoundException.class)
    public void test_getUnit_notExist() {
        unitService.getUnit(99999L);
    }

    @Test
    public void test_getUnit_real() {
        Unit unit = unitService.getUnit("Warrior");
        assertEquals("Warrior", unit.getName());
        assertEquals("Melee", unit.getCategory().getName());
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_createUnit_empty() {
        Long unit = unitService.createUnit(new Unit());
        unitService.getUnit(unit);
    }

    @Test
    public void test_createUnit_data() {
        Unit testUnit = createTestUnit();
        Long unit = unitService.createUnit(testUnit);
        Unit created = unitService.getUnit(unit);
        assertEquals(testUnit.getName(),created.getName());
        assertEquals(testUnit.getMaintenanceCost(), created.getMaintenanceCost());
        assertEquals(testUnit.getCategory(),created.getCategory());
        assertEquals(unit, created.getUnitId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void test_deleteUnit_empty() {
        unitService.deleteUnit(99999L);
    }

    @Test
    public void test_deleteUnit_successfully() {
        Unit testUnit = createTestUnit();
        Long unit = unitService.createUnit(testUnit);
        unitService.deleteUnit(unit);
    }

    private Unit createTestUnit() {
        UnitCategory unitCategory = new UnitCategory("testCat");

        Unit unit = new Unit();
        unit.setName("testUnit");
        unit.setMaintenanceCost(20);
        unit.setCategory(unitCategory);

        return unit;
    }


}