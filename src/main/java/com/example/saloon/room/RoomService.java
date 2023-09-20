package com.example.saloon.room;

import java.util.List;

public interface RoomService {
    List<RoomDto> getAll();

    void updateStatus(Integer roomNumber, RoomStatus status);

    Integer getCapacity(Integer roomNumber);
}
