package com.example.saloon.room;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.example.saloon.reservation.Reservation;
import java.util.Comparator;
import java.util.Date;

@Component
public class RoomConverter implements Converter<Room, RoomDto> {
    private static final Logger logger = LoggerFactory.getLogger(RoomConverter.class);
    @Override
    public RoomDto convert(Room source) {
        logger.info("Converting room: {}", source.getRoomNumber());
        Date now = new Date();

        Date nextReservationStart = source.getReservationList().stream()
                .filter(reservation -> reservation.getReservationFrom().after(now))
                .min(Comparator.comparing(Reservation::getReservationFrom))
                .map(Reservation::getReservationFrom)
                .orElse(null);

        Date currentReservationEnd = null;
        if (source.getStatus() == RoomStatus.OCCUPIED) {
            currentReservationEnd = source.getReservationList().stream()
                    .filter(reservation -> reservation.getReservationFrom().before(now) &&
                            reservation.getReservationUntil().after(now))
                    .max(Comparator.comparing(Reservation::getReservationUntil))
                    .map(Reservation::getReservationUntil)
                    .orElse(null);
        }
        RoomDto dto = RoomDto.builder().
        roomNumber(source.getRoomNumber())
                .roomClass(source.getRoomclass())
                .roomStatus(source.getStatus())
                .capacity(source.getCapacity())
                .image(source.getImage())
                .nextReservationStart(nextReservationStart)
                .currentReservationEnd(currentReservationEnd)
                .build();

        logger.info("Converted Room DTO: {}", dto);
        return dto;
    }
}
