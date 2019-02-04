package tpavels.spring.civ.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tpavels.spring.civ.model.Building;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BuildingServiceIntegrationTest {

    @Autowired
    private BuildingService buildingService;

    @Test(expected = EntityNotFoundException.class)
    public void text_getBuilding_notExist() {
        buildingService.getBuilding(99999L);
    }

    @Test
    public void text_getBuilding() {
        Building building = buildingService.getBuilding(1L);
        assertEquals("Barracks", building.getName());
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_createBuilding_constraint() {
        Long building = buildingService.createBuilding(new Building());
        buildingService.getBuilding(building);
    }

    @Test
    public void test_createBuilding_name() {
        Building testBuilding = createTestBuilding();
        Long building = buildingService.createBuilding(testBuilding);
        Building created = buildingService.getBuilding(building);
        assertEquals(testBuilding.getName(),created.getName());
        assertEquals(testBuilding.getMaintenanceCost(), created.getMaintenanceCost());
        assertEquals(building, created.getBuildingId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void test_deleteBuilding_empty() {
        buildingService.deleteBuilding(99999L);
    }

    @Test
    public void test_deleteBuilding_successfully() {
        Building testBuilding = createTestBuilding();
        Long building = buildingService.createBuilding(testBuilding);
        buildingService.deleteBuilding(building);
    }

    private Building createTestBuilding() {
        Building newBuilding = new Building();
        newBuilding.setName("test");
        newBuilding.setMaintenanceCost(10);
        return newBuilding;
    }

}