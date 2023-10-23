package com.example.cinema_booking.controller.CustomerController;

import com.example.cinema_booking.entity.CinemaEntity;
import com.example.cinema_booking.entity.FilmEntity;
import com.example.cinema_booking.entity.ShowtimeEntity;
import com.example.cinema_booking.repository.ShowtimeRepository;
import com.example.cinema_booking.service.CinemaService;
import com.example.cinema_booking.service.FilmService;
import com.example.cinema_booking.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;


@Controller
@RequestMapping("customer")
public class ListShowtimeController {
    @Autowired
    private FilmService filmService;

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private ShowtimeRepository showtimeRepository;
    @GetMapping("listShowtime/{id}")
    public String listShowtime(@PathVariable Long id, Model model) {
        FilmEntity film = filmService.getFilmById(id);
        List<CinemaEntity> cinema = cinemaService.getAllCinema();
//        List<ShowtimeEntity> showtimeEntities = showtimeRepository.findAll();
        List<ShowtimeEntity> showtimeForFilm = showtimeRepository.findByFilmEntityIdFilm(id);
        showtimeForFilm.sort(Comparator.comparing(ShowtimeEntity::getDateShow));
        model.addAttribute("films", film);
        model.addAttribute("cinemas", cinema);
        model.addAttribute("showtimeForFilm", showtimeForFilm);
        return "customerHtml/buyTicket";
    }

    @GetMapping("listShowtime")
    public String listShowtime( Model model) {
        List<CinemaEntity> cinema = cinemaService.getAllCinema();
        List<ShowtimeEntity> showtimeEntities = showtimeRepository.findAll();
        model.addAttribute("cinemas", cinema);
        model.addAttribute("showtimeForFilm", showtimeEntities);
        return "customerHtml/test";
    }

}
