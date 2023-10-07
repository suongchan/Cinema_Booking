package com.example.cinema_booking.controller.CustomerController;

import com.example.cinema_booking.domain.Film;
import com.example.cinema_booking.entity.CinemaEntity;
import com.example.cinema_booking.entity.FilmEntity;
import com.example.cinema_booking.entity.ShowtimeEntity;
import com.example.cinema_booking.service.CinemaService;
import com.example.cinema_booking.service.FilmService;
import com.example.cinema_booking.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @GetMapping("listShowtime/{id}")
    public String listShowtime(@PathVariable Long id, Model model) {
        FilmEntity film = filmService.getFilmById(id);
        List<CinemaEntity> cinema = cinemaService.getAllCinema();
        List<ShowtimeEntity> showtime = showtimeService.getAllShows();
        model.addAttribute("films", film);
        model.addAttribute("cinemas", cinema);
        model.addAttribute("showtimes", showtime);
        return "customerHtml/buyTicket";
    }
}
