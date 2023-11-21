package com.example.saloon.reservation;

import com.example.saloon.room.Room;
import com.example.saloon.room.RoomRepo;
import com.example.saloon.room.RoomStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepo reservationRepo;
    private final RoomRepo roomRepo;

    @Override
    public Reservation createReservation(ReservationDto reservationDto) {
//        Room room = roomRepo.findByRoom(reservationDto.getRoomNumber());
//        if (room == null || !isRoomAvailable(room, reservationDto.getReservationFrom(), reservationDto.getReservationUntil())) {
//            throw new RoomNotAvailableException("Комната не доступна или не существует");
//        }
//        Reservation reservation = convertToReservation(reservationDto, room);
//        reservationRepo.save(reservation);
//        updateRoomStatus(room, RoomStatus.OCCUPIED);
//        return reservation;
        return null;
    }

    @Override
    public boolean isRoomAvailable(Room room, Date from, Date until) {
        return false;
    }

    @Override
    public Reservation convertToReservation(ReservationDto reservationDto, Room room) {
        return null;
    }

    @Override
    public void updateRoomStatus(Room room, RoomStatus status) {

    }
}
