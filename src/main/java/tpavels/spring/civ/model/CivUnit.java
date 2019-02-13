package tpavels.spring.civ.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CivUnit extends BaseModel {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "unit_id")
    @NotNull(message = "Unit cannot be null")
    private Unit unit;

    @OneToOne(mappedBy = "civUnit", cascade = CascadeType.PERSIST)
    @NotNull(message = "Location cannot be null")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "civilization_id")
    private Civilization civilization;

    @Min(value = 0, message = "Health should not be less than 0")
    @Max(value = 100, message = "Health should not be greater than 100")
    private Integer health;

    @Min(value = 1, message = "Rank should not be less than 1")
    @Max(value = 4, message = "Rank should not be greater than 4")
    private Integer rank;

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Civilization getCivilization() {
        return civilization;
    }

    public void setCivilization(Civilization civilization) {
        this.civilization = civilization;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
