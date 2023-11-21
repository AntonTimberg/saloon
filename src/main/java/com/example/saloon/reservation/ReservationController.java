package com.example.saloon.reservation;

import com.example.saloon.room.Room;
import com.example.saloon.room.RoomConverter;
import com.example.saloon.room.RoomDto;
import com.example.saloon.room.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reservations")
@AllArgsConstructor
public class ReservationController {
    private final RoomService roomService;
    private final ReservationService reservationService;
    private final RoomConverter roomConverter;

    @GetMapping("/create/{roomNumber}")
    public String createReservationForm(@PathVariable("roomNumber") Integer roomNumber, Model model) {
        Room room = roomService.getByRoomNumber(roomNumber);
        model.addAttribute("room", roomConverter.convert(room));
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setRoom(roomService.getByRoomNumber(roomNumber));
        return "createReservation";
    }

    @PostMapping("/create")
    public String createReservation(@ModelAttribute ReservationDto reservationDto,
                                    @RequestParam("roomNumber") Integer roomNumber) {
        reservationService.createReservation(reservationDto, roomNumber);
        return "redirect:/rooms/getAll";
    }
}
