package com.example.cinema_booking.converter;

import com.example.cinema_booking.domain.Cinema;
import com.example.cinema_booking.domain.CinemaRoom;
import com.example.cinema_booking.entity.CinemaEntity;
import com.example.cinema_booking.entity.CinemaRoomEntity;

public class RoomConverter {
    public static CinemaRoomEntity toEntity(CinemaRoom cinemaRoom) {
        CinemaRoomEntity cinemaRoomEntity = new CinemaRoomEntity();
        cinemaRoomEntity.setNameCinemaRoom(cinemaRoom.getNameCinemaRoom());
        cinemaRoomEntity.setCinemaRoomDesc(cinemaRoom.getCinemaRoomDesc());
        cinemaRoomEntity.setNumberChair(cinemaRoom.getNumberChair());

        CinemaEntity cinemaEntity = new CinemaEntity();
//        cinemaEntity.setNameCinema(cinemaRoom.getNameCinema());
        cinemaRoomEntity.setCinemaEntity(cinemaEntity);

        return cinemaRoomEntity;
    }
}
