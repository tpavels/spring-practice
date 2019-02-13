package tpavels.spring.civ.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Building extends BaseModel{

    @NotNull(message = "Name cannot be null")
    private String name;

    @Min(value = 0, message = "Health should not be less than 0")
    private Integer maintenanceCost;

    private Boolean isWorldWonder;

    @ManyToMany(mappedBy = "buildings")
    private final Set<City> cities = new HashSet<>();

    public String getName() {
        return name;
    }

    public Integer getMaintenanceCost() {
        return maintenanceCost;
    }

    public Boolean getWorldWonder() {
        return isWorldWonder;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaintenanceCost(Integer maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    public void setWorldWonder(Boolean worldWonder) {
        isWorldWonder = worldWonder;
    }

    public void addCity(City city) {
        this.cities.add(city);
    }
}
