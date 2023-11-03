package com.example.cinema_booking.controller.AdminController;

import com.example.cinema_booking.entity.*;
import com.example.cinema_booking.repository.SeatStatusRepository;
import com.example.cinema_booking.repository.ShowtimeRepository;
import com.example.cinema_booking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;

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
    private ChairService chairService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private SeatStatusService seatStatusService;
    @Autowired
    private SeatStatusRepository seatStatusRepository;

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


//    @PostMapping("createShow")
//    public String createShow(@ModelAttribute("showtime") ShowtimeEntity showtime, Model model) throws UserPrincipalNotFoundException {
//
//        List<ShowtimeEntity> conflictingShowtimes = showtimeRepository.findShowsByRoomAndTimeRange(showtime.getCinemaRoomEntity(), showtime.getStart(), showtime.getEnd(), showtime.getDateShow());
//
//        if (!conflictingShowtimes.isEmpty()) {
//            // Lịch chiếu trùng lịch, hiển thị thông báo lên trang HTML
//            model.addAttribute("conflictingShowtimes", true);
//            // Đây là nơi bạn có thể thêm thông báo lỗi khác (ví dụ: "Lịch chiếu trùng lịch")
//            model.addAttribute("cinemas", cinemaService.getAllCinema());
//            model.addAttribute("rooms", roomService.getAllRoom());
//            model.addAttribute("films", filmService.getAllFilm());
//            return "adminHtml/addShows";
//        }
//
//        // Tạo một đối tượng ShowtimeEntity
//        ShowtimeEntity showtimeEntity = new ShowtimeEntity();
//        showtimeEntity.setStart(showtime.getStart());
//        showtimeEntity.setEnd(showtime.getEnd());
//        showtimeEntity.setDateShow(showtime.getDateShow());
//
//        // Lấy đối tượng CinemaEntity từ dịch vụ hoặc repository
//        CinemaEntity cinemaEntity = cinemaService.getCinemaById(showtime.getCinemaEntity().getIdCinema());
//
//        // Lấy đối tượng CinemaRoomEntity từ dịch vụ hoặc repository
//        CinemaRoomEntity roomEntity = roomService.getRoomById(showtime.getCinemaRoomEntity().getIdCinemaRoom());
//
//        // Lấy đối tượng FilmEntity từ dịch vụ hoặc repository
//        FilmEntity filmEntity = filmService.getFilmById(showtime.getFilmEntity().getIdFilm());
//
//        // Đặt các tham chiếu vào ShowtimeEntity
//        showtimeEntity.setCinemaEntity(cinemaEntity);
//        showtimeEntity.setCinemaRoomEntity(roomEntity);
//        showtimeEntity.setFilmEntity(filmEntity);
//
//        // Lấy danh sách ghế từ phòng chiếu (đã có sẵn trong cơ sở dữ liệu)
//        List<ChairEntity> chairs = showtimeEntity.getCinemaRoomEntity().getChairs();
//
//        // Tạo danh sách SeatStatusEntity cho mỗi ghế và đặt trạng thái là chưa được đặt
//        List<SeatStatusEntity> seatStatusEntities = new ArrayList<>();
//        for (ChairEntity chair : chairs) {
//            SeatStatusEntity seatStatus = new SeatStatusEntity();
//            seatStatus.setShowtime(showtime);
//            seatStatus.setChair(chair);
//            seatStatus.setOccupied(false);
//            seatStatusEntities.add(seatStatus);
//        }
//        if (seatStatusEntities.size() != showtimeEntity.getCinemaRoomEntity().getChairs().size()) {
//            System.out.println("Số lượng ghế trong seatStatusEntities không phù hợp với số ghế trong CinemaRoomEntity.");
//            // Xử lý lỗi tại đây nếu cần
//        }
//
//        // Lưu danh sách SeatStatusEntity vào cơ sở dữ liệu
//        seatStatusRepository.saveAll(seatStatusEntities);
//
//        // Lưu ShowtimeEntity vào cơ sở dữ liệu
//        showtimeService.createShows(showtimeEntity);
//        System.out.println("Thông tin ShowtimeEntity mới: " + showtimeEntity.toString());
//        return "redirect:/admin/showList";
//    }

    @PostMapping("createShow")
    public String createShow(@ModelAttribute("showtime") ShowtimeEntity showtime, Model model) throws UserPrincipalNotFoundException {
        // Lấy đối tượng CinemaEntity từ dịch vụ hoặc repository
        CinemaEntity cinemaEntity = cinemaService.getCinemaById(showtime.getCinemaEntity().getIdCinema());

        // Lấy đối tượng CinemaRoomEntity từ dịch vụ hoặc repository
        CinemaRoomEntity roomEntity = roomService.getRoomById(showtime.getCinemaRoomEntity().getIdCinemaRoom());

        // Lấy đối tượng FilmEntity từ dịch vụ hoặc repository
        FilmEntity filmEntity = filmService.getFilmById(showtime.getFilmEntity().getIdFilm());

        // Đặt các tham chiếu vào ShowtimeEntity
        showtime.setCinemaEntity(cinemaEntity);
        showtime.setCinemaRoomEntity(roomEntity);
        showtime.setFilmEntity(filmEntity);

        // Lưu ShowtimeEntity vào cơ sở dữ liệu trước
        showtimeService.createShows(showtime);

        // Lấy danh sách ghế từ phòng chiếu (đã có sẵn trong cơ sở dữ liệu)
        List<ChairEntity> chairs = roomEntity.getChairs();

        // Tạo danh sách SeatStatusEntity cho mỗi ghế và đặt trạng thái là chưa được đặt
        List<SeatStatusEntity> seatStatusEntities = new ArrayList<>();
        for (ChairEntity chair : chairs) {
            SeatStatusEntity seatStatus = new SeatStatusEntity();
            seatStatus.setShowtime(showtime);
            seatStatus.setChair(chair);
            seatStatus.setOccupied(false);
            seatStatusEntities.add(seatStatus);
        }

        // Lưu danh sách SeatStatusEntity vào cơ sở dữ liệu
        seatStatusRepository.saveAll(seatStatusEntities);

        System.out.println("Thông tin ShowtimeEntity mới: " + showtime.toString());
        return "redirect:/admin/showList";
    }


    @GetMapping("/editShow/{id}")
    public String editShowPage(@PathVariable Long id, Model model) {
        ShowtimeEntity showtime = showtimeService.getShowtimeById(id);
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