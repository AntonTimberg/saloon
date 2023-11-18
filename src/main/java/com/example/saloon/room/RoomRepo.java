package com.example.saloon.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
    Room findByRoom(Integer room);
    //List<Room> findAll();
}
