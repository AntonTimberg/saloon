package com.example.saloon.reservation;

import com.example.saloon.room.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private Integer roomNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reservationFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reservationUntil;
    private Long userId;
}
