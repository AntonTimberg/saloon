package com.example.saloon.reservation;

import com.example.saloon.room.Room;
import com.example.saloon.room.RoomConverter;
import com.example.saloon.room.RoomService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final RoomService roomService;
    private final ReservationService reservationService;
    private final RoomConverter roomConverter;

    @GetMapping("/create/{roomNumber}")
    public String createReservationForm(@PathVariable("roomNumber") Integer roomNumber, Model model) {
        Room room = roomService.getByRoomNumber(roomNumber);
        model.addAttribute("room", roomConverter.convert(room));
        model.addAttribute("existingReservations", roomService.getReservationsForRoom(roomNumber));
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setRoomNumber(roomNumber);
        model.addAttribute("reservationDto", reservationDto);
        return "createReservation";
    }

    @PostMapping("/create")
    public String createReservation(@ModelAttribute ReservationDto reservationDto,
                                    @RequestParam("roomNumber") Integer roomNumber,
                                    RedirectAttributes redirectAttributes) {
        try {
            reservationService.createReservation(reservationDto, roomNumber);
        } catch (RoomUnavailableException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/reservations/create/" + roomNumber;
        }
        return "redirect:/rooms/getAll";
    }
}
