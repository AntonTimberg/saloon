package com.example.saloon.member;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.Period;

@Component
public class MemberConverter implements Converter<Member, MemberDto> {
    @Override
    public MemberDto convert(Member source) {
        return MemberDto.builder()
                .name(source.getName())
                .surName(source.getSurname())
                .gender(source.getGender())
                .age(Period.between(source.getBirthDay(), LocalDate.now()).getYears())
                .login(source.getLogin())
                .status(String.valueOf(source.getStatus()))
                .build();
    }
}
