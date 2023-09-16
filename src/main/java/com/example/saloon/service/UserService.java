package com.example.saloon.service;

import com.example.saloon.dto.UserDto;
import com.example.saloon.entity.Users;

import java.util.List;

public interface UserService {
    UserDto createUser(Users user);

    List<UserDto> getAll();

    UserDto getUserByRoom(Integer room);

    void deleteUserByRoom(Integer room);

    UserDto updateUser(Users user);

    void deleteUserByLogin(String login);

    Users findByLogin(String login);
}
