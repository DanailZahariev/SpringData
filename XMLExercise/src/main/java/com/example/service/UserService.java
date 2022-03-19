package com.example.service;

import com.example.model.dtos.UserSeedDto;
import com.example.model.dtos.UserViewRootDto;
import com.example.model.entity.User;

import java.util.List;

public interface UserService {

    long getEntityCount();

    void seedUsers(List<UserSeedDto> users);

    User getRandomUser();

    UserViewRootDto findUsersWithMoreThanOneSoldProduct();
}
