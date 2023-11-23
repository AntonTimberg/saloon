package com.example.saloon.reservation;

import com.example.saloon.room.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationGetController {
    private final RoomService roomService;
    private final ReservationRepo reservationRepo;
    private final ReservationConverter reservationConverter;

    @GetMapping("/{roomNumber}")
    public ResponseEntity<List<ReservationDto>> getReservations(@PathVariable Integer roomNumber) {
        List<ReservationDto> reservations = roomService.getReservationsForRoom(roomNumber);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/getBy/{userId}")
    public List<ReservationDto> getByUserId(@PathVariable Long userId) {
        return reservationRepo.getAllByUserId(userId).stream().map(a -> reservationConverter.convert(a)).toList();
    }
}

