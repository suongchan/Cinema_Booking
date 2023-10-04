package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.CinemaRoomEntity;
import com.example.cinema_booking.entity.FilmEntity;
import com.example.cinema_booking.entity.ShowsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowsRepository extends JpaRepository<ShowsEntity,Long> {
    List<ShowsEntity> findByRoomAndStartBetween(Long idShow, CinemaRoomEntity room, FilmEntity filmEntity, LocalDateTime start, LocalDateTime end);
}
