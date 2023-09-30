package com.example.cinema_booking.converter;

import com.example.cinema_booking.domain.CinemaRoom;
import com.example.cinema_booking.domain.Film;
import com.example.cinema_booking.entity.CategoryEntity;
import com.example.cinema_booking.entity.CinemaEntity;
import com.example.cinema_booking.entity.CinemaRoomEntity;
import com.example.cinema_booking.entity.FilmEntity;

public class FilmConverter {
    public static FilmEntity toEntity(Film film) {
        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setNameFilm(film.getNameFilm());
        filmEntity.setTime(film.getTime());
        filmEntity.setImageFilm(film.getImageFilm());
        filmEntity.setIntroduce(film.getIntroduce());
        filmEntity.setRating(film.getRating());
        filmEntity.setTrailer(film.getTrailer());
        filmEntity.setStatus(film.getStatus());
        filmEntity.setShowtime(film.getShowtime());

        CategoryEntity categoryEntity = new CategoryEntity();
//        cinemaEntity.setNameCinema(cinemaRoom.getNameCinema());
        filmEntity.setCategoryEntity(categoryEntity);

        return filmEntity;
    }
}
