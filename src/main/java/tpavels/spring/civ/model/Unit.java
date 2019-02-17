package tpavels.spring.civ.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
}
