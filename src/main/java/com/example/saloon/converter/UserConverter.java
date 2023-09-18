package com.example.saloon.converter;

import com.example.saloon.dto.UserDto;
import com.example.saloon.entity.Users;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class UserConverter implements Converter<Users, UserDto> {

    @Override
    public UserDto convert(Users source) {
        return UserDto.builder()
                .room(source.getRoom())
                .name(source.getName())
                .surName(source.getSurname())
                .gender(source.getGender())
                .age(Period.between(source.getBirthDay(), LocalDate.now()).getYears())
                .login(source.getLogin())
                .status(String.valueOf(source.getStatus()))
                .build();
    }
}
