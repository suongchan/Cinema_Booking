package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<FilmEntity,Long> {
}
