package entity._06FootballBettingDatabase;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "bet_games")
public class BetGame implements Serializable {

    @Id
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    @ManyToOne
    private Game game;

    @Id
    @JoinColumn(name = "bet_id", referencedColumnName = "id")
    @ManyToOne
    private Bet bet;

    @ManyToOne
    @JoinColumn(name = "result_prediction")
    private ResultPrediction resultPrediction;


}
