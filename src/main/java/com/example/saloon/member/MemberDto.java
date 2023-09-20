package com.example.saloon.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class MemberDto {
    private Integer room;

    private String name;

    private String surName;

    private String gender;

    private Integer age;

    private String login;

    private String status;
}
