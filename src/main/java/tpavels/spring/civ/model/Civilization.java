package tpavels.spring.civ.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Civilization extends BaseModel {

    @Transient
    private Long civilizationId;
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Leader cannot be null")
    private String leader;
    @Min(value = 0, message = "Gold should not be less than 0")
    private Long gold;

    @OneToMany
    private List<City> cities;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "civilization_id", insertable = true, updatable = true)
    private List<CivUnits> units;
}
