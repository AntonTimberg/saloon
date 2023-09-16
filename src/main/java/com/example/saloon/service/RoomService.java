package com.example.saloon.service;

import com.example.saloon.status.RoomStatus;
import com.example.saloon.entity.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAll();

    void updateStatus(Integer roomNumber, RoomStatus status);
}
