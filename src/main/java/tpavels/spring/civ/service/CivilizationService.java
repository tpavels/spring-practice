package tpavels.spring.civ.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tpavels.spring.civ.model.Civilization;
import tpavels.spring.civ.repository.CivilizationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CivilizationService {

    private CivilizationRepository civilizationRepository;

    public Long createCivilization(Civilization civ) {
        if (civ == null) {
            civ = new Civilization();
        }
        civ = civilizationRepository.save(civ);
        return civ.getId();
    }

    public void deleteCivilization(Long id) {
        boolean exist = civilizationRepository.findById(id).isPresent();
        if (exist) {
            civilizationRepository.deleteById(id);
        }
    }

    public Civilization updateCivilization(Civilization civ){
        boolean exist = civilizationRepository.findById(civ.getId()).isPresent();
        Civilization updated = null;
        if (exist) {
            updated = civilizationRepository.save(civ);
        }
        return updated;
    }

    public Civilization getCivilization(Long id) {
        return civilizationRepository.findById(id).orElse(null);
    }

    public List<Civilization> fetchAllCivilizations(){
        List<Civilization> allCivs = new ArrayList<>();
        civilizationRepository.findAll().forEach(allCivs::add);
        return allCivs;
    }
}
