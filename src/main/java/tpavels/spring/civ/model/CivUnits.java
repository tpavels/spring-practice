package tpavels.spring.civ.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CivUnits extends BaseModel {

    @ManyToOne
    private Unit unit;
    @OneToOne
    private Location location;
    private Long health;
    private Long rank;
}
