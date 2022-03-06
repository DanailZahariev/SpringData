package entity._06FootballBettingDatabase;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "rounds")
public class Round extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "round")
    private Set<Game> game;
}
