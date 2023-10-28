package com.example.cinema_booking.service;

import com.example.cinema_booking.entity.ChairEntity;
import com.example.cinema_booking.entity.CinemaRoomEntity;

import java.util.List;

public interface ChairService {
    void createChair(CinemaRoomEntity cinemaRoom);


    void saveAllChairs(List<ChairEntity> chairs);

}

