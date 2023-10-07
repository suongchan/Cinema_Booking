package com.example.cinema_booking.domain;

import com.example.cinema_booking.entity.CinemaEntity;
import com.example.cinema_booking.entity.CinemaRoomEntity;
import com.example.cinema_booking.entity.FilmEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter

public class Showtime {
    private Long idShow;
//    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private LocalDate dateShow;
//    @DateTimeFormat(pattern ="HH:mm")
    private LocalTime start;
//    @DateTimeFormat(pattern ="HH:mm")
    private LocalTime end;
    private CinemaEntity cinemaEntity;
    private CinemaRoomEntity cinemaRoomEntity;
    private FilmEntity filmEntity;

}