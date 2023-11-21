package com.example.saloon.room;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoomConverter implements Converter<Room, RoomDto> {

    @Override
    public RoomDto convert(Room source) {
        return RoomDto.builder()
                .roomNumber(source.getRoomNumber())
                .roomClass(source.getRoomclass())
                .roomStatus(source.getStatus())
                .capacity(source.getCapacity())
                .reservationList(source.getReservationList())
                .image(source.getImage())
                .build();
    }
}
