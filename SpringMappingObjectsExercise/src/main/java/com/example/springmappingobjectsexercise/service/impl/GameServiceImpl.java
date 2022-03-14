package com.example.springmappingobjectsexercise.service.impl;

import com.example.springmappingobjectsexercise.entities.Game;
import com.example.springmappingobjectsexercise.entities.dto.GameAddDto;
import com.example.springmappingobjectsexercise.entities.dto.GameDetailDto;
import com.example.springmappingobjectsexercise.entities.dto.GameViewAllDto;
import com.example.springmappingobjectsexercise.repository.GameRepository;
import com.example.springmappingobjectsexercise.service.GameService;
import com.example.springmappingobjectsexercise.service.UserService;
import com.example.springmappingobjectsexercise.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserService userService;


    public GameServiceImpl(GameRepository gameRepository,
                           ModelMapper modelMapper, ValidationUtil validationUtil, UserService userService) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
    }

    @Override
    public void addGame(GameAddDto gameAddDto) {
        if (getUserAdminStatus()) {
            return;
        }

        Set<ConstraintViolation<GameAddDto>> violations = validationUtil.getViolations(gameAddDto);

        if (!violations.isEmpty()) {
            violations.stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
            return;
        }

        Game game = modelMapper.map(gameAddDto, Game.class);

        gameRepository.save(game);
        System.out.println("Added game " + gameAddDto.getTittle());
    }

    @Override
    public void editGame(long id, BigDecimal price, double size) {
        if (getUserAdminStatus()) {
            return;
        }

        Game game = gameRepository.findById(id).orElse(null);

        if (game == null) {
            System.out.println("Invalid game id.");
            return;
        }

        game.setPrice(price);
        game.setSize(size);
        gameRepository.save(game);
        System.out.println("Edited " + game.getTittle());
    }

    @Override
    public void deleteGame(long id) {
        if (getUserAdminStatus()) {
            return;
        }

        Game game = gameRepository.findById(id).orElse(null);

        if (game == null) {
            return;
        }

        System.out.println("Successfully deleted " + game.getTittle());
        gameRepository.delete(game);
    }

    @Override
    public void viewAllGames() {
        List<Game> allGames = gameRepository.findAll();
        List<GameViewAllDto> gameViewAllDto = new ArrayList<>();


        allGames.stream().map(dto -> modelMapper.map(dto, GameViewAllDto.class))
                .forEach(gameViewAllDto::add);

        gameViewAllDto.forEach(g -> System.out.printf("%s %.2f%n", g.getTittle(), g.getPrice()));

//        for (Game game : allGames) {
//            GameViewAllDto gameViewAllDto = modelMapper.map(game, GameViewAllDto.class);
//            gameViewAllDto.add(gameViewAllDto);
//        }
//
//        gameViewAllDto.forEach(g -> System.out.printf("%s %.2f%n", g.getTittle(), g.getPrice()));


    }

    @Override
    public void detailGameInfo(String name) {
        Game byTittle = gameRepository.findByTittle(name);
        GameDetailDto gameDetailDto = modelMapper.map(byTittle, GameDetailDto.class);

        System.out.printf("%s%n %.2f%n %s%n %s%n", gameDetailDto.getTittle(), gameDetailDto.getPrice(),
                gameDetailDto.getDescription(), gameDetailDto.getReleaseDate());
    }

    private boolean getUserAdminStatus() {
        if (!userService.hasLoggedUser()) {
            System.out.println("No logged user!");
            return true;
        }

        if (!userService.isUserAdmin()) {
            System.out.println("Only administrators can add, edit or delete games.");
            return true;
        }
        return false;
    }
}
