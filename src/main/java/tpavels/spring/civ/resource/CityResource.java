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
        List<City> cities = cityService.fetchAllCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCities(@PathVariable Long id) {
        City city = cityService.getCity(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createCities(@RequestBody City c) {
        Long cityId = cityService.createCity(c);
        return new ResponseEntity<>(cityId, HttpStatus.OK);
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
