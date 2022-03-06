package entity._06FootballBettingDatabase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity(name = "countries")
public class Country implements Serializable {

    @Id
    @Column(length = 3)
    private String id;

    private String name;

    @ManyToMany(mappedBy = "countries")
    private Set<Continent> continents;

    @ManyToOne
    private Town towns;

}
