package com.example.cinema_booking.controller.AdminController;

import com.example.cinema_booking.domain.Category;
import com.example.cinema_booking.domain.Cinema;
import com.example.cinema_booking.entity.CategoryEntity;
import com.example.cinema_booking.entity.CinemaEntity;
import com.example.cinema_booking.service.CategoryService;
import com.example.cinema_booking.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Controller
@RequestMapping("admin")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @Value("${imagePath}")
    private String imagePath;

    @GetMapping("cinemaList")
    public String listCinema(Model model) {
        List<CinemaEntity> cinemas = cinemaService.getAllCinema();
        model.addAttribute("cinema", cinemas);
        return "adminHtml/adminCinema";
    }
    @GetMapping("addCinema")
    public String addCinemaForm(@ModelAttribute Cinema cinema, Model model) {
        model.addAttribute("cinema", cinema);
        return "adminHtml/addCinema";
    }
    @PostMapping("addCinema")
    public String addCinema(@ModelAttribute("cinema")Cinema cinema, @RequestParam("file") MultipartFile file, Model model) throws IOException, URISyntaxException
    {
        String message = "";
        try {
            File file1 = new File(imagePath + file.getOriginalFilename());
            try (OutputStream os = new FileOutputStream(file1)) {
                os.write(file.getBytes());
            }

            message = "Uploaded the file successfully: " +file.getOriginalFilename();
            model.addAttribute("message", message);
        } catch (Exception e){
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            model.addAttribute("message", message);
        }
        Long id = cinemaService.createCinema(cinema);
        cinema.setIdCinema(id);
        return "redirect:/admin/cinemaList";
    }

    @GetMapping("editCinema/{id}")
    public String showEditCinemaForm(@PathVariable Long id, Model model) {
        CinemaEntity cinema = cinemaService.getCinemaById(id);
        if (cinema != null) {
            model.addAttribute("cinema", cinema);
            return "adminHtml/editCinema";
        } else {
            return "redirect:/admin/cinemaList";
        }
    }

    @PostMapping("saveEditedCinema")
    public String saveEditedCinema(@ModelAttribute("cinema") Cinema cinema, Model model) throws UserPrincipalNotFoundException {
        try {
            // Gọi phương thức service để cập nhật thông tin khách hàng
            cinemaService.updateCinema(cinema);
            return "redirect:/admin/cinemaList";
        } catch (UserPrincipalNotFoundException e) {
            // Xử lý khi không tìm thấy khách hàng
            model.addAttribute("messageError", "Không tìm thấy cinema.");
            return "adminHtml/editCinema";
        }
    }

    @GetMapping("/deleteCinema/{id}")
    public String deleteCinema(@PathVariable Long id) throws UserPrincipalNotFoundException {
        cinemaService.deleteCinema(id);
        return "redirect:/admin/cinemaList";
    }

}
