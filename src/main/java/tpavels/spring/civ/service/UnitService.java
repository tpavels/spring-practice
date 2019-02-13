package tpavels.spring.civ.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import tpavels.spring.civ.model.City;
import tpavels.spring.civ.model.Unit;
import tpavels.spring.civ.repository.UnitRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UnitService {

    private UnitRepository unitRepository;

    public Long createUnit(Unit unit) {
        unit.setId(null);
        unit = unitRepository.save(unit);
        return unit.getId();
    }
    
    public void deleteUnit(Long id) {
        try {
            unitRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Unit {%s} does not exist",id));
        }
    }

    public Unit updateUnit(Unit existing, Unit updated){
        updated.setId(existing.getId());
        updated = unitRepository.save(updated);
        return updated;
    }

    public Unit getUnit(Long id) {
        return unitRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Unit getUnit(String name) {
        Unit byName = unitRepository.getByName(name);
        return byName;
    }

    public List<Unit> fetchAllUnits(){
        List<Unit> allUnits = new ArrayList<>();
        unitRepository.findAll().forEach(allUnits::add);
        return allUnits;
    }
}
