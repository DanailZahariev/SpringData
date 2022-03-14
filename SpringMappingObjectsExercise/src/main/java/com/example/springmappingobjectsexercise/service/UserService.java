package com.example.springmappingobjectsexercise.service;

import com.example.springmappingobjectsexercise.entities.dto.UserLoginDto;
import com.example.springmappingobjectsexercise.entities.dto.UserRegisterDto;

public interface UserService {

    void registerUser(UserRegisterDto userRegisterDto);

    void userLogin(UserLoginDto userLoginDto);

    void logout();

    boolean isUserAdmin();

    boolean hasLoggedUser();
}
