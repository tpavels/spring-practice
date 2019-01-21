package tpavels.spring.civ.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Civilization extends BaseModel {

    @Transient
    private Long civilizationId;
    private String name;
    private String leader;
    private Long gold;

    @OneToMany
    private List<City> cities;
    @OneToMany
    @JoinColumn(name = "civilization_id", insertable = false, updatable = false)
    private List<CivUnits> units;
}
