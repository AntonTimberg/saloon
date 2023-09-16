package com.example.saloon.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoomStatus {
    AVAILABLE("Available"),
    OCCUPIED("Occupied"),
    MAINTENANCE("Maintenance");

    private final String status;
}
