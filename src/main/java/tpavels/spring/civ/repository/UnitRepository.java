package tpavels.spring.civ.repository;

import org.springframework.data.repository.CrudRepository;
import tpavels.spring.civ.model.Civilization;
import tpavels.spring.civ.model.Unit;

import java.util.List;

public interface UnitRepository extends CrudRepository<Unit, Long> {
    Unit findByName(String name);
}
