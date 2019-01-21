package tpavels.spring.civ.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import tpavels.spring.civ.model.Unit;
import tpavels.spring.civ.model.UnitCategory;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class UnitServiceIntegrationTest {

    @Autowired
    private UnitService unitService;

    @Test(expected = EntityNotFoundException.class)
    public void text_getUnit_notExist() {
        unitService.getUnit(99999L);
    }

    @Test
    public void test_createUnit_empty() {
        Long unit = unitService.createUnit(new Unit());
        Unit created = unitService.getUnit(unit);
        assertNull(created.getName());
        assertNull(created.getMaintenanceCost());
        assertEquals(unit, created.getUnitId());
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
        UnitCategory unitCategory = new UnitCategory();
        unitCategory.setName("testCat");

        Unit unit = new Unit();
        unit.setName("testUnit");
        unit.setMaintenanceCost(20);
        unit.setCategory(unitCategory);

        return unit;
    }


}