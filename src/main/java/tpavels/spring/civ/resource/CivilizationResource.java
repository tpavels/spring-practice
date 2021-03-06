package tpavels.spring.civ.resource;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpavels.spring.civ.model.Civilization;
import tpavels.spring.civ.service.CivilizationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "civilization")
public class CivilizationResource {

    private CivilizationService civilizationService;

    @GetMapping
    public ResponseEntity<List<Civilization>> fetchAllCivilizations() {
        List<Civilization> civilizations = civilizationService.fetchAllCivilizations();
        return new ResponseEntity<>(civilizations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Civilization> getCivilizations(@PathVariable Long id) {
        Civilization civilization = civilizationService.getCivilization(id);
        return new ResponseEntity<>(civilization, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createCivilizations(@RequestBody Civilization civ) {
        Long civilizationId = civilizationService.createCivilization(civ);
        return new ResponseEntity<>(civilizationId, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCivilizations(@PathVariable Long id) {
        civilizationService.deleteCivilization(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Civilization> updateCities(@RequestBody Civilization updated) {
        Civilization existing = civilizationService.getCivilization(updated.getId());
        Civilization c = civilizationService.updateCivilization(existing, updated);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
}
