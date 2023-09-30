package com.example.cinema_booking.controller.AdminController;

import com.example.cinema_booking.domain.Cinema;
import com.example.cinema_booking.domain.CinemaRoom;
import com.example.cinema_booking.entity.CinemaEntity;
import com.example.cinema_booking.entity.CinemaRoomEntity;
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

    @GetMapping("roomList")
    public String listRoom(Model model) {
        List<CinemaRoomEntity> rooms = roomService.getAllRoom();
        model.addAttribute("rooms", rooms);
        return "adminHtml/adminRoomCinema";
    }
    @GetMapping("addRoom")
    public String addRoomForm( Model model) {
        List<CinemaEntity> cinemas = cinemaService.getAllCinema();
        model.addAttribute("cinemaRoom", new CinemaRoomEntity());
        model.addAttribute("cinemas", cinemas);
        return "adminHtml/addRoom";
    }
//    @PostMapping("addRoom")
//    public String addCinemaRoom(@RequestParam Long idCinema, @RequestParam String nameCinemaRoom) {
//        Optional<CinemaEntity> cinema = cinemaService.findById(idCinema); // Thay thế bằng phương thức tìm kiếm bằng ID thích hợp
//        if (cinema != null) {
//            CinemaRoom cinemaRoom = new CinemaRoom();
//            cinemaRoom.setNameCinemaRoom(nameCinemaRoom);
//            cinemaRoom.setCinemaEntity(cinemaRoom.getCinemaEntity());
//            roomService.createCinemaRoom(cinemaRoom);
//        }
//        return "redirect:/admin/roomList";
//    }
//
//    @GetMapping("editRoom/{id}")
//    public String showEditRoomForm(@PathVariable Long id, Model model) {
//        CinemaRoomEntity room = roomService.getRoomById(id);
//        if (room != null) {
//            model.addAttribute("room", room);
//            return "adminHtml/editRoom";
//        } else {
//            return "redirect:/admin/roomList";
//        }
//    }
//
//    @PostMapping("saveEditedRoom")
//    public String saveEditedRoom(@ModelAttribute("room") CinemaRoom cinemaRoom, Model model) throws UserPrincipalNotFoundException {
//        try {
//            // Gọi phương thức service để cập nhật thông tin khách hàng
//            roomService.updateRoom(cinemaRoom);
//            return "redirect:/admin/roomList";
//        } catch (UserPrincipalNotFoundException e) {
//            // Xử lý khi không tìm thấy khách hàng
//            model.addAttribute("messageError", "Không tìm thấy cinema.");
//            return "adminHtml/editRoom";
//        }
//    }
//
//    @GetMapping("/deleteRoom/{id}")
//    public String deleteRoom(@PathVariable Long id) throws UserPrincipalNotFoundException {
//        roomService.deleteRoom(id);
//        return "redirect:/admin/roomList";
//    }

}
