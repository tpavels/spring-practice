package tpavels.spring.civ.model;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true)
public class UnitCategory extends BaseModel {

    @NotNull(message = "Name cannot be null")
    private String name;

}
