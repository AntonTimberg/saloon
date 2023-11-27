package com.example.saloon.room;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);
    private final RoomService roomService;
    private final RoomConverter roomConverter;

    @RequestMapping("/getAll")
    public String listRooms(Model model) {
        logger.info("Fetching all rooms");
        List<Room> rooms = roomService.getAll();
        rooms.forEach(room -> roomService.updateStatus(room.getRoomNumber()));
        List<RoomDto> roomDtos = rooms.stream()
                .map(roomConverter::convert)
                .collect(Collectors.toList());

        roomDtos.forEach(dto -> logger.info("Room DTO: {}", dto));

        logger.info("Number of rooms found: {}", roomDtos.size());
        model.addAttribute("rooms", roomDtos);
        return "rooms";
    }
}
