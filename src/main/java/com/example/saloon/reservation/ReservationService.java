package com.example.saloon.reservation;

import com.example.saloon.room.Room;
import com.example.saloon.room.RoomStatus;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    Reservation createReservation(ReservationDto reservationDto, Integer roomNumber);
    boolean isRoomAvailable(Room room, Date from, Date until);
    Reservation convertToReservation(ReservationDto reservationDto, Room room);
    void updateRoomStatus(Room room, RoomStatus status);
    List<Reservation> getAllByUserId(Long userId);
}
