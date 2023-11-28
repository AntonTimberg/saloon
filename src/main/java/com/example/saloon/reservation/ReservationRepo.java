package com.example.saloon.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    Reservation getById(Long id);
    List<Reservation> getAllByUserId(Long userId);
}
