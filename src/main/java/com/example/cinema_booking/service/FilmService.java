package com.example.cinema_booking.service;

import com.example.cinema_booking.domain.Film;
import com.example.cinema_booking.entity.CustomerEntity;
import com.example.cinema_booking.entity.FilmEntity;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDate;
import java.util.List;

public interface FilmService {
    List<FilmEntity> getAllFilm();

    FilmEntity addFilm(FilmEntity film);

    FilmEntity getFilmById(Long id);

    FilmEntity updateFilm(FilmEntity film) throws UserPrincipalNotFoundException;

    void deleteFilm(Long id) throws UserPrincipalNotFoundException;

    List<Film> getAllFilmDC(LocalDate now);

    List<Film> getAllFilmSC(LocalDate now);

    List<FilmEntity> searchFilmsByName(String name_film);
}
