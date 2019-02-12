package tpavels.spring.civ.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Civilization extends BaseModel {

    @Getter
    @Setter
    @Transient
    private Long civilizationId;

    @Getter
    @Setter
    @NotNull(message = "Name cannot be null")
    private String name;

    @Getter
    @Setter
    @NotNull(message = "Leader cannot be null")
    private String leader;

    @Getter
    @Setter
    @Min(value = 0, message = "Gold should not be less than 0")
    private Long gold;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "civilization"
    )
    @Getter
    private final List<City> cities = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "civilization",
            orphanRemoval = true // disconnected object instances are automatically removed.
    )
    @Getter
    private final List<CivUnits> units = new ArrayList<>();

}
