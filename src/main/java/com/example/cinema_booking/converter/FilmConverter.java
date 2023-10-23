package com.example.cinema_booking.converter;

import com.example.cinema_booking.domain.CinemaRoom;
import com.example.cinema_booking.domain.Film;
import com.example.cinema_booking.entity.*;

import java.util.List;

public class FilmConverter {
    public static FilmEntity toEntity(Film film) {
        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setNameFilm(film.getNameFilm());
        filmEntity.setTime(film.getTime());
        filmEntity.setShowtimeEntity((List<ShowtimeEntity>) film.getShowtimeEntity());
        filmEntity.setImageFilm(film.getImageFilm());
        filmEntity.setIntroduce(film.getIntroduce());
        filmEntity.setRating(film.getRating());
        filmEntity.setTrailer(film.getTrailer());
//        filmEntity.setStatus(film.getStatus());
//        filmEntity.setShowtime(film.getShowtime());

        CategoryEntity categoryEntity = new CategoryEntity();
//        cinemaEntity.setNameCinema(cinemaRoom.getNameCinema());
        filmEntity.setCategoryEntity(categoryEntity);

        return filmEntity;
    }

    public static Film toModel(FilmEntity entity) {
        Film film = new Film();
        film.setIdFilm(entity.getIdFilm());
        film.setNameFilm(entity.getNameFilm());
        film.setCategory(CategoryConverter.toModel(entity.getCategoryEntity()));
        film.setTime(entity.getTime());
        film.setIntroduce(entity.getIntroduce());
        film.setTrailer(entity.getTrailer());
        film.setRating(entity.getRating());
        film.setImageFilm(entity.getImageFilm());
        film.setStatus(entity.isStatus());
        film.setDateStart(entity.getDateStart());
        film.setDateEnd(entity.getDateEnd());

        return film;
    }
}
