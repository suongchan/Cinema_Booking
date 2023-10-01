package com.example.cinema_booking.service;

import com.example.cinema_booking.entity.FilmEntity;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public interface FilmService {
    List<FilmEntity> getAllFilm();

    FilmEntity addFilm(FilmEntity film);

    FilmEntity getFilmById(Long id);

    FilmEntity updateFilm(FilmEntity film) throws UserPrincipalNotFoundException;

    void deleteFilm(Long id) throws UserPrincipalNotFoundException;
}
