package com.example.saloon.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatus {
    ADMIN("Administrator"),
    STAFF("Stuff"),
    CLIENT("Client"),
    EXCOMMUNICADO("Excommunicado");

    private final String status;
}
