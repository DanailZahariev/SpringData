package com.example.springdataexercise.bookshop.usersystem.models;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "towns")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "town_id")
    private Long id;

    private String name;

    private String country;

    @OneToMany(mappedBy = "bornTown", targetEntity = User.class)
    private Set<User> userBornTown;

    @OneToMany(mappedBy = "currentlyLiving", targetEntity = User.class)
    private Set<User> userCurrentlyLiving;
}
