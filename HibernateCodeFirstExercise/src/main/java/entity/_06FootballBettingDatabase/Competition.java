package entity._06FootballBettingDatabase;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "competitions")
public class Competition extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "competition_type")
    private CompetitionTypes competitionTypes;

    @OneToMany(mappedBy = "competition")
    private Set<Game> games;
}
