package tpavels.spring.civ.app;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import tpavels.spring.civ.model.*;
import tpavels.spring.civ.repository.BuildingRepository;
import tpavels.spring.civ.repository.CityRepository;
import tpavels.spring.civ.repository.CivilizationRepository;
import tpavels.spring.civ.repository.UnitRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Transactional
public class SampleDataLoader implements ApplicationRunner {

    private BuildingRepository buildingRepository;
    private UnitRepository unitRepository;
    private CityRepository cityRepository;
    private CivilizationRepository civilizationRepository;

    @Override
    public void run(ApplicationArguments args) {
        loadBuildings();
        loadUnits();
        loadCities();
        loadCivs();
    }

    private void loadCivs() {
        saveCiv("American", "Teddy Roosevelt", 123L,
                createCities(Arrays.asList("Washington", "New York", "Philadelphia", "Boston")),
                Arrays.asList(createCivUnit("Warrior", new Location(4L, 4L), 100, 1 ),
                        createCivUnit("Warrior", new Location(4L, 4L), 100, 2 ),
                        createCivUnit("Scout", new Location(12L, 7L), 100, 2 ),
                        createCivUnit("Archer", new Location(12L, 7L), 100, 3 )));

        saveCiv("Egyptian", "Cleopatra", 222L,
                createCities(Arrays.asList("Alexandria", "Thebes", "Memphis")),
                Arrays.asList(createCivUnit("Galley", new Location(27L, 2L), 100, 1 ),
                        createCivUnit("Archer", new Location(27L, 2L), 100, 1 ),
                        createCivUnit("Archer", new Location(35L, 4L), 100, 1 )));

        saveCiv("English", "Victoria", 455L,
                createCities(Arrays.asList("London", "Liverpool", "Manchester")),
                Arrays.asList(createCivUnit("Trader", new Location(10L, 16L), null, null ),
                        createCivUnit("Trader", new Location(18L, 20L), null, null ),
                        createCivUnit("Slinger", new Location(18L, 20L), 100, 2 ),
                        createCivUnit("Spearman", new Location(18L, 20L), 100, 3 ),
                        createCivUnit("Spearman", new Location(18L, 20L), 100, 1 )));

        saveCiv("Indian", "Gandhi", 600L,
                createCities(Arrays.asList("Delhi", "Mumbai")),
                Arrays.asList(createCivUnit("Warrior", new Location(36L, 20L), 100, 1 ),
                        createCivUnit("Battering Ram", new Location(36L, 20L), 100, 2 ),
                        createCivUnit("Builder", new Location(32L, 15L), null, null ),
                        createCivUnit("Settler", new Location(32L, 15L), null, null )));
    }

    private void loadCities() {
        saveCity("Washington",true, new Location(4L,4L),
                createBuildings(Arrays.asList("Barracks", "Granary", "Monument", "Walls")));
        saveCity("New York",false, new Location(4L,11L),
                createBuildings(Arrays.asList("Monument", "Granary")));
        saveCity("Philadelphia",false, new Location(12L,7L),
                createBuildings(Arrays.asList("Walls", "Monument", "Granary")));
        saveCity("Boston",false, new Location(16L,1L),
                createBuildings(Arrays.asList("Stonehenge", "Granary")));

        saveCity("Alexandria",true, new Location(27L,2L),
                createBuildings(Arrays.asList("Monument", "Walls")));
        saveCity("Thebes",false, new Location(29L,8L),
                createBuildings(Arrays.asList("Library", "Monument", "Great Library")));
        saveCity("Memphis",false, new Location(35L,4L),
                null);

        saveCity("London",true, new Location( 10L,16L),
                createBuildings(Arrays.asList("Monument", "Barracks","Granary","Library","Pyramids")));
        saveCity("Liverpool",false, new Location(7L,25L),
                createBuildings(Arrays.asList("Monument")));
        saveCity("Manchester",false, new Location( 18L,20L),
                null);

        saveCity("Delhi",true, new Location(32L,15L),
                createBuildings(Arrays.asList("Walls", "Monument", "Shrine")));
        saveCity("Mumbai",false, new Location(36L,20L),
                createBuildings(Arrays.asList("Granary", "Shrine")));
    }

    private void loadUnits() {
        saveUnit("Warrior", 0, "Melee");
        saveUnit("Archer", 1, "Ranged");
        saveUnit("Builder", 0, "Support");
        saveUnit("Battering Ram", 1, "Siege");
        saveUnit("Scout", 0, "Recon");
        saveUnit("Galley", 0, "Naval");
        saveUnit("Trader", 0, "Support");
        saveUnit("Spearman", 1, "Melee");
        saveUnit("Slinger", 0, "Ranged");
        saveUnit("Settler", 0, "Support");
    }

    private void loadBuildings() {
        saveBuilding("Barracks",1, false);
        saveBuilding("Granary",1, false);
        saveBuilding("Library",1, false);
        saveBuilding("Monument",1, false);
        saveBuilding("Shrine",1, false);
        saveBuilding("Walls",0, false);
        saveBuilding("Pyramids",0, true);
        saveBuilding("Stonehenge",0, true);
        saveBuilding("Great Library",0, true);
    }

    private void saveCiv(String name, String leader, Long gold, List<City> cities, List<CivUnit> units) {
        Civilization civilization = new Civilization();
        civilization.setGold(gold);
        civilization.setLeader(leader);
        civilization.setName(name);
        civilization.getCities().addAll(cities);
        civilization.getUnits().addAll(units);
        cities.forEach(c -> c.setCivilization(civilization));
        units.forEach(u -> u.setCivilization(civilization));
        civilizationRepository.save(civilization);
    }

    private void saveBuilding(String name, Integer maintCost, Boolean isWorldWonder) {
        Building building = new Building();
        building.setName(name);
        building.setMaintenanceCost(maintCost);
        building.setWorldWonder(isWorldWonder);
        buildingRepository.save(building);
    }

    private void saveUnit(String name, Integer maintCost, String cat) {
        Unit unit = new Unit();
        unit.setMaintenanceCost(maintCost);
        unit.setName(name);
        unit.setCategory(new UnitCategory(cat));
        unitRepository.save(unit);
    }

    private void saveCity(String name, Boolean isCapital, Location loc, Set<Building> buildings) {
        City city = new City();
        city.setName(name);
        city.setLocation(loc);
        city.setCapital(isCapital);
        city.addAllBuilding(buildings);
        cityRepository.save(city);
    }

    private CivUnit createCivUnit(String unitName, Location loc, Integer health, Integer rank) {
        CivUnit civUnit = new CivUnit();
        civUnit.setLocation(loc);
        civUnit.setHealth(health);
        civUnit.setRank(rank);
        civUnit.setUnit(unitRepository.getByName(unitName));
        return civUnit;
    }

    private Set<Building> createBuildings(List<String> names) {
        return names.stream()
                .map(n -> buildingRepository.getByName(n))
                .collect(Collectors.toSet());
    }

    private List<City> createCities(List<String> names) {
        return names.stream()
                .map(n -> cityRepository.getByName(n))
                .collect(Collectors.toList());
    }
}

