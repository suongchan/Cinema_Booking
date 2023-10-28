package com.example.cinema_booking.controller.CustomerController;

import com.example.cinema_booking.domain.Chair;
import com.example.cinema_booking.domain.Cinema;
import com.example.cinema_booking.entity.*;
import com.example.cinema_booking.exception.CustomerNotFoundException;
import com.example.cinema_booking.repository.SeatStatusRepository;
import com.example.cinema_booking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Principal;
import java.sql.SQLOutput;
import java.util.*;

@Controller
@RequestMapping("customer")
public class ChairController {

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private ChairService chairService;
    @Autowired
    private SeatStatusService seatStatusService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ServiceService serviceService;

    @GetMapping("listChair/{id}")
    public String listChair(@PathVariable Long id, Model model) {
         ShowtimeEntity showtime = showtimeService.getShowtimeById(id);
        List<SeatStatusEntity> seatStatus =  seatStatusService.getSeatStatusEntitiesByShowtime(id);
        model.addAttribute("showtime", showtime);
        model.addAttribute("seatStatus", seatStatus);

        return "customerHtml/buyTicket2";
    }


    @GetMapping("buyPopCorn/{id}")
    public String listPopcorn(@PathVariable Long id, Model model,@RequestParam String selectedChairs) {
        ShowtimeEntity showtime = showtimeService.getShowtimeById(id);
        List<ServiceEntity> service = serviceService.getAllService();
        model.addAttribute("showtime", showtime);
        model.addAttribute("services", service);
        System.out.println(selectedChairs);
        return "customerHtml/buyPopcorn";
    }

    @PostMapping("/updateSeatStatus")
    public ResponseEntity<String> updateSeatStatus(@RequestBody Map<String, Object> requestData) {
        System.out.println("dhksjfhjsdg");
        List<String> selectedChairs = (List<String>) requestData.get("selectedChairs");
        Long showtimeId = Long.parseLong(requestData.get("showtimeId").toString());

        try {
            // Cập nhật trạng thái của ghế đã chọn
            seatStatusService.updateSeatStatus(showtimeId, selectedChairs);
            System.out.println(showtimeId);
            System.out.println(selectedChairs);

            // Trả về thông báo thành công
            return ResponseEntity.ok("Seat status updated successfully");
        } catch (Exception e) {
            // Xử lý lỗi nếu cần
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating seat status");
        }
    }





}
