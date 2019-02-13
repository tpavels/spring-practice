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
public class Unit extends BaseModel {

    @NotNull(message = "Name cannot be null")
    private String name;

    @Min(value = 0, message = "Health should not be less than 0")
    private Integer maintenanceCost;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "unit"
    )
    private final List<CivUnit> civUnit = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "unit_category_id")
    @NotNull(message = "Category cannot be null")
    private UnitCategory category;

    public String getName() {
        return name;
    }

    public Integer getMaintenanceCost() {
        return maintenanceCost;
    }

    public List<CivUnit> getCivUnit() {
        return civUnit;
    }

    public UnitCategory getCategory() {
        return category;
    }

    public void setCategory(UnitCategory category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaintenanceCost(Integer maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    public void addCivUnit(CivUnit civUnit) {
        this.civUnit.add(civUnit);
    }
}
