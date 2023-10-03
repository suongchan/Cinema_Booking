package com.example.cinema_booking.domain;

import com.example.cinema_booking.entity.CinemaEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CinemaRoom {
    private CinemaEntity cinemaEntity;
    private Long idCinemaRoom;
    private String nameCinemaRoom;
    private String cinemaRoomDesc;
    private Chair[][] chairs;
    private int numberChair;

//    private String nameCinema;
//    public String getNameCinema() {
//        return cinemaEntity.getNameCinema();
//    }
}
