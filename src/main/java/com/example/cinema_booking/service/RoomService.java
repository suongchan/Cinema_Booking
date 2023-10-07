package com.example.cinema_booking.service;

import com.example.cinema_booking.entity.CinemaRoomEntity;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

public interface RoomService {

    List<CinemaRoomEntity> getAllRoom();

    CinemaRoomEntity addRoom(CinemaRoomEntity cinemaRoom);

    CinemaRoomEntity getRoomById(Long id);
//
    CinemaRoomEntity update(CinemaRoomEntity cinemaRoom) ;

    void deleteRoom(Long id) throws UserPrincipalNotFoundException;

    Optional<CinemaRoomEntity> getRoomByCinemaId(Long id);

}
