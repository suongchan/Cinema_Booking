package com.example.cinema_booking.converter;

import com.example.cinema_booking.domain.Category;
import com.example.cinema_booking.domain.Cinema;
import com.example.cinema_booking.entity.CategoryEntity;
import com.example.cinema_booking.entity.CinemaEntity;

public class CinemaConverter {
    public static CinemaEntity toEntity(Cinema cinema) {
        CinemaEntity cinemaEntity = new CinemaEntity();
        cinemaEntity.setNameCinema(cinema.getNameCinema());
        cinemaEntity.setAddressCinema(cinema.getAddressCinema());
        cinemaEntity.setImageCinema(cinema.getImageCinema());
        cinemaEntity.setCity(cinema.getCity());

        return cinemaEntity;
    }
}
