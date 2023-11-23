package com.example.saloon.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepo memberRepo;

    private final MemberConverter memberConverter;


    @Override
    public MemberDto createMember(Member member) {
        validateMember(member);
        return memberConverter.convert(memberRepo.save(member));
    }

    @Override
    public List<MemberDto> getAll() {
        return memberRepo.findAll().stream()
                .map(memberConverter::convert)
                .toList();
    }
    @Override
    public MemberDto updateMember(Member user) {
        if (memberRepo.existsById(user.getId())){
            return memberConverter.convert(memberRepo.save(user));
        } else {
            throw new RuntimeException("User does not exist");
        }
    }

    @Override
    public void deleteMemberByLogin(String login) {
        Member user = memberRepo.findByLogin(login);
        if (user != null){
            memberRepo.delete(user);
        }
    }

    @Override
    public Member findByLogin(String login) {
        return memberRepo.findByLogin(login);
    }

    @Override
    public boolean loginIsExist(String login) {
        return memberRepo.existsByLogin(login);
    }

    @Override
    public MemberStatus getStatus() {
        return getStatus();
    }

    @Override
    public void validateMember(Member member) {
        if (!Arrays.asList("male", "female").contains(member.getGender().toLowerCase())) {
            throw new IllegalArgumentException("Invalid gender");
        }

        String nameRegex = "^[A-Za-zА-Яа-я]+$";
        if (!Pattern.matches(nameRegex, member.getName()) || !Pattern.matches(nameRegex, member.getSurname())) {
            throw new IllegalArgumentException("Name or Surname contains invalid characters");
        }

        String loginPasswordRegex = "^[A-Za-z0-9]+$";
        if (!Pattern.matches(loginPasswordRegex, member.getLogin()) || !Pattern.matches(loginPasswordRegex, member.getPassword())) {
            throw new IllegalArgumentException("Login or Password contains invalid characters");
        }
    }
}
