package com.example.cinema_booking.service;

import com.example.cinema_booking.entity.SeatStatusEntity;

import java.util.List;

public interface SeatStatusService {
     SeatStatusEntity createSeatStatus(SeatStatusEntity seatStatus);

    List<SeatStatusEntity> getSeatStatusEntitiesByShowtime(Long id);

    void updateSeatStatus(Long showtimeId, List<String> selectedChairs);
}
