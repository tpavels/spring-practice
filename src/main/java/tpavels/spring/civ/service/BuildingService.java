package tpavels.spring.civ.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tpavels.spring.civ.model.Building;
import tpavels.spring.civ.repository.BuildingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BuildingService {

    private BuildingRepository buildingRepository;

    public Long createBuilding(Building b) {
        if (b == null) {
            b = new Building();
        }
        b = buildingRepository.save(b);
        return b.getId();
    }

    public void deleteBuilding(Long id) {
        boolean exist = buildingRepository.findById(id).isPresent();
        if (exist) {
            buildingRepository.deleteById(id);
        }
    }

    public Building updateBuilding(Building b){
        boolean exist = buildingRepository.findById(b.getId()).isPresent();
        Building updated = null;
        if (exist) {
            updated = buildingRepository.save(b);
        }
        return updated;
    }

    public Building getBuilding(Long id) {
        return buildingRepository.findById(id).orElse(null);
    }

    public List<Building> fetchAllBuildings(){
        List<Building> allBuildings = new ArrayList<>();
        buildingRepository.findAll().forEach(allBuildings::add);
        return allBuildings;
    }
}
