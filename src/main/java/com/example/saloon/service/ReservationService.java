package com.example.saloon.service;

import com.example.saloon.entity.Reservation;

import java.time.LocalDate;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);

}
