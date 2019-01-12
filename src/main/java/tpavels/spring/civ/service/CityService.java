package tpavels.spring.civ.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tpavels.spring.civ.model.City;
import tpavels.spring.civ.repository.CityRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CityService {

    private CityRepository cityRepository;

    public Long createCity(City c) {
        if (c == null) {
            c = new City();
        }
        c = cityRepository.save(c);
        return c.getId();
    }

    public void deleteCity(Long id) {
        boolean exist = cityRepository.findById(id).isPresent();
        if (exist) {
            cityRepository.deleteById(id);
        }
    }

    public City updateCity(City c){
        boolean exist = cityRepository.findById(c.getId()).isPresent();
        City updated = null;
        if (exist) {
            updated = cityRepository.save(c);
        }
        return updated;
    }

    public City getCity(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    public List<City> fetchAllCities(){
        List<City> allCities = new ArrayList<>();
        cityRepository.findAll().forEach(allCities::add);
        return allCities;
    }
}
