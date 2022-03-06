package entity._06FootballBettingDatabase;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "games")
public class Game extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "home_team")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team")
    private Team awayTeam;

    @Column(name = "home_goals")
    private Integer homeGoals;

    @Column(name = "away_goals")
    private Integer awayGoals;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "home_team_win_bet_rate")
    private Double homeTeamWinBetRate;

    @Column(name = "away_team_win_bet_rate")
    private Double awayTeamWinBetRate;

    @Column(name = "drawn_game_bet_rate")
    private Double drawGameBetRate;

    @ManyToOne
    private Round round;

    @ManyToOne
    private Competition competition;


}
