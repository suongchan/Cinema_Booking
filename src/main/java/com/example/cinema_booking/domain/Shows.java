package com.example.cinema_booking.domain;

import com.example.cinema_booking.entity.CinemaEntity;
import com.example.cinema_booking.entity.CinemaRoomEntity;
import com.example.cinema_booking.entity.FilmEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter

public class Shows {
    private Long idShow;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private LocalDateTime start;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private LocalDateTime end;
    private CinemaEntity cinemaEntity;
    private CinemaRoomEntity room;
    private FilmEntity filmEntity;

}
