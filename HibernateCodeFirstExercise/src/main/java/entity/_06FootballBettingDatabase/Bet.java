package entity._06FootballBettingDatabase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "bets")
public class Bet extends BaseEntity {

    @Column(name = "bet_money")
    private BigDecimal betMoney;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @ManyToOne
    private User user;



}
