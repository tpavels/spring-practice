package tpavels.spring.civ.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Unit extends BaseModel {

    @Transient
    private Long unitId;
    private String name;
    private Integer maintenanceCost;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private UnitCategory category;

}
