package com.example.saloon.room;

import com.example.saloon.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class RoomDto {
    private Integer roomNumber;
    private RoomStatus roomStatus;
    private RoomClass roomClass;
    private List<Reservation> reservationList;
    private Integer capacity;
    private String image;
}
