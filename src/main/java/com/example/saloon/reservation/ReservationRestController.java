package com.example.saloon.reservation;

import com.example.saloon.room.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReservationRestController {
    private final RoomService roomService;
    private final ReservationService reservationService;
    private final ReservationConverter reservationConverter;

    @GetMapping("/reservations/{roomNumber}")
    public ResponseEntity<List<ReservationDto>> getReservations(@PathVariable Integer roomNumber) {
        List<ReservationDto> reservations = roomService.getReservationsForRoom(roomNumber).stream()
                .map(reservationConverter::convert)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/reservations/getBy/{userId}")
    public ResponseEntity<List<ReservationDto>> getByUserId(@PathVariable Long userId) {
        List<ReservationDto> reservation = reservationService.getAllByUserId(userId).stream()
                .map(reservationConverter::convert).toList();
        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/reservations/getBy/{userLogin}")
    public ResponseEntity<List<ReservationDto>> getByUserLogin(@PathVariable String userLogin) {
        List<ReservationDto> reservation = reservationService.getReservationsByUserLogin(userLogin).stream()
                .map(reservationConverter::convert).toList();
        return ResponseEntity.ok(reservation);
    }
}

