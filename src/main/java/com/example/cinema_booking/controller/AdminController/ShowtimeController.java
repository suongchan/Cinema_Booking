package com.example.cinema_booking.controller.AdminController;

import com.example.cinema_booking.domain.Cinema;
import com.example.cinema_booking.domain.Film;
import com.example.cinema_booking.domain.Showtime;
import com.example.cinema_booking.entity.*;
import com.example.cinema_booking.repository.ShowtimeRepository;
import com.example.cinema_booking.service.CinemaService;
import com.example.cinema_booking.service.FilmService;
import com.example.cinema_booking.service.RoomService;
import com.example.cinema_booking.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class ShowtimeController {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private FilmService filmService;

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private RoomService roomService;

    @GetMapping("showList")
    public String listShows(Model model) {
        List<ShowtimeEntity> showtime = showtimeService.getAllShows();
        model.addAttribute("showtime",showtime );
        return "adminHtml/adminShows";
    }

    @GetMapping("addShow")
    public String addShowPage(Model model ) {
        List<FilmEntity> film = filmService.getAllFilm();

        List<CinemaEntity> cinema = cinemaService.getAllCinema();
        List<CinemaRoomEntity> room = roomService.getAllRoom();
        ShowtimeEntity showtime = new ShowtimeEntity();
        model.addAttribute("showtime", showtime);
        model.addAttribute("films", film);
        model.addAttribute("cinemas", cinema);
        model.addAttribute("rooms", room);
        return "adminHtml/addShows"; // Trả về trang thêm phim (addFilm.html)
    }


    @PostMapping("createShow")
    public String createShow(@ModelAttribute("showtime") ShowtimeEntity showtime, Model model) throws UserPrincipalNotFoundException {

        List<ShowtimeEntity> conflictingShowtimes = showtimeRepository.findShowsByRoomAndTimeRange(showtime.getCinemaRoomEntity(), showtime.getStart(), showtime.getEnd(), showtime.getDateShow());

        if (!conflictingShowtimes.isEmpty()) {
            // Lịch chiếu trùng lịch, hiển thị thông báo lên trang HTML
            model.addAttribute("conflictingShowtimes", true);
            // Đây là nơi bạn có thể thêm thông báo lỗi khác (ví dụ: "Lịch chiếu trùng lịch")
            model.addAttribute("cinemas", cinemaService.getAllCinema());
            model.addAttribute("rooms", roomService.getAllRoom());
            model.addAttribute("films", filmService.getAllFilm());
            return "adminHtml/addShows";
        }

        // Tạo một đối tượng ShowtimeEntity
        ShowtimeEntity showtimeEntity = new ShowtimeEntity();
        showtimeEntity.setStart(showtime.getStart());
        showtimeEntity.setEnd(showtime.getEnd());

        // Lấy đối tượng CinemaEntity từ dịch vụ hoặc repository
        CinemaEntity cinemaEntity = cinemaService.getCinemaById(showtime.getCinemaEntity().getIdCinema());

        // Lấy đối tượng CinemaRoomEntity từ dịch vụ hoặc repository
        CinemaRoomEntity roomEntity = roomService.getRoomById(showtime.getCinemaRoomEntity().getIdCinemaRoom());

        // Lấy đối tượng FilmEntity từ dịch vụ hoặc repository
        FilmEntity filmEntity = filmService.getFilmById(showtime.getFilmEntity().getIdFilm());

        // Đặt các tham chiếu vào ShowtimeEntity
        showtimeEntity.setCinemaEntity(cinemaEntity);
        showtimeEntity.setCinemaRoomEntity(roomEntity);
        showtimeEntity.setFilmEntity(filmEntity);

        // Lưu ShowtimeEntity vào cơ sở dữ liệu
        showtimeService.createShows(showtimeEntity);
        return "redirect:/admin/showList";
    }

    @GetMapping("/editShow/{id}")
    public String editShowPage(@PathVariable Long id, Model model) {
        ShowtimeEntity showtime = showtimeService.getShowtimeById(id).orElse(null);
        List<FilmEntity> film = filmService.getAllFilm();
        List<CinemaEntity> cinema = cinemaService.getAllCinema();
        List<CinemaRoomEntity> room = roomService.getAllRoom();

        model.addAttribute("showtime", showtime);
        model.addAttribute("films", film);
        model.addAttribute("cinemas", cinema);
        model.addAttribute("rooms", room);
        return "adminHtml/editShows";
    }
    @PostMapping("/editShow")
    public String editShow(@ModelAttribute("showtime") ShowtimeEntity showtime, Model model) throws UserPrincipalNotFoundException {

        List<ShowtimeEntity> conflictingShowtimes = showtimeRepository.findShowsByRoomAndTimeRange(showtime.getCinemaRoomEntity(), showtime.getStart(), showtime.getEnd(), showtime.getDateShow());

        if (!conflictingShowtimes.isEmpty()) {
            // Lịch chiếu trùng lịch, hiển thị thông báo lên trang HTML
            model.addAttribute("conflictingShowtimes", true);
            // Đây là nơi bạn có thể thêm thông báo lỗi khác (ví dụ: "Lịch chiếu trùng lịch")
            model.addAttribute("cinemas", cinemaService.getAllCinema());
            model.addAttribute("rooms", roomService.getAllRoom());
            model.addAttribute("films", filmService.getAllFilm());
            return "adminHtml/editShows";
        }


        showtimeService.update(showtime);
        return "redirect:/admin/showList";
    }

    @GetMapping("/deleteShow/{id}")
    public String deleteShow(@PathVariable Long id) throws UserPrincipalNotFoundException {
        showtimeService.deleteShow(id);
        return "redirect:/admin/showList";
    }
}