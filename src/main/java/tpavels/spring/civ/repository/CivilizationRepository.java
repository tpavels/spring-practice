package tpavels.spring.civ.repository;

import org.springframework.data.repository.CrudRepository;
import tpavels.spring.civ.model.Civilization;

public interface CivilizationRepository extends CrudRepository<Civilization, Long> {
}
