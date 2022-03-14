package com.example.springmappingobjectsexercise.repository;

import com.example.springmappingobjectsexercise.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GameRepository extends JpaRepository<Game, Long> {

    Game findByTittle(String tittle);

}
