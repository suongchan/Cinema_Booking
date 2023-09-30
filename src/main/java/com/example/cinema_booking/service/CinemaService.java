package com.example.cinema_booking.service;

import com.example.cinema_booking.domain.Cinema;
import com.example.cinema_booking.entity.CinemaEntity;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

public interface CinemaService {
    List<CinemaEntity> getAllCinema();

    Long createCinema(Cinema cinema);

    CinemaEntity getCinemaById(Long id);

    void updateCinema(Cinema cinema) throws UserPrincipalNotFoundException;

    void deleteCinema(Long id) throws UserPrincipalNotFoundException;

    Optional<CinemaEntity> findById(Long idCinema);
}
