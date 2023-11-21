package com.example.saloon.reservation;

import com.example.saloon.room.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
public class ReservationDto {
    private Room room;
    private Date reservationFrom;
    private Date reservationUntil;
    private Integer userId;
}
