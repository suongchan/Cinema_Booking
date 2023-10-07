package com.example.cinema_booking.converter;

import com.example.cinema_booking.domain.Service;
import com.example.cinema_booking.domain.Showtime;
import com.example.cinema_booking.entity.ServiceEntity;
import com.example.cinema_booking.entity.ShowtimeEntity;

public class ShowtimeConverter {
    public static ShowtimeEntity toEntity(Showtime showtime) {
        ShowtimeEntity showtimeEntity = new ShowtimeEntity();
        showtimeEntity.setDateShow(showtime.getDateShow());
        showtimeEntity.setStart(showtime.getStart());
        showtimeEntity.setEnd(showtime.getEnd());
        showtimeEntity.setCinemaEntity(showtime.getCinemaEntity());
        showtimeEntity.setCinemaRoomEntity(showtime.getCinemaRoomEntity());
        showtimeEntity.setFilmEntity(showtime.getFilmEntity());


        return showtimeEntity;
    }
}
