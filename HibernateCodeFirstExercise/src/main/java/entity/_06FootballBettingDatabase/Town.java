package entity._06FootballBettingDatabase;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity(name = "towns")
public class Town extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "town")
    private Set<Team> teams;

    @OneToOne(targetEntity = Country.class)
    private Country country;


}
