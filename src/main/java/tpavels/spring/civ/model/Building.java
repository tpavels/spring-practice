package tpavels.spring.civ.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Building extends BaseModel{

    @Transient
    private Long buildingId;
    private String name;
    private Integer maintenanceCost;
    private Boolean isWorldWonder;

}
