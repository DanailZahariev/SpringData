package entity._06FootballBettingDatabase;

import javax.persistence.*;


@Entity(name = "players")
public class Player extends BaseEntity {

    private String name;

    @Column(name = "squad_number")
    private Short squadNumber;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Position position;

    @Column(name = "is_injured")
    private Boolean isInjured;


}
