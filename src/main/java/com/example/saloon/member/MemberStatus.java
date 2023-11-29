package com.example.saloon.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberStatus {
    ADMIN("Administrator"),
    STAFF("Staff"),
    CLIENT("Client"),
    SERVICE("Service"),
    EXCOMMUNICADO("Excommunicado");

    private final String status;
}
