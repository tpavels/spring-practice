package tpavels.spring.civ.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class City {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean isCapital;
    @ManyToMany
    private List<Building> buildings;

}
