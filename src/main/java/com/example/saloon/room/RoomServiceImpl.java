package com.example.saloon.room;

import com.example.saloon.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
    public void updateStatus(Integer roomNumber) {
        Room room = roomRepo.findByRoomNumber(roomNumber);
        if (room == null) return;

        List<Reservation> reservations = room.getReservationList();
        LocalDateTime now = LocalDateTime.now();
        boolean isOccupiedOrInMaintenance = reservations.stream()
                .anyMatch(reservation -> {
                    LocalDateTime from = convertToLocalDateTime(reservation.getReservationFrom());
                    LocalDateTime until = convertToLocalDateTime(reservation.getReservationUntil());
                    return (now.isAfter(from.minusDays(1)) && now.isBefore(until.plusHours(4)));
                });

        if (isOccupiedOrInMaintenance) {
            boolean isInMaintenance = reservations.stream()
                    .anyMatch(reservation -> {
                        LocalDateTime until = convertToLocalDateTime(reservation.getReservationUntil());
                        return now.isAfter(until) && now.isBefore(until.plusHours(4));
                    });

            if (isInMaintenance) {
                room.setStatus(RoomStatus.MAINTENANCE);
            } else {
                room.setStatus(RoomStatus.OCCUPIED);
            }
        } else {
            room.setStatus(RoomStatus.AVAILABLE);
        }

        roomRepo.save(room);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void updateAllRoomStatuses() {
        List<Room> rooms = roomRepo.findAll();
        rooms.forEach(room -> updateStatus(room.getRoomNumber()));
    }

    private LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
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
