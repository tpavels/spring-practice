package tpavels.spring.civ.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class City extends BaseModel {

    @Transient
    private Long cityId;
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Is Capital cannot be null")
    private Boolean isCapital;
    @OneToOne(cascade = CascadeType.PERSIST)
    @NotNull(message = "Location cannot be null")
    private Location location;
    @ManyToMany
    private List<Building> buildings;

}
