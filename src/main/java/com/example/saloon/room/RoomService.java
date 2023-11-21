package com.example.saloon.room;

import com.example.saloon.reservation.ReservationDto;

import java.util.List;

public interface RoomService {
    List<RoomDto> getAll();

    void updateStatus(Integer roomNumber, RoomStatus status);

    Integer getCapacity(Integer roomNumber);

    Room getByRoomNumber(Integer roomNumber);
    List<ReservationDto> getReservationsForRoom(Integer roomNumber);
}
