package com.example.saloon.controller;

import com.example.saloon.status.RoomStatus;
import com.example.saloon.entity.Room;
import com.example.saloon.repo.RoomRepo;
import com.example.saloon.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;
    private final RoomRepo roomRepo;

    @RequestMapping("/getAll")
    public List<Room> getAllRooms() {
        return roomService.getAll();
    }

    @GetMapping("/update/{room}/{status}")
    public ResponseEntity<String> updateStatus(@PathVariable Integer room, @PathVariable String status) {
        Room roomEntity = roomRepo.findByRoom(room);
        if (roomEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");
        } else if (roomEntity.getStatus() == RoomStatus.OCCUPIED) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room is occupied");
        } else {
            roomEntity.setStatus(RoomStatus.valueOf(status));
            roomRepo.save(roomEntity);
            return ResponseEntity.ok("Room status has changed");
        }
    }
}
