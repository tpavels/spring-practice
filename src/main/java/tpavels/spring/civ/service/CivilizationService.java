package tpavels.spring.civ.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import tpavels.spring.civ.model.Civilization;
import tpavels.spring.civ.repository.CivilizationRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CivilizationService {

    private CivilizationRepository civilizationRepository;

    public Long createCivilization(Civilization civ) {
        civ = civilizationRepository.save(civ);
        return civ.getId();
    }

    public void deleteCivilization(Long id) {
        try {
            civilizationRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Civilization {%s} does not exist",id));
        }
    }

    public Civilization updateCivilization(Civilization existing, Civilization updated){
        updated.setId(existing.getId());
        updated = civilizationRepository.save(updated);
        return updated;
    }

    public Civilization getCivilization(Long id) {
        Civilization civ = civilizationRepository.findById(id).orElse(null);
        if (civ != null) {
            civ.setCivilizationId(civ.getId());
        } else {
            throw new EntityNotFoundException(String.format("Civilization {%s} does not exist",id));
        }
        return civ;
    }

    public List<Civilization> fetchAllCivilizations(){
        List<Civilization> allCivs = new ArrayList<>();
        civilizationRepository.findAll().forEach(allCivs::add);
        return allCivs;
    }
}
