package com.example.cinema_booking.controller.AdminController;

import com.example.cinema_booking.domain.Film;
import com.example.cinema_booking.entity.CategoryEntity;
import com.example.cinema_booking.entity.CinemaRoomEntity;
import com.example.cinema_booking.entity.FilmEntity;
import com.example.cinema_booking.service.CategoryService;
import com.example.cinema_booking.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("filmList")
    public String listFilm(Model model) {
        List<FilmEntity> film = filmService.getAllFilm();
        model.addAttribute("films", film);
        return "adminHtml/adminFilm";
    }

    @GetMapping("addFilm")
    public String addFilmPage(Model model, Film film) {
        model.addAttribute("films", film);
        List<CategoryEntity> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);

        return "adminHtml/addFilm";
    }

    @PostMapping("addFilm")
    public String addFilm(@ModelAttribute FilmEntity film, @RequestParam Long idCategory) {
        CategoryEntity category = categoryService.getCategoryById(idCategory); // Lấy danh mục từ id
        film.setCategoryEntity(category); // Thiết lập danh mục cho phim
        filmService.addFilm(film);
        return "redirect:/admin/filmList";
    }
}
