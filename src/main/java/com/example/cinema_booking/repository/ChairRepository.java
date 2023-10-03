package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.ChairEntity;
import com.example.cinema_booking.entity.CinemaRoomEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

public interface ChairRepository extends JpaRepository<ChairEntity, Long> {

    @Modifying
    @Query("DELETE FROM ChairEntity c WHERE c.cinemaRoom = :cinemaRoom")
    void deleteAllByCinemaRoom(@Param("cinemaRoom") CinemaRoomEntity cinemaRoom);

}
