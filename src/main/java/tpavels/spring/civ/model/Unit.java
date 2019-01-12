package tpavels.spring.civ.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Unit {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer maintenanceCost;
    @ManyToOne
    private UnitCategory category;

}
