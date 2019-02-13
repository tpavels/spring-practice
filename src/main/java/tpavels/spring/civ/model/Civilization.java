package tpavels.spring.civ.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Civilization extends BaseModel {

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Leader cannot be null")
    private String leader;

    @Min(value = 0, message = "Gold should not be less than 0")
    private Long gold;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "civilization"
    )
        private final List<City> cities = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "civilization",
            orphanRemoval = true
    )
    private final List<CivUnit> units = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }


    public void addCity(City city) {
        this.cities.add(city);
    }

    public void addUnit(CivUnit civUnit) {
        this.units.add(civUnit);
    }

    public Long getGold() {
        return gold;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }

    public List<City> getCities() {
        return cities;
    }

    public List<CivUnit> getUnits() {
        return units;
    }
}
