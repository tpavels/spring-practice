package tpavels.spring.civ.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import tpavels.spring.civ.model.*;
import tpavels.spring.civ.repository.BuildingRepository;
import tpavels.spring.civ.repository.CityRepository;
import tpavels.spring.civ.repository.CivilizationRepository;
import tpavels.spring.civ.repository.UnitRepository;
import tpavels.spring.civ.resource.UnitResource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SampleDataLoader implements ApplicationRunner {

    private BuildingRepository buildingRepository;
    private UnitRepository unitRepository;
    private CityRepository cityRepository;
    private CivilizationRepository civilizationRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadBuildings();
        loadUnits();
        loadCities();
        loadCivs();
    }

    private void loadCivs() {
        saveCiv("American", "Teddy Roosevelt", 123L,
                createCities(Arrays.asList("Washington", "New York", "Philadelphia", "Boston")),
                Arrays.asList(createCivUnit("Warrior", new Location(4L, 4L), 100L, 1L ),
                        createCivUnit("Warrior", new Location(4L, 4L), 100L, 2L ),
                        createCivUnit("Scout", new Location(12L, 7L), 100L, 2L ),
                        createCivUnit("Archer", new Location(12L, 7L), 100L, 3L )));

        saveCiv("Egyptian", "Cleopatra", 222L,
                createCities(Arrays.asList("Alexandria", "Thebes", "Memphis")),
                Arrays.asList(createCivUnit("Galley", new Location(27L, 2L), 100L, 1L ),
                        createCivUnit("Archer", new Location(27L, 2L), 100L, 1L ),
                        createCivUnit("Archer", new Location(35L, 4L), 100L, 1L )));

        saveCiv("English", "Victoria", 455L,
                createCities(Arrays.asList("London", "Liverpool", "Manchester")),
                Arrays.asList(createCivUnit("Trader", new Location(10L, 16L), null, null ),
                        createCivUnit("Trader", new Location(18L, 20L), null, null ),
                        createCivUnit("Slinger", new Location(18L, 20L), 100L, 2L ),
                        createCivUnit("Spearman", new Location(18L, 20L), 100L, 3L ),
                        createCivUnit("Spearman", new Location(18L, 20L), 100L, 1L )));

        saveCiv("Indian", "Gandhi", 600L,
                createCities(Arrays.asList("Delhi", "Mumbai")),
                Arrays.asList(createCivUnit("Warrior", new Location(36L, 20L), 100L, 1L ),
                        createCivUnit("Battering Ram", new Location(36L, 20L), 100L, 2L ),
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

    private void saveCiv(String name, String leader, Long gold, List<City> cities, List<CivUnits> units) {
        civilizationRepository.save(Civilization.builder()
                .name(name)
                .leader(leader)
                .cities(cities)
                .gold(gold)
                .units(units)
                .build());
    }

    private void saveBuilding(String name, Integer maintCost, Boolean isWorldWonder) {
        buildingRepository.save(Building.builder()
                .name(name)
                .maintenanceCost(maintCost)
                .isWorldWonder(isWorldWonder)
                .build());
    }

    private void saveUnit(String name, Integer maintCost, String cat) {
        unitRepository.save(Unit.builder()
                .name(name)
                .maintenanceCost(maintCost)
                .category(new UnitCategory(cat))
                .build());
    }

    private void saveCity(String name, Boolean isCapital, Location loc, List<Building> buildings) {
        cityRepository.save(City.builder()
                .name(name)
                .buildings(buildings)
                .location(loc)
                .isCapital(isCapital)
                .build());
    }

    private CivUnits createCivUnit(String unitName, Location loc, Long health, Long rank) {
        return CivUnits.builder()
                .unit(unitRepository.getByName(unitName))
                .location(loc)
                .rank(rank)
                .health(health)
                .build();
    }

    private List<Building> createBuildings(List<String> names) {
        return names.stream()
                .map(n -> buildingRepository.getByName(n))
                .collect(Collectors.toList());
    }

    private List<City> createCities(List<String> names) {
        return names.stream()
                .map(n -> cityRepository.getByName(n))
                .collect(Collectors.toList());
    }
}

