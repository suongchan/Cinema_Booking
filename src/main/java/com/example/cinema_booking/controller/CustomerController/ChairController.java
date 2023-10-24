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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("customer")
public class ChairController {
    @Autowired
    private FilmService filmService;

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private ChairService chairService;
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @GetMapping("listChair/{id}")
    public String listShowtime(@PathVariable Long id, Model model) {
        ShowtimeEntity showtime = showtimeService.getShowtimeById(id);
        List<ChairEntity> chairs = chairService.getChairsByShowtime(showtime);
        System.out.println(chairs);
//        setChairName(chairs); // Thiết lập nameChair cho từng ghế


        model.addAttribute("chairs", chairs);
        model.addAttribute("showtime", showtime);
        return "customerHtml/buyTicket2";
    }
//        private void setChairName(List<ChairEntity> chairs) {
//            char row = 'A'; // Bắt đầu từ hàng A
//            int column = 1; // Bắt đầu từ cột 1
//
//            for (ChairEntity chair : chairs) {
//                chair.setNameChair(row + Integer.toString(column));
//                column++;
//
//                // Nếu đạt cột 10, di chuyển đến hàng tiếp theo và reset cột về 1
//                if (column > 10) {
//                    row++;
//                    column = 1;
//                }
//                System.out.println(chair);
//            }
//
//        }

}
