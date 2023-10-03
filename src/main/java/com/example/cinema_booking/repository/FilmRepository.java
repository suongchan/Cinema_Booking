package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.CustomerEntity;
import com.example.cinema_booking.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FilmRepository extends JpaRepository<FilmEntity,Long> {

    @Query("SELECT f FROM FilmEntity f WHERE :searchDate BETWEEN f.dateStart AND f.dateEnd")
    List<FilmEntity> findAllByDateRange(@Param("searchDate") LocalDate searchDate);

    @Query("SELECT f FROM FilmEntity f WHERE f.dateStart BETWEEN :startDate AND :endDate")
    List<FilmEntity> findAllByDateSC(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<FilmEntity> findByNameFilmContaining(String filmName);

}
