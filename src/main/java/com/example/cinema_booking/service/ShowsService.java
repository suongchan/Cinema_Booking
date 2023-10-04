package com.example.cinema_booking.service;

import com.example.cinema_booking.entity.ShowsEntity;
import com.example.cinema_booking.exception.ScheduleConflictException;

import java.util.List;

public interface ShowsService {
    List<ShowsEntity> getShow();

    ShowsEntity createShow(ShowsEntity show) throws ScheduleConflictException;

    List<ShowsEntity> getAllShow();
}
