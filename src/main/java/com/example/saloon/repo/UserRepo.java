package com.example.saloon.repo;

import com.example.saloon.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
    Users findByRoom(Integer room);

    Users findByLogin(String login);
    Long findIdByLogin(String login);
}
