package com.example.cinema_booking.controller.CustomerController;


import com.example.cinema_booking.domain.Film;
import com.example.cinema_booking.entity.FilmEntity;
import com.example.cinema_booking.service.CategoryService;
import com.example.cinema_booking.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("film")
public class CustomerFilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping()
    private String test(){
        return "customerHtml/homePage";
    }

    @GetMapping("/dang-chieu")
    public String showFilmDC(Model model) {
        List<Film> films = filmService.getAllFilmDC(LocalDate.now());
        model.addAttribute("filmDC", films);
        return "customerHtml/filmDC";
    }

    @GetMapping("/sap-chieu")
    public String showFilmSC(Model model) {
        List<Film> films = filmService.getAllFilmSC(LocalDate.now());
        model.addAttribute("filmSC", films);
        return "customerHtml/filmSC";
    }

    @GetMapping("detail/{id}")
    public String showDetailFilm(@PathVariable String id, Model model) {
        Long filmId = Long.parseLong(id);
        FilmEntity film = filmService.getFilmById(filmId);
        model.addAttribute("film", film);
        return "customerHtml/film_detail";
    }
}
