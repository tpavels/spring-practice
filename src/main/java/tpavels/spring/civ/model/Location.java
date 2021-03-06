package tpavels.spring.civ.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Location extends BaseModel {

    @NotNull(message = "x cannot be null")
    private Long x;

    @NotNull(message = "y cannot be null")
    private Long y;

    public Location(Long x, Long y) {
        this.x = x;
        this.y = y;
    }

}
