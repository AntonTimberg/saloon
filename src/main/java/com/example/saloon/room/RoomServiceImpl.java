package com.example.saloon.room;

import com.example.saloon.reservation.ReservationConverter;
import com.example.saloon.reservation.ReservationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepo roomRepo;
    private final RoomConverter roomConverter;
    private final ReservationConverter reservationConverter;

    @Override
    public List<RoomDto> getAll() {
        return roomRepo.findAll().stream()
                .map(roomConverter::convert)
                .toList();
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
    public List<ReservationDto> getReservationsForRoom(Integer roomNumber) {
        Room room = roomRepo.findByRoomNumber(roomNumber);
        if (room == null) {
            // Обработка случая, если комната не найдена
            return new ArrayList<>();
        }
        return room.getReservationList().stream()
                .map(a -> reservationConverter.convert(a))
                .collect(Collectors.toList());
    }
}
