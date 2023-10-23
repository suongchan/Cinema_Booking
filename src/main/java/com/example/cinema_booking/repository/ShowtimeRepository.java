package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.CinemaEntity;
import com.example.cinema_booking.entity.CinemaRoomEntity;
import com.example.cinema_booking.entity.ShowtimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<ShowtimeEntity, Long> {

    @Query("SELECT s FROM ShowtimeEntity s WHERE s.cinemaRoomEntity = :room " +
            "AND ((s.start >= :start AND s.start <= :end) " +
            "OR (s.end >= :start AND s.end <= :end))" +
            "AND DATE(s.dateShow) = :date"
    )

    List<ShowtimeEntity> findShowsByRoomAndTimeRange(
            @Param("room") CinemaRoomEntity cinemaRoomEntity,
            @Param("start") LocalTime start,
            @Param("end") LocalTime end,
            @Param("date") LocalDate dateShow
    );


    List<ShowtimeEntity> findByFilmEntityIdFilm(Long id);

    List<ShowtimeEntity> findByFilmEntityIdFilmAndCinemaEntityIdCinema(Long filmId, Long idCinema);

    List<ShowtimeEntity> findByCinemaEntityIdCinema(Long idCinema);
}
