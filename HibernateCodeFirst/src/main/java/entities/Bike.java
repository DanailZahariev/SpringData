package entities;

import javax.persistence.*;

@Entity
@Table(name = "bikes")
public class Bike extends Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
