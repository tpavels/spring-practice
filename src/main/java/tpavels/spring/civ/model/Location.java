package tpavels.spring.civ.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Location extends BaseModel {

    @NotNull(message = "x cannot be null")
    private Long x;

    @NotNull(message = "y cannot be null")
    private Long y;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "civ_unit_id", referencedColumnName = "id")
    private CivUnit civUnit;

    public Location(Long x, Long y) {
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public CivUnit getCivUnit() {
        return civUnit;
    }

    public void setCivUnit(CivUnit civUnit) {
        this.civUnit = civUnit;
    }
}
