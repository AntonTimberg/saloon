package com.example.saloon.reservation;

import com.example.saloon.member.Member;
import com.example.saloon.member.MemberRepo;
import com.example.saloon.room.Room;
import com.example.saloon.room.RoomRepo;
import com.example.saloon.room.RoomService;
import com.example.saloon.room.RoomStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepo reservationRepo;
    private final RoomRepo roomRepo;
    private final MemberRepo memberRepo;
    private final RoomService roomService;

    @Override
    public Reservation createReservation(ReservationDto reservationDto, Integer roomNumber) {
        Room room = roomRepo.findByRoomNumber(roomNumber);
        if (room == null) {
            throw new NullPointerException("\n" +
                    "The room does not exist");
        }
        if (!isRoomAvailable(room, reservationDto.getReservationFrom(),
                reservationDto.getReservationUntil())) {
            throw new RoomUnavailableException("\n" +
                    "The room is already booked for this period");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Member currentUser = memberRepo.findByLogin(currentUserName);

        Reservation reservation = Reservation.builder()
                .room(room)
                .reservationFrom(reservationDto.getReservationFrom())
                .reservationUntil(reservationDto.getReservationUntil())
                .userId(currentUser.getId())
                .build();

        reservationRepo.save(reservation);
        roomService.updateStatus(roomNumber);

        return reservation;
    }

    @Override
    public boolean isRoomAvailable(Room room, Date from, Date until) {
        return room.getReservationList().stream()
                .noneMatch(reservation ->
                        reservation.getReservationFrom().before(until) &&
                                reservation.getReservationUntil().after(from)
                );
    }

    @Override
    public Reservation convertToReservation(ReservationDto reservationDto, Room room) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Member currentUser = memberRepo.findByLogin(currentUserName);

        return Reservation.builder()
                .room(room)
                .reservationFrom(reservationDto.getReservationFrom())
                .reservationUntil(reservationDto.getReservationUntil())
                .userId(currentUser.getId())
                .build();
    }

    @Override
    public void updateRoomStatus(Room room, RoomStatus status) {

    }

    @Override
    public List<Reservation> getAllByUserId(Long userId) {
        return reservationRepo.getAllByUserId(userId);
    }
}
