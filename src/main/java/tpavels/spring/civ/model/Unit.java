package tpavels.spring.civ.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Unit extends BaseModel {

    @Transient
    private Long unitId;
    @NotNull(message = "Name cannot be null")
    private String name;
    @Min(value = 0, message = "Health should not be less than 0")
    private Integer maintenanceCost;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @NotNull(message = "Category cannot be null")
    private UnitCategory category;

}
