package tpavels.spring.civ.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CivUnits {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Unit unit;
    @OneToOne
    private Location location;
    private Long health;
    private Long rank;
}
