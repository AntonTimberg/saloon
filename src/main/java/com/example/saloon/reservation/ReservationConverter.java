package com.example.saloon.reservation;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ReservationConverter implements Converter<Reservation, ReservationDto> {
    @Override
    public ReservationDto convert(Reservation source) {
        if (source == null) {
            return null;
        }

        return ReservationDto.builder()
                .room(source.getRoom())
                .reservationFrom(source.getReservationFrom())
                .reservationUntil(source.getReservationUntil())
                .userId(source.getUserId())
                .build();
    }
}
