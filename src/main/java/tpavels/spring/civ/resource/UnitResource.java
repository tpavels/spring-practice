package tpavels.spring.civ.resource;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpavels.spring.civ.model.Unit;
import tpavels.spring.civ.service.UnitService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "unit")
public class UnitResource {

    private UnitService unitService;

    @GetMapping
    public ResponseEntity<List<Unit>> fetchAllUnits() {
        List<Unit> Units = unitService.fetchAllUnits();
        return new ResponseEntity<>(Units, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unit> getUnits(@PathVariable Long id) {
        Unit Unit = unitService.getUnit(id);
        return new ResponseEntity<>(Unit, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createUnits(@RequestBody Unit unit) {
        Long UnitId = unitService.createUnit(unit);
        return new ResponseEntity<>(UnitId, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnits(@PathVariable Long id) {
        unitService.deleteUnit(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Unit> updateUnits(@RequestBody Unit unit) {
        Unit updated = unitService.updateUnit(unit);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
