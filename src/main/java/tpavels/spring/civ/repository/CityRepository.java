package tpavels.spring.civ.repository;

import org.springframework.data.repository.CrudRepository;
import tpavels.spring.civ.model.City;
import tpavels.spring.civ.model.Civilization;

public interface CityRepository extends CrudRepository<City, Long> {

    City getByName(String name);
}
