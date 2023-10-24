package com.example.cinema_booking.service;

import com.example.cinema_booking.entity.ShowtimeEntity;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public interface ShowtimeService {


    ShowtimeEntity createShows(ShowtimeEntity show) throws UserPrincipalNotFoundException;

    List<ShowtimeEntity> getAllShows();

    void deleteShow(Long id) throws UserPrincipalNotFoundException;

    ShowtimeEntity update(ShowtimeEntity showtime);

    ShowtimeEntity getShowtimeById(Long id);
}
