package com.example.cinema_booking.domain;

import com.example.cinema_booking.entity.ChairEntity;
import com.example.cinema_booking.entity.CinemaEntity;
import com.example.cinema_booking.entity.CinemaRoomEntity;
import com.example.cinema_booking.entity.ShowtimeEntity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Chair {
    private Long idChair;

    private String nameChair;


    private boolean isOccupied;

    private CinemaRoomEntity cinemaRoom;

    private CinemaEntity cinemaEntity;


}
