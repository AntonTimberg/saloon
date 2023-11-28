package com.example.saloon;

import com.example.saloon.member.MemberConverter;
import com.example.saloon.member.MemberDto;
import com.example.saloon.member.MemberService;
import com.example.saloon.reservation.ReservationConverter;
import com.example.saloon.reservation.ReservationDto;
import com.example.saloon.reservation.ReservationService;
import com.example.saloon.room.RoomConverter;
import com.example.saloon.room.RoomDto;
import com.example.saloon.room.RoomService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final MemberService memberService;
    private final ReservationService reservationService;
    private final RoomService roomService;
    private final RoomConverter roomConverter;
    private final ReservationConverter reservationConverter;
    private final MemberConverter memberConverter;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/clients")
    @ResponseBody
    public List<MemberDto> getClients() {
        return memberService.getAll().stream()
                .map(memberConverter::convert)
                .collect(Collectors.toList());
    }

    @GetMapping("/bookings")
    @ResponseBody
    public List<ReservationDto> getBookings() {
        return reservationService.getAll().stream()
                .map(reservationConverter::convert).collect(Collectors.toList());
    }

    @GetMapping("/loginById/{userId}")
    @ResponseBody
    public String loginById(@PathVariable Long userId){
        return memberService.findById(userId).get().getLogin().toString();
    }

    @GetMapping("/rooms")
    @ResponseBody
    public List<RoomDto> getRooms() {
        return roomService.getAll().stream().map(roomConverter::convert).collect(Collectors.toList());
    }


}
