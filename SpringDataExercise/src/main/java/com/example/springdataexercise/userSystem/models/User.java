package com.example.springdataexercise.userSystem.models;

import com.example.springdataexercise.userSystem.password.Password;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$")
    @Size(min = 4, max = 30)
    @NotBlank
    private String username;

    @Column(name = "first_name", nullable = false)
    @NotBlank
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    private String lastName;

    @Transient
    private String fullName;

    @Column(nullable = false)
    @Password(containsDigit = true, containsLowerCase = true, containsUpperCase = true, containsSpecialSymbol = true)
    private String password;

    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9.! #$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\. [a-zA-Z0-9-]+)*$")
    private String email;

    @Column(name = "registered_on")
    @Digits(integer = 1, fraction = 3)
    private LocalDate registeredOn;

    @Column(name = "last_time_logged_in")
    private LocalDate lastTimeLoggedIn;

    @NotNull
    @Size(min = 1, max = 120)
    private int age;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "born_town",referencedColumnName = "town_id")
    private Town bornTown;

    @ManyToOne
    @JoinColumn(name = "currently_living",referencedColumnName = "town_id")
    private Town currentlyLiving;

    @ManyToMany
    @JoinTable(name = "users_friends", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private Set<User> userFriends;

    @OneToMany(mappedBy = "owner", targetEntity = Album.class)
    private Set<Album> albums;
}
