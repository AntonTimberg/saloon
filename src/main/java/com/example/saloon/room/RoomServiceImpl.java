package com.example.saloon.room;

import com.example.saloon.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepo roomRepo;

    @Override
    public List<Room> getAll() {
        return roomRepo.findAll();
    }

    @Override
    public void updateStatus(Integer roomNumber,RoomStatus status) {
        Room room = roomRepo.findByRoomNumber(roomNumber);
        if(room != null) room.setStatus(status);
    }

    @Override
    public Integer getCapacity(Integer roomNumber) {
        Room room = roomRepo.findByRoomNumber(roomNumber);
        if (room == null) return 0;
        return room.getCapacity();
    }

    @Override
    public Room getByRoomNumber(Integer roomNumber) {
        return roomRepo.findByRoomNumber(roomNumber);
    }

    @Override
    public List<Reservation> getReservationsForRoom(Integer roomNumber) {
        Room room = roomRepo.findByRoomNumber(roomNumber);
        if (room == null) {
            return new ArrayList<>();
        }
        return room.getReservationList();
    }
}
