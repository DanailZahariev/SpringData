package com.example.springdataexercise.bookshop.usersystem.models;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Long id;

    private String name;

    @Column(name = "background_color")
    private String backgroundColor;

    @Column(name = "is_public")
    private boolean isPublic;

    @ManyToMany
    @JoinTable(name = "albums_pictures", joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id", referencedColumnName = "picture_id"))
    private Set<Picture> pictures;

    @ManyToOne
    @JoinColumn(name = "owner_id",referencedColumnName = "id")
    private User owner;

}
