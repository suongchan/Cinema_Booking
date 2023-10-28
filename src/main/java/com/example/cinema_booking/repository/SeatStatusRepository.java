package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.SeatStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatStatusRepository extends JpaRepository<SeatStatusEntity, Long> {

    List<SeatStatusEntity> findByShowtimeIdShow(Long showtimeId);

    SeatStatusEntity findByShowtimeIdShowAndChairNameChair(Long showtimeId, String chairName);
}

