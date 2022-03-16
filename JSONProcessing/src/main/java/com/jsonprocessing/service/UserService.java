package com.jsonprocessing.service;

import com.jsonprocessing.model.dto.UserSoldDto;
import com.jsonprocessing.model.dto.UserWithSoldProductsDto;
import com.jsonprocessing.model.entities.User;

import java.io.IOException;
import java.util.List;

public interface UserService {

    void seedUsers() throws IOException;

    User findRandomUser();

    List<UserSoldDto> findAllUsersWithMoreThanOneSoldProducts();

    List<UserWithSoldProductsDto> findAllUsersWithSoldProducts();
}
