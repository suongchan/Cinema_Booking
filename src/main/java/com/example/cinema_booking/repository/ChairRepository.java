package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.ChairEntity;
import com.example.cinema_booking.entity.CinemaRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChairRepository extends JpaRepository<ChairEntity, Long> {

}
