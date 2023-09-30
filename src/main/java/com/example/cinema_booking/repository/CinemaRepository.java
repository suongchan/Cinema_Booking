package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<CinemaEntity, Long> {
}
