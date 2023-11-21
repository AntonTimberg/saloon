package com.example.saloon.reservation;

import com.example.saloon.room.RoomRepo;
import com.example.saloon.room.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{roomNumber}")
    public ResponseEntity<List<ReservationDto>> getReservations(@PathVariable Integer roomNumber) {
        List<ReservationDto> reservations = roomService.getReservationsForRoom(roomNumber);
        return ResponseEntity.ok(reservations);
    }
}

