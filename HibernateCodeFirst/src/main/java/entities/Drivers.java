package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "drivers")
public class Drivers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @ManyToMany
    private Set<Car> vehicles;

    public Drivers() {
        super();
        this.vehicles = new HashSet<>();
    }

    public Set<Car> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Car> vehicles) {
        this.vehicles = vehicles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
