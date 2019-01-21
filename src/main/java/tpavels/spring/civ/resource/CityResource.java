package tpavels.spring.civ.resource;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpavels.spring.civ.model.City;
import tpavels.spring.civ.service.CityService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "city")
public class CityResource {

    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<City>> fetchAllCities() {
        List<City> Cities = cityService.fetchAllCities();
        return new ResponseEntity<>(Cities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCities(@PathVariable Long id) {
        City City = cityService.getCity(id);
        return new ResponseEntity<>(City, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createCities(@RequestBody City c) {
        Long CityId = cityService.createCity(c);
        return new ResponseEntity<>(CityId, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCities(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<City> updateCities(@RequestBody City updated) {
        City existing = cityService.getCity(updated.getCityId());
        City c = cityService.updateCity(existing, updated);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
}
