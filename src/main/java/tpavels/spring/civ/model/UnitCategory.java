package tpavels.spring.civ.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "units")
public class UnitCategory extends BaseModel {

    private String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "category"
    )
    private final List<Unit> units = new ArrayList<>();

    public UnitCategory(@NotNull String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Unit> getUnits() {
        return units;
    }
}
