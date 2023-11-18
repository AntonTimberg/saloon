package com.example.saloon.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    //List<Reservation> findByRoomAndTimeRange(Room room, Date startTime, Date endTime);
    Reservation getById(Long id);
}
