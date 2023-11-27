package com.example.saloon.room;

import com.example.saloon.reservation.Reservation;

import java.util.List;

public interface RoomService {
    List<Room> getAll();

    void updateStatus(Integer roomNumber);

    Integer getCapacity(Integer roomNumber);

    Room getByRoomNumber(Integer roomNumber);
    List<Reservation> getReservationsForRoom(Integer roomNumber);
}
