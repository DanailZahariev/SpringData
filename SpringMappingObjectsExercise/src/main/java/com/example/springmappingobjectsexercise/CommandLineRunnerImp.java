package com.example.springmappingobjectsexercise;

import com.example.springmappingobjectsexercise.entities.dto.GameAddDto;
import com.example.springmappingobjectsexercise.entities.dto.UserLoginDto;
import com.example.springmappingobjectsexercise.entities.dto.UserRegisterDto;
import com.example.springmappingobjectsexercise.service.GameService;
import com.example.springmappingobjectsexercise.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component
public class CommandLineRunnerImp implements CommandLineRunner {

    private final Scanner scanner;
    private final UserService userService;
    private final GameService gameService;

    public CommandLineRunnerImp(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
        scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) {

        while (true) {

            System.out.println("Enter command: ");

            String[] commandsName = scanner.nextLine().split("\\|");

            switch (commandsName[0]) {
                case "RegisterUser":
                    userService.registerUser(new UserRegisterDto(commandsName[1], commandsName[2],
                            commandsName[3], commandsName[4]));
                    break;
                case "LoginUser":
                    userService.userLogin(new UserLoginDto(commandsName[1], commandsName[2]));
                    break;
                case "Logout":
                    userService.logout();
                    break;
                case "AddGame":
                    gameService.addGame(new GameAddDto(commandsName[1], new BigDecimal(commandsName[2]),
                            Double.parseDouble(commandsName[3]),
                            commandsName[4], commandsName[5], commandsName[6],
                            LocalDate.parse(commandsName[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
                    break;
                case "EditGame":
                    String[] split = commandsName[2].split("price=");
                    String[] split1 = commandsName[3].split("size=");
                    BigDecimal price = new BigDecimal(split[1]);
                    double size = Double.parseDouble(split1[1]);
                    System.out.println(price);
                    System.out.println(size);
                    gameService.editGame(Long.parseLong(commandsName[1]),
                            price, size);
                    break;
                case "DeleteGame":
                    gameService.deleteGame(Long.parseLong(commandsName[1]));
                    break;
                case "AllGames":
                    gameService.viewAllGames();
                    break;
                case "DetailGame":
                    gameService.detailGameInfo(commandsName[1]);
                    break;
                case "Exit":
                    return;
                default:
                    throw new IllegalStateException("Invalid Command " + commandsName[0]);
            }
        }
    }
}
