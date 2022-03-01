package com.example.springdataintro.services;

import com.example.springdataintro.models.User;
import com.example.springdataintro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {


    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        User byUsername = this.userRepository.findByUsername(user.getUsername());

        if (byUsername == null){
            this.userRepository.save(user);
        }
    }
}
