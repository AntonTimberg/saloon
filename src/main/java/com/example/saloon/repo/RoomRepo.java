package com.example.saloon.repo;

import com.example.saloon.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
    Room findByRoom(Integer room);
}
