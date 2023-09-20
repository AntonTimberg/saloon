package com.example.saloon.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberStatus {
    ADMIN("Administrator"),
    STAFF("Stuff"),
    CLIENT("Client"),
    EXCOMMUNICADO("Excommunicado");

    private final String status;
}
