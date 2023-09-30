package com.example.cinema_booking.service.impl;

import com.example.cinema_booking.controller.AdminController.FilmController;
import com.example.cinema_booking.entity.FilmEntity;
import com.example.cinema_booking.repository.FilmRepository;
import com.example.cinema_booking.service.FilmService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository filmRepository;
    @Override
    public List<FilmEntity> getAllFilm() {
        return filmRepository.findAll() ;
    }

    @Override
    public FilmEntity addFilm(FilmEntity film) {
        return filmRepository.save(film);
    }
}
