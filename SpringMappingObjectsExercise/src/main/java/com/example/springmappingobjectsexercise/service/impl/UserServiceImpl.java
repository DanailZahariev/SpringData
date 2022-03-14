package com.example.springmappingobjectsexercise.service.impl;

import com.example.springmappingobjectsexercise.entities.User;
import com.example.springmappingobjectsexercise.entities.dto.UserLoginDto;
import com.example.springmappingobjectsexercise.entities.dto.UserRegisterDto;
import com.example.springmappingobjectsexercise.repository.UserRepository;
import com.example.springmappingobjectsexercise.service.UserService;
import com.example.springmappingobjectsexercise.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private User loggedUser;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            System.out.println("Confirm password – must match the provided password.");
        }
        Set<ConstraintViolation<UserRegisterDto>> violation =
                validationUtil.getViolations(userRegisterDto);

        if (!violation.isEmpty()) {
            violation.stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
            return;
        }

        User user = modelMapper.map(userRegisterDto, User.class);
        long count = this.userRepository.count();

        if (count == 0) {
            user.setAdmin(true);
        }

        userRepository.save(user);
        System.out.println(user.getFullName() + " was successfully registered");
    }

    @Override
    public void userLogin(UserLoginDto userLoginDto) {
        Set<ConstraintViolation<UserLoginDto>> violation =
                validationUtil.getViolations(userLoginDto);

        if (!violation.isEmpty()) {
            violation.stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
            return;
        }

        User user = userRepository.
                findAllByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword()).orElse(null);
        if (user == null) {
            System.out.println("Incorrect username / password.");
            return;
        }

        loggedUser = user;
        System.out.println("Successfully logged in " + loggedUser.getFullName());

    }

    @Override
    public void logout() {
        if (loggedUser == null) {
            System.out.println("Cannot log out. No user was logged in.");
        } else {
            loggedUser = null;
        }
    }

    @Override
    public boolean isUserAdmin() {
        return loggedUser.isAdmin();
    }

    @Override
    public boolean hasLoggedUser() {
        return loggedUser != null;
    }
}
