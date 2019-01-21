package tpavels.spring.civ.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import tpavels.spring.civ.model.Building;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class BuildingServiceIntegrationTest {

    @Autowired
    private BuildingService buildingService;

    @Test(expected = EntityNotFoundException.class)
    public void text_getBuilding_notExist() {
        buildingService.getBuilding(99999L);
    }

    @Test
    public void test_createBuildin_empty() {
        Long building = buildingService.createBuilding(new Building());
        Building created = buildingService.getBuilding(building);
        assertNull(created.getName());
        assertNull(created.getMaintenanceCost());
        assertEquals(building, created.getBuildingId());
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