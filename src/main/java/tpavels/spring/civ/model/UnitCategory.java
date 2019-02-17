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
@EqualsAndHashCode(callSuper = true)
public class UnitCategory extends BaseModel {

    private String name;

    public UnitCategory(@NotNull String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
