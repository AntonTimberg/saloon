package com.example.saloon.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepo roomRepo;
    private final RoomConverter roomConverter;

    @Override
    public List<RoomDto> getAll() {
        return roomRepo.findAll().stream()
                .map(roomConverter::convert)
                .toList();
    }

    @Override
    public void updateStatus(Integer roomNumber,RoomStatus status) {
        Room room = roomRepo.findByRoom(roomNumber);
        if(room != null) room.setStatus(status);
    }

    @Override
    public Integer getCapacity(Integer roomNumber) {
        Room room = roomRepo.findByRoom(roomNumber);
        if (room == null) return 0;
        return room.getCapacity();
    }
}
