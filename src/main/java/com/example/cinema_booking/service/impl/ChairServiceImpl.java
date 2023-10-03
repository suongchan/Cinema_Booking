package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.entity.ChairEntity;
import com.example.cinema_booking.entity.CinemaEntity;
import com.example.cinema_booking.entity.CinemaRoomEntity;
import com.example.cinema_booking.repository.ChairRepository;
import com.example.cinema_booking.repository.RoomRepository;
import com.example.cinema_booking.service.ChairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ChairServiceImpl implements ChairService {
    @Autowired
    private ChairRepository chairRepository;

    @Override
    public void createChair(CinemaRoomEntity cinemaRoom) {
        int numberChair = cinemaRoom.getNumberChair();
        List<ChairEntity> chairEntities = new ArrayList<>();

        Long idCinema= cinemaRoom.getCinemaEntity().getIdCinema();
        for (int i = 1; i <= numberChair ; i++) {
            ChairEntity chairEntity = new ChairEntity();
            chairEntity.setCinemaRoom(cinemaRoom);
            chairEntity.setOccupied(false);
            chairEntity.setCinemaEntity(new CinemaEntity(idCinema));
            chairRepository.save(chairEntity);
            chairEntities.add(chairEntity);
        }
    }

}
