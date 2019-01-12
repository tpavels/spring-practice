package tpavels.spring.civ.resource;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpavels.spring.civ.model.Building;
import tpavels.spring.civ.service.BuildingService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "building")
public class BuildingResource {

    private BuildingService buildingService;

    @GetMapping
    public ResponseEntity<List<Building>> fetchAllBuildings() {
        List<Building> Buildings = buildingService.fetchAllBuildings();
        return new ResponseEntity<>(Buildings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Building> getBuildings(@PathVariable Long id) {
        Building Building = buildingService.getBuilding(id);
        return new ResponseEntity<>(Building, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createBuildings(@RequestBody Building b) {
        Long BuildingId = buildingService.createBuilding(b);
        return new ResponseEntity<>(BuildingId, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuildings(@PathVariable Long id) {
        buildingService.deleteBuilding(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Building> updateBuildings(@RequestBody Building b) {
        Building updated = buildingService.updateBuilding(b);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
