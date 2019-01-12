package tpavels.spring.civ.repository;

import org.springframework.data.repository.CrudRepository;
import tpavels.spring.civ.model.Civilization;

public interface CivRepository extends CrudRepository<Civilization, Long> {
    Civilization findByName(String name);
    Civilization findByLeader(String name);
}
