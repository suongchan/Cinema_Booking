package com.example.cinema_booking.controller.CustomerController;


import com.example.cinema_booking.domain.Film;
import com.example.cinema_booking.entity.GiftEntity;
import com.example.cinema_booking.service.FilmService;
import com.example.cinema_booking.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping()
public class HomeController {

    @Autowired
    private FilmService filmService;
    @Autowired
    private GiftService giftService;
    @GetMapping()
    public String showHomePage(Model model) {
        List<Film> filmDC = filmService.getAllFilmDC(LocalDate.now());
        List<GiftEntity> gifts = giftService.getAllGift();
        List<Film> filmSC = filmService.getAllFilmSC(LocalDate.now());

        // Shuffle the lists to get random order
        List<Film> shuffledFilmDC = new ArrayList<>(filmDC);
        Collections.shuffle(shuffledFilmDC);

        List<Film> shuffledFilmSC = new ArrayList<>(filmSC);
        Collections.shuffle(shuffledFilmSC);

        // Get the first 4 films from each list
        List<Film> randomFilmDC = shuffledFilmDC.subList(0, Math.min(4, shuffledFilmDC.size()));
        List<Film> randomFilmSC = shuffledFilmSC.subList(0, Math.min(4, shuffledFilmSC.size()));

        // Add the random films and gifts to the model
        model.addAttribute("filmDC", randomFilmDC);
        model.addAttribute("gifts", gifts);
        model.addAttribute("filmSC", randomFilmSC);

        return "customerHtml/homePage";


    }
}

