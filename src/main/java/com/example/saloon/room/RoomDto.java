package com.example.saloon.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
public class RoomDto {
    private Integer roomNumber;
    private RoomStatus roomStatus;
    private RoomClass roomClass;
    private Integer capacity;
    private String image;
    private Date nextReservationStart;
    private Date currentReservationEnd;
}
