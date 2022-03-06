package entity._06FootballBettingDatabase;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "teams")
public class Team extends BaseEntity {

    private String name;


    private byte[] logo;

    private String initials;

    @ManyToOne
    @JoinColumn(name = "primary_kit_color")
    private Color primaryKitColor;

    @ManyToOne
    @JoinColumn(name = "secondary_kit_color")
    private Color secondaryKitColor;

    @ManyToOne
    private Town town;

    private BigDecimal budget;

    @OneToMany(mappedBy = "team")
    private Set<Player> players;

}
