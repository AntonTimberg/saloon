package com.example.saloon.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
