package tpavels.spring.civ.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long x;
    private Long y;
}
