package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.CinemaEntity;
import com.example.cinema_booking.entity.CinemaRoomEntity;
import com.example.cinema_booking.entity.ShowsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface ShowsRepository extends JpaRepository<ShowsEntity,Long> {
//    List<ShowsEntity> findByRoomAndStartBetween(Long idShow, CinemaRoomEntity room, FilmEntity filmEntity, LocalDateTime start, LocalDateTime end);

    @Query("SELECT s FROM ShowsEntity s WHERE s.room = :room " +
            "AND ((s.start >= :startTime AND s.start <= :endTime) " +
            "OR (s.end >= :startTime AND s.end <= :endTime))")
    List<ShowsEntity> findShowsByRoomAndTimeRange(
            @Param("room") CinemaRoomEntity room,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );

}
