package com.example.saloon.member;

import java.util.List;

public interface MemberService {
    MemberDto createMember(Member user);

    List<MemberDto> getAll();

    MemberDto getMemberByRoom(Integer room);

    void deleteMemberByRoom(Integer room);

    MemberDto updateMember(Member user);

    void deleteMemberByLogin(String login);

    Member findByLogin(String login);

    boolean loginIsExist(String login);

    MemberStatus getStatus();
}
