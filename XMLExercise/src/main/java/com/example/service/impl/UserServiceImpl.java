package com.example.service.impl;

import com.example.model.dtos.UserSeedDto;
import com.example.model.dtos.UserViewRootDto;
import com.example.model.dtos.UserWithSoldProductsDto;
import com.example.model.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public long getEntityCount() {
        return userRepository.count();
    }

    @Override
    public void seedUsers(List<UserSeedDto> users) {

        users.stream().filter(validationUtil::isValid).map(user -> modelMapper.map(user, User.class))
                .forEach(userRepository::save);

    }

    @Override
    public User getRandomUser() {

        long randomId = ThreadLocalRandom.current().nextLong(1, userRepository.count() + 1);

        return userRepository.findById(randomId).orElse(null);
    }

    @Override
    public UserViewRootDto findUsersWithMoreThanOneSoldProduct() {

        UserViewRootDto userViewRootDto = new UserViewRootDto();

        userViewRootDto.setProducts(userRepository.findAllUsersWithMoreThanOneSoldProduct()
                .stream().map(user -> modelMapper.map(user, UserWithSoldProductsDto.class)).collect(Collectors.toList()));




        return userViewRootDto;
    }
}
