package tpavels.spring.civ.model;


import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CivUnits extends BaseModel {

    @ManyToOne
    @NotNull(message = "Unit cannot be null")
    private Unit unit;
    @OneToOne(cascade = CascadeType.PERSIST)
    @NotNull(message = "Location cannot be null")
    private Location location;
    @Min(value = 0, message = "Health should not be less than 0")
    @Max(value = 100, message = "Health should not be greater than 100")
    private Long health;
    @Min(value = 1, message = "Health should not be less than 1")
    @Max(value = 4, message = "Health should not be greater than 4")
    private Long rank;
}
