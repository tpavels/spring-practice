package tpavels.spring.civ.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import tpavels.spring.civ.model.Building;
import tpavels.spring.civ.repository.BuildingRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BuildingService {

    private BuildingRepository buildingRepository;

    public Long createBuilding(Building b) {
        b = buildingRepository.save(b);
        return b.getId();
    }

    public void deleteBuilding(Long id) {
         try {
             buildingRepository.deleteById(id);
         } catch (EmptyResultDataAccessException e) {
             throw new EntityNotFoundException(String.format("Building {%s} does not exist",id));
         }
    }

    public Building updateBuilding(Building existing, Building updated){
        updated.setId(existing.getId());
        updated = buildingRepository.save(updated);
        return updated;
    }

    public Building getBuilding(Long id) {
        Building building = buildingRepository.findById(id).orElse(null);
        if (building != null) {
            building.setBuildingId(building.getId());
        } else {
            throw new EntityNotFoundException(String.format("Building {%s} does not exist",id));
        }
        return building;
    }

    public List<Building> fetchAllBuildings(){
        List<Building> allBuildings = new ArrayList<>();
        buildingRepository.findAll().forEach(b -> processBuildings(b, allBuildings));
        return allBuildings;
    }

    private void processBuildings(Building b, List<Building> allBuildings) {
        b.setBuildingId(b.getId());
        allBuildings.add(b);
    }
}
