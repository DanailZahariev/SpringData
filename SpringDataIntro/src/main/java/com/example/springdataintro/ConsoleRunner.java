package com.example.springdataintro;

import com.example.springdataintro.models.User;
import com.example.springdataintro.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;


    @Override
    public void run(String... args) throws Exception {

        User dani = new User("dani", 32);
        userService.registerUser(dani);

        User dani2 = new User("dani", 32);
        userService.registerUser(dani2);

    }
}
