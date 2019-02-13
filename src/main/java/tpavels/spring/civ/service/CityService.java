package tpavels.spring.civ.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import tpavels.spring.civ.model.City;
import tpavels.spring.civ.repository.CityRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CityService {

    private CityRepository cityRepository;

    public Long createCity(City c) {
        c = cityRepository.save(c);
        return c.getId();
    }

    public void deleteCity(Long id) {
        try {
            cityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("City {%s} does not exist",id));
        }
    }

    public City updateCity(City existing, City updated){
        updated.setId(existing.getId());
        updated = cityRepository.save(updated);
        return updated;
    }

    public City getCity(Long id) {
       return cityRepository
               .findById(id)
               .orElseThrow(EntityNotFoundException::new);
    }

    public City getCity(String name) {
        City byName = cityRepository.getByName(name);
        return byName;
    }

    public List<City> fetchAllCities(){
        List<City> allCities = new ArrayList<>();
        cityRepository.findAll().forEach(allCities::add);
        return allCities;
    }
}
