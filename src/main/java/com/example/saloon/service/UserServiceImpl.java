package com.example.saloon.service;

import com.example.saloon.converter.UserConverter;
import com.example.saloon.dto.UserDto;
import com.example.saloon.entity.Users;
import com.example.saloon.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    private final UserConverter userConverter;


    @Override
    public UserDto createUser(Users user) {
        return userConverter.convert(userRepo.save(user));
    }

    @Override
    public List<UserDto> getAll() {
        return userRepo.findAll().stream()
                .map(userConverter::convert)
                .toList();
    }

    @Override
    public UserDto getUserByRoom(Integer room) {
        return userConverter.convert(userRepo.findByRoom(room));
    }

    @Override
    public void deleteUserByRoom(Integer room) {
        Users user = userRepo.findByRoom(room);
        if (user != null){
            userRepo.delete(user);
        }
    }

    @Override
    public UserDto updateUser(Users user) {
        if (userRepo.existsById(user.getId())){
            return userConverter.convert(userRepo.save(user));
        } else {
            throw new RuntimeException("User does not exist");
        }
    }

    @Override
    public void deleteUserByLogin(String login) {
        userRepo.delete(userRepo.findByLogin(login));
    }

    @Override
    public Users findByLogin(String login) {
        return userRepo.findByLogin(login);
    }
}
