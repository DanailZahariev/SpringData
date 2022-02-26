package entities;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car extends Vehicle {

    private int seats;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
