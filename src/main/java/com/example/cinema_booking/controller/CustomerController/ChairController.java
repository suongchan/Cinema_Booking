package com.example.cinema_booking.controller.CustomerController;

import com.example.cinema_booking.domain.Chair;
import com.example.cinema_booking.domain.CinemaRoom;
import com.example.cinema_booking.entity.ChairEntity;
import com.example.cinema_booking.entity.CinemaEntity;
import com.example.cinema_booking.entity.FilmEntity;
import com.example.cinema_booking.entity.ShowtimeEntity;
import com.example.cinema_booking.repository.ShowtimeRepository;
import com.example.cinema_booking.service.ChairService;
import com.example.cinema_booking.service.CinemaService;
import com.example.cinema_booking.service.FilmService;
import com.example.cinema_booking.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("customer")
public class ChairController {

    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping("listChair/{id}")
    public String listShowtime(@PathVariable Long id, Model model) {
         ShowtimeEntity showtime = showtimeService.getShowtimeById(id);
        model.addAttribute("showtime", showtime);
        return "customerHtml/buyTicket";
    }
}
