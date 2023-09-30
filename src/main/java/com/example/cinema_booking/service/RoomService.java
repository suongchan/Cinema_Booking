package com.example.cinema_booking.service;

import com.example.cinema_booking.domain.CinemaRoom;
import com.example.cinema_booking.entity.CinemaRoomEntity;

import java.util.List;

public interface RoomService {

    List<CinemaRoomEntity> getAllRoom();

    Long createCinemaRoom(CinemaRoom cinemaRoom);

//    public Long createRoom(String nameCinemaRoom, Long idCinema);
//
//    CinemaRoomEntity getRoomById(Long id);
//
//    void updateRoom(CinemaRoom cinemaRoom) throws UserPrincipalNotFoundException;
//
//    void deleteRoom(Long id) throws UserPrincipalNotFoundException;
}
