package com.example.springdataexercise.bookshop.usersystem.models;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private Long id;

    private String title;

    private String caption;

    private String path;

    @ManyToMany(mappedBy = "pictures",targetEntity = Album.class)
    private Set<Album> albums;

}
