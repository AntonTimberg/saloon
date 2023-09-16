package com.example.saloon.service;

import com.example.saloon.status.RoomStatus;

import com.example.saloon.entity.Room;
import com.example.saloon.repo.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{
    private final RoomRepo roomRepo;


    @Override
    public List<Room> getAll() {
        return roomRepo.findAll();
    }

    @Override
    public void updateStatus(Integer roomNumber,RoomStatus status) {
        Room room = roomRepo.findByRoom(roomNumber);
        if(room != null) room.setStatus(status);
    }
}
