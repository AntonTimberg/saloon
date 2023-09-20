package com.example.saloon.room;

import com.example.saloon.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class RoomDto {
    private Integer room;
    private RoomStatus roomStatus;
    private RoomClass roomClass;
    //private List<Reservation> reservationList;
    private Integer capacity;
}
