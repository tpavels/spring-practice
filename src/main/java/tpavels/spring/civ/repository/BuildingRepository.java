package tpavels.spring.civ.repository;

import org.springframework.data.repository.CrudRepository;
import tpavels.spring.civ.model.Building;
import tpavels.spring.civ.model.Civilization;


public interface BuildingRepository extends CrudRepository<Building, Long> {
}
