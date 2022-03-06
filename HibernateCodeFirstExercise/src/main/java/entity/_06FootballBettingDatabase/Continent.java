package entity._06FootballBettingDatabase;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "continents")
public class Continent extends BaseEntity {

    private String name;

    @ManyToMany(targetEntity = Country.class)
    @JoinTable(name = "countries_continents", joinColumns = @JoinColumn(name = "continent_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Set<Country> countries;

}
