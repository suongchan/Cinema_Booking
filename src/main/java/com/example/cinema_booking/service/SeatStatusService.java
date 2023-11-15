package com.example.cinema_booking.service;

import com.example.cinema_booking.entity.SeatStatusEntity;

import java.util.List;

public interface SeatStatusService {


    List<SeatStatusEntity> getSeatStatusEntitiesByShowtime(Long id);

    void updateSeatStatus(Long showtimeId, List<String> selectedChairs);

    SeatStatusEntity createSeatStatus(SeatStatusEntity seatStatus);

    SeatStatusEntity getSeats(Long showtimeId, String selectedChairs);

    SeatStatusEntity getSeatStatusById(Long seatId);

    void setStatus(Long idOrder);
}
