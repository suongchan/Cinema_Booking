package com.example.cinema_booking.controller.AdminController;

import com.example.cinema_booking.domain.CinemaRoom;
import com.example.cinema_booking.entity.ChairEntity;
import com.example.cinema_booking.entity.CinemaEntity;
import com.example.cinema_booking.entity.CinemaRoomEntity;
import com.example.cinema_booking.service.ChairService;
import com.example.cinema_booking.service.CinemaService;
import com.example.cinema_booking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class CinemaRoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private ChairService chairService;

    @GetMapping("roomList")
    public String listRoom(Model model) {
        List<CinemaRoomEntity> room = roomService.getAllRoom();
        model.addAttribute("rooms", room);
        return "adminHtml/adminRoomCinema";
    }

    @GetMapping("cinemaList/{id}")
    public Optional<CinemaRoomEntity> getCinemaRooms(@PathVariable Long id) {
        Optional<CinemaRoomEntity> rooms = roomService.getRoomByCinemaId(id);
        return rooms;
    }

    @GetMapping("addRoom")
    public String addFilmPage(Model model, CinemaRoom cinemaRoom) {
        List<CinemaEntity> cinema = cinemaService.getAllCinema();
        model.addAttribute("rooms", cinemaRoom);
        model.addAttribute("cinemas", cinema);
        return "adminHtml/addRoom"; // Trả về trang thêm phim (addFilm.html)
    }
    @PostMapping("addRoom")
    public String addFilm(@ModelAttribute("rooms") CinemaRoomEntity cinemaRoom) {

        CinemaRoomEntity cinemaRoomEntity = roomService.addRoom(cinemaRoom);
        chairService.createChair(cinemaRoomEntity);
        List<ChairEntity> chairs = cinemaRoom.getChairs();
        if (chairs != null) {
            // Duyệt qua danh sách ghế
            for (ChairEntity chair : chairs) {
                // Kiểm tra xem ghế đã tồn tại trong cơ sở dữ liệu chưa
                if (chair.getIdChair() == null) {
                    // Ghế chưa tồn tại, thì tạo mới và đặt trạng thái ghế là khả dụng
                    chair.setOccupied(false);
                    chairService.createChair(chair.getCinemaRoom());
                } else {
                    // Ghế đã tồn tại trong cơ sở dữ liệu, không cần thực hiện gì
                }
            }
        }
        return "redirect:/admin/roomList";
    }

    @GetMapping("/editRoom/{id}")
    public String editFilmPage(@PathVariable Long id, Model model) {
        CinemaRoomEntity cinemaRoom = roomService.getRoomById(id);
        List<CinemaEntity> cinemaEntities = cinemaService.getAllCinema();
        model.addAttribute("rooms", cinemaRoom);
        model.addAttribute("cinemas", cinemaEntities);
        return "adminHtml/editRoom";
    }

    @PostMapping("/editRoom")
    public String editRoom(@ModelAttribute("rooms") CinemaRoomEntity cinemaRoom) throws UserPrincipalNotFoundException {
        roomService.update(cinemaRoom);
        return "redirect:/admin/roomList"; // Chuyển hướng sau khi chỉnh sửa phim
    }

    @GetMapping("/deleteRoom/{id}")
    public String deleteRoom(@PathVariable Long id) throws UserPrincipalNotFoundException {
        roomService.deleteRoom(id);
        return "redirect:/admin/roomList";
    }

}
