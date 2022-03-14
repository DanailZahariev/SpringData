package com.example.springmappingobjectsexercise.service;

import com.example.springmappingobjectsexercise.entities.dto.GameAddDto;

import java.math.BigDecimal;

public interface GameService {

    void addGame(GameAddDto gameAddDto);

    void editGame(long id, BigDecimal price, double size);

    void deleteGame(long id);

    void viewAllGames();

    void detailGameInfo(String name);
}
