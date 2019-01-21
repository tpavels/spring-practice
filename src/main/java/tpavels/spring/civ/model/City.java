package tpavels.spring.civ.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class City extends BaseModel {

    @Transient
    private Long cityId;
    private String name;
    private Boolean isCapital;
    @OneToOne
    private Location location;
    @ManyToMany
    private List<Building> buildings;

}
