package entity._06FootballBettingDatabase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "positions")
public class Position extends BaseEntity {

    @Column(name = "position_description")
    private String positionDescription;

    @OneToMany(mappedBy = "position")
    private Set<Player> players;

}
