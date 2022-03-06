package entity._06FootballBettingDatabase;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "users")
public class User extends BaseEntity {

    private String username;

    private String password;

    private String email;

    @Column(name = "full_name")
    private String fullName;

    private BigDecimal balance;

}
