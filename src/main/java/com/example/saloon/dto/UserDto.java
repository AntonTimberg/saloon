package com.example.saloon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
public class UserDto {
    private Integer room;

    private String name;

    private String surName;

    private String gender;

    private Integer age;

    private String login;

    private String status;
}
