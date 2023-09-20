package com.example.saloon.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {
    Member findByRoom(Integer room);

    Member findByLogin(String login);
    Long findIdByLogin(String login);
}
