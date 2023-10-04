package com.example.cinema_booking.controller.AdminController;

import com.example.cinema_booking.domain.Shows;
import com.example.cinema_booking.entity.*;
import com.example.cinema_booking.exception.ScheduleConflictException;
import com.example.cinema_booking.service.CinemaService;
import com.example.cinema_booking.service.FilmService;
import com.example.cinema_booking.service.RoomService;
import com.example.cinema_booking.service.ShowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin")
public class ShowsController {

    @Autowired
    private ShowsService showsService;

    @Autowired
    private FilmService filmService;

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private RoomService roomService;

    @GetMapping("showList")
    public String listShows(Model model) {
        List<ShowsEntity> shows = showsService.getAllShow();
        model.addAttribute("shows",shows );
        return "adminHtml/adminShows";
    }

    @GetMapping("addShow")
    public String addShowPage(Model model, Shows shows) {
        List<FilmEntity> film = filmService.getAllFilm();
        List<CinemaEntity> cinema = cinemaService.getAllCinema();
        List<CinemaRoomEntity> rooms = roomService.getAllRoom();
        model.addAttribute("shows", shows);
        model.addAttribute("films", film);
        model.addAttribute("cinemas", cinema);
        model.addAttribute("rooms", rooms);
        return "adminHtml/addShows"; // Trả về trang thêm phim (addFilm.html)
    }


    @PostMapping("/createShow")
    public ResponseEntity<?> createShow(@RequestBody ShowsEntity show) {
        try {
            ShowsEntity createdShow = showsService.createShow(show);
            return ResponseEntity.ok(createdShow);
        } catch (ScheduleConflictException e) {
            return ResponseEntity.badRequest().body("Lịch chiếu bị trùng");
        }
    }
}
