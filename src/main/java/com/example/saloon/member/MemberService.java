package com.example.saloon.member;

import java.util.List;

public interface MemberService {
    Member createMember(Member user);

    List<Member> getAll();

    Member updateMember(Member user);

    void deleteMemberByLogin(String login);

    Member findByLogin(String login);

    boolean loginIsExist(String login);

    void validateMember(Member member);
}
