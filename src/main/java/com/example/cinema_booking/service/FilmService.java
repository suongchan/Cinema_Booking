package com.example.cinema_booking.service;

import com.example.cinema_booking.entity.FilmEntity;

import java.util.List;

public interface FilmService {
    List<FilmEntity> getAllFilm();

    FilmEntity addFilm(FilmEntity film);
}
