package com.example.saloon.repo;

import com.example.saloon.entity.Reservation;
import com.example.saloon.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    //List<Reservation> findByRoomAndTimeRange(Room room, Date startTime, Date endTime);
    Reservation getById(Long id);
}
