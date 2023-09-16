package com.example.saloon.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoomClass {
    NORMAL("Normal"),
    VIP("VIP"),
    PRESIDENT("President"),
    PALACE("Palace");

    private final String roomClass;
}
