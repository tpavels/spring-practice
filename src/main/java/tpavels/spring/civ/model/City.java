package tpavels.spring.civ.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class City extends BaseModel {

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Is Capital cannot be null")
    private Boolean isCapital;

    @OneToOne(mappedBy = "city", cascade = CascadeType.PERSIST)
    @NotNull(message = "Location cannot be null")
    private Location location;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "CityBuilding",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "building_id")
    )
    private final Set<Building> buildings = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "civilization_id")
    private Civilization civilization;

    public String getName() {
        return name;
    }

    public Boolean getCapital() {
        return isCapital;
    }

    public Location getLocation() {
        return location;
    }

    public Set<Building> getBuildings() {
        return buildings;
    }

    public Civilization getCivilization() {
        return civilization;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapital(Boolean capital) {
        isCapital = capital;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setCivilization(Civilization civilization) {
        this.civilization = civilization;
    }

    public void addBuilding(Building building) {
        this.buildings.add(building);
    }

    public void addAllBuilding(Set<Building> building) {
        if (building != null) {
            this.buildings.addAll(building);
        }
    }
}
