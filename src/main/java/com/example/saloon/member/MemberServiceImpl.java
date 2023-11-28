package com.example.saloon.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepo memberRepo;

    @Override
    public Member createMember(Member member) {
        validateMember(member);
        return memberRepo.save(member);
    }

    @Override
    public List<Member> getAll() {
        return memberRepo.findAll();
    }

    @Override
    public Member updateMember(Member user) {
        if (memberRepo.existsById(user.getId())) {
            return memberRepo.save(user);
        } else {
            throw new RuntimeException("User does not exist");
        }
    }

    @Override
    public void deleteMemberByLogin(String login) {
        Member user = memberRepo.findByLogin(login);
        if (user != null) {
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
    public void validateMember(Member member) {
        if (!Arrays.asList("male", "female").contains(member.getGender().toLowerCase())) {
            throw new IllegalArgumentException("Invalid gender");
        }

        String nameRegex = "^[A-Za-zА-Яа-я]+$";
        if (!Pattern.matches(nameRegex, member.getName()) || !Pattern.matches(nameRegex, member.getSurname())) {
            throw new IllegalArgumentException("Name or Surname contains invalid characters");
        }
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberRepo.findById(id);
    }
}
