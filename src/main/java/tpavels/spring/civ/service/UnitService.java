package tpavels.spring.civ.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tpavels.spring.civ.model.Unit;
import tpavels.spring.civ.repository.UnitRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UnitService {

    private UnitRepository unitRepository;

    public Long createUnit(Unit unit) {
        if (unit == null) {
            unit = new Unit();
        }
        unit = unitRepository.save(unit);
        return unit.getId();
    }
    
    public void deleteUnit(Long id) {
        boolean exist = unitRepository.findById(id).isPresent();
        if (exist) {
            unitRepository.deleteById(id);
        }
    }

    public Unit updateUnit(Unit unit){
        boolean exist = unitRepository.findById(unit.getId()).isPresent();
        Unit updated = null;
        if (exist) {
            updated = unitRepository.save(unit);
        }
        return updated;
    }

    public Unit getUnit(Long id) {
        return unitRepository.findById(id).orElse(null);
    }

    public List<Unit> fetchAllUnits(){
        List<Unit> allUnits = new ArrayList<>();
        unitRepository.findAll().forEach(allUnits::add);
        return allUnits;
    }
}
