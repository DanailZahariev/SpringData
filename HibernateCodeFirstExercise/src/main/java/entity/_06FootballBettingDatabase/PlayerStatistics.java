package entity._06FootballBettingDatabase;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "player_statistics")
public class PlayerStatistics implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id",referencedColumnName = "id")
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "player_id",referencedColumnName = "id")
    private Player player;

    @Column(name = "scored_goals")
    private Integer scoredGoals;

    @Column(name = "player_assist")
    private Integer playerAssist;

    @Column(name = "played_minutes")
    private Integer playedMinutes;


}
