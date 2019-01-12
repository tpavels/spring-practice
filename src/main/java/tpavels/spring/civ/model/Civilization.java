package tpavels.spring.civ.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Civilization {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String leader;
    @OneToMany
    private List<City> cities;
    @OneToMany
    private List<Unit> units;
}
