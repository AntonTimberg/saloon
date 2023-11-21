package com.example.saloon.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;
    private final RoomRepo roomRepo;
    @RequestMapping("/getAll")
    public String listRooms(Model model) {
        List<RoomDto> rooms = roomService.getAll();

        model.addAttribute("rooms", rooms);
        return "rooms";
    }
}
