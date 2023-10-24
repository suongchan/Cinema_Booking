package com.example.cinema_booking.service;

import com.example.cinema_booking.entity.ChairEntity;
import com.example.cinema_booking.entity.CinemaEntity;
import com.example.cinema_booking.entity.CinemaRoomEntity;
import com.example.cinema_booking.entity.ShowtimeEntity;

import java.util.List;

public interface ChairService {
    void createChair(CinemaRoomEntity cinemaRoom);

}
